package com.example.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class StoryRepository(private val storyDao: StoryDao) {

    val allStories: Flow<List<StoryEntity>> = storyDao.getAllStories()
    val bookmarkedStoryIds: Flow<List<String>> = storyDao.getBookmarkedStoryIds()

    suspend fun initializeDatabase() = withContext(Dispatchers.IO) {
        // Seed initial stories if empty
        storyDao.insertStories(PreloadedData.INITIAL_STORIES)
    }

    suspend fun getStoryById(id: String): StoryEntity? = withContext(Dispatchers.IO) {
        storyDao.getStoryById(id)
    }

    suspend fun toggleBookmark(storyId: String, currentBookmarked: Boolean) = withContext(Dispatchers.IO) {
        if (currentBookmarked) {
            storyDao.removeBookmark(storyId)
        } else {
            storyDao.addBookmark(BookmarkEntity(storyId))
        }
    }

    suspend fun addCustomStory(story: StoryEntity) = withContext(Dispatchers.IO) {
        storyDao.insertStory(story)
    }

    suspend fun saveHistory(storyId: String, paragraphIndex: Int, posMs: Long) = withContext(Dispatchers.IO) {
        storyDao.updateHistory(HistoryEntity(storyId, paragraphIndex, posMs, System.currentTimeMillis()))
    }
}
