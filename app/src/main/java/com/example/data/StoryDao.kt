package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStories(): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE id = :id")
    suspend fun getStoryById(id: String): StoryEntity?

    @Query("SELECT * FROM stories WHERE genre = :genre")
    fun getStoriesByGenre(genre: String): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE narratorId = :narratorId")
    fun getStoriesByNarrator(narratorId: String): Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: StoryEntity)

    // Bookmarks
    @Query("SELECT storyId FROM bookmarks")
    fun getBookmarkedStoryIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE storyId = :storyId")
    suspend fun removeBookmark(storyId: String)

    // History
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHistory(history: HistoryEntity)

    @Query("SELECT * FROM history WHERE storyId = :storyId")
    suspend fun getHistory(storyId: String): HistoryEntity?
}
