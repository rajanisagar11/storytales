package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class StoryTTSEngine(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentParagraphIndex = MutableStateFlow(0)
    val currentParagraphIndex: StateFlow<Int> = _currentParagraphIndex.asStateFlow()

    private val _speechRate = MutableStateFlow(1.0f)
    val speechRate: StateFlow<Float> = _speechRate.asStateFlow()

    private val _currentLanguage = MutableStateFlow("en")
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    private var activeParagraphs: List<String> = emptyList()
    private var currentStoryId: String = ""
    private var onPlaybackCompleted: (() -> Unit)? = null

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _isPlaying.value = true
                    utteranceId?.let { id ->
                        if (id.startsWith("para_")) {
                            val index = id.removePrefix("para_").toIntOrNull() ?: 0
                            _currentParagraphIndex.value = index
                        }
                    }
                }

                override fun onDone(utteranceId: String?) {
                    utteranceId?.let { id ->
                        if (id.startsWith("para_")) {
                            val index = id.removePrefix("para_").toIntOrNull() ?: 0
                            if (index < activeParagraphs.size - 1) {
                                speakParagraph(index + 1)
                            } else {
                                _isPlaying.value = false
                                onPlaybackCompleted?.invoke()
                            }
                        }
                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    _isPlaying.value = false
                }
            })
            setLanguageLocale(_currentLanguage.value)
        } else {
            Log.e("StoryTTSEngine", "TTS Initialization failed!")
        }
    }

    fun setLanguageLocale(langCode: String) {
        _currentLanguage.value = langCode
        if (!isInitialized || tts == null) return
        val locale = when (langCode.lowercase()) {
            "hi" -> Locale("hi", "IN")
            "es" -> Locale("es", "ES")
            "fr" -> Locale.FRENCH
            "de" -> Locale.GERMAN
            "ja" -> Locale.JAPANESE
            "ta" -> Locale("ta", "IN")
            else -> Locale.US
        }
        val result = tts?.setLanguage(locale)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            tts?.language = Locale.US
        }
    }

    fun startNarration(
        storyId: String,
        paragraphs: List<String>,
        langCode: String,
        pitch: Float = 1.0f,
        speed: Float = 1.0f,
        startIndex: Int = 0,
        onCompleted: (() -> Unit)? = null
    ) {
        currentStoryId = storyId
        activeParagraphs = paragraphs
        onPlaybackCompleted = onCompleted
        setLanguageLocale(langCode)
        setPitchAndSpeed(pitch, speed)

        if (paragraphs.isNotEmpty() && isInitialized) {
            _currentParagraphIndex.value = startIndex
            speakParagraph(startIndex)
        }
    }

    fun setPitchAndSpeed(pitch: Float, speed: Float) {
        _speechRate.value = speed
        if (isInitialized && tts != null) {
            tts?.setPitch(pitch)
            tts?.setSpeechRate(speed)
        }
    }

    fun togglePlayPause() {
        if (_isPlaying.value) {
            pause()
        } else {
            resume()
        }
    }

    fun pause() {
        tts?.stop()
        _isPlaying.value = false
    }

    fun resume() {
        if (activeParagraphs.isNotEmpty() && _currentParagraphIndex.value < activeParagraphs.size) {
            speakParagraph(_currentParagraphIndex.value)
        }
    }

    fun seekToParagraph(index: Int) {
        if (index in activeParagraphs.indices) {
            tts?.stop()
            _currentParagraphIndex.value = index
            speakParagraph(index)
        }
    }

    private fun speakParagraph(index: Int) {
        if (!isInitialized || tts == null || index !in activeParagraphs.indices) return
        val text = activeParagraphs[index]
        val utteranceId = "para_$index"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        _isPlaying.value = true
    }

    fun stop() {
        tts?.stop()
        _isPlaying.value = false
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
