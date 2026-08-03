package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class StoryEntity(
    @PrimaryKey val id: String,
    val title: String,
    val genre: String,
    val author: String,
    val narratorId: String,
    val narratorName: String,
    val narratorTitle: String,
    val narratorPitch: Float = 1.0f,
    val narratorSpeed: Float = 1.0f,
    val coverImageRes: String,
    val readTimeMinutes: Int,
    val defaultLanguage: String, // e.g. "en", "hi", "es", "fr"
    val contentEn: String,
    val contentHi: String,
    val contentEs: String,
    val contentFr: String,
    val contentDe: String,
    val contentJa: String,
    val likesCount: Int,
    val isFeatured: Boolean = false
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val storyId: String,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey val storyId: String,
    val lastParagraphIndex: Int = 0,
    val updatedPositionMs: Long = 0,
    val updatedAt: Long = System.currentTimeMillis()
)
