package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.StoryTTSEngine
import com.example.data.AppDatabase
import com.example.data.Narrator
import com.example.data.PreloadedData
import com.example.data.StoryEntity
import com.example.data.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class MainTab {
    REELS, NARRATORS, BOOKMARKS, CREATOR
}

class StoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = StoryRepository(AppDatabase.getInstance(application).storyDao())
    val ttsEngine = StoryTTSEngine(application)

    private val _currentTab = MutableStateFlow(MainTab.REELS)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    private val _selectedLanguage = MutableStateFlow("en") // en, hi, es, fr, de, ja
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

    private val _selectedGenre = MutableStateFlow("All")
    val selectedGenre: StateFlow<String> = _selectedGenre.asStateFlow()

    private val _selectedNarratorId = MutableStateFlow<String?>(null)
    val selectedNarratorId: StateFlow<String?> = _selectedNarratorId.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _activeStoryIndex = MutableStateFlow(0)
    val activeStoryIndex: StateFlow<Int> = _activeStoryIndex.asStateFlow()

    private val _isReadingMode = MutableStateFlow(false) // Listening vs Reader mode
    val isReadingMode: StateFlow<Boolean> = _isReadingMode.asStateFlow()

    private val _autoNextStory = MutableStateFlow(true)
    val autoNextStory: StateFlow<Boolean> = _autoNextStory.asStateFlow()

    // Bookmarked story IDs
    val bookmarkedIds = repository.bookmarkedStoryIds.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Filtered Stories
    val stories: StateFlow<List<StoryEntity>> = combine(
        repository.allStories,
        _selectedGenre,
        _selectedNarratorId,
        _searchQuery
    ) { allStories, genre, narratorId, query ->
        allStories.filter { story ->
            val matchesGenre = (genre == "All" || story.genre.equals(genre, ignoreCase = true))
            val matchesNarrator = (narratorId == null || story.narratorId == narratorId)
            val matchesQuery = query.isBlank() || story.title.contains(query, ignoreCase = true) ||
                    story.author.contains(query, ignoreCase = true) ||
                    story.narratorName.contains(query, ignoreCase = true)
            matchesGenre && matchesNarrator && matchesQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val narrators: List<Narrator> = PreloadedData.NARRATORS

    init {
        viewModelScope.launch {
            repository.initializeDatabase()
        }
    }

    fun setTab(tab: MainTab) {
        _currentTab.value = tab
    }

    fun setLanguage(langCode: String) {
        _selectedLanguage.value = langCode
        ttsEngine.setLanguageLocale(langCode)
    }

    fun setGenreFilter(genre: String) {
        _selectedGenre.value = genre
        _activeStoryIndex.value = 0
    }

    fun setNarratorFilter(narratorId: String?) {
        _selectedNarratorId.value = narratorId
        _activeStoryIndex.value = 0
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setActiveStoryIndex(index: Int) {
        _activeStoryIndex.value = index
    }

    fun toggleReadingMode() {
        _isReadingMode.value = !_isReadingMode.value
    }

    fun toggleAutoNext() {
        _autoNextStory.value = !_autoNextStory.value
    }

    fun toggleBookmark(storyId: String) {
        viewModelScope.launch {
            val isBookmarked = bookmarkedIds.value.contains(storyId)
            repository.toggleBookmark(storyId, isBookmarked)
        }
    }

    fun playStoryNarration(story: StoryEntity) {
        val content = when (_selectedLanguage.value) {
            "hi" -> story.contentHi
            "es" -> story.contentEs
            "fr" -> story.contentFr
            "de" -> story.contentDe
            "ja" -> story.contentJa
            else -> story.contentEn
        }
        val paragraphs = content.split("\n\n").map { it.trim() }.filter { it.isNotEmpty() }
        val narrator = narrators.find { it.id == story.narratorId }
        val pitch = narrator?.defaultPitch ?: story.narratorPitch
        val speed = narrator?.defaultSpeed ?: story.narratorSpeed

        ttsEngine.startNarration(
            storyId = story.id,
            paragraphs = paragraphs,
            langCode = _selectedLanguage.value,
            pitch = pitch,
            speed = speed,
            startIndex = 0,
            onCompleted = {
                if (_autoNextStory.value) {
                    val currentList = stories.value
                    if (_activeStoryIndex.value < currentList.size - 1) {
                        _activeStoryIndex.value += 1
                        playStoryNarration(currentList[_activeStoryIndex.value])
                    }
                }
            }
        )
    }

    fun createCustomStory(
        title: String,
        genre: String,
        author: String,
        narratorId: String,
        contentEn: String,
        contentHi: String,
        coverImageRes: String = "img_story_sci_fi"
    ) {
        viewModelScope.launch {
            val narrator = narrators.find { it.id == narratorId } ?: narrators.first()
            val newStory = StoryEntity(
                id = "story_custom_${System.currentTimeMillis()}",
                title = title,
                genre = genre,
                author = author.ifBlank { "AI & Community" },
                narratorId = narrator.id,
                narratorName = narrator.name,
                narratorTitle = narrator.title,
                narratorPitch = narrator.defaultPitch,
                narratorSpeed = narrator.defaultSpeed,
                coverImageRes = coverImageRes,
                readTimeMinutes = maxOf(1, contentEn.length / 300),
                defaultLanguage = "en",
                contentEn = contentEn,
                contentHi = contentHi.ifBlank { contentEn },
                contentEs = contentEn,
                contentFr = contentEn,
                contentDe = contentEn,
                contentJa = contentEn,
                likesCount = 1,
                isFeatured = true
            )
            repository.addCustomStory(newStory)
            _currentTab.value = MainTab.REELS
            _activeStoryIndex.value = 0
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsEngine.shutdown()
    }
}
