package com.example.tp_flashcard.room

import androidx.room.Dao
import androidx.room.Query
import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashCardDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<FlashCardCategory>>

    @Query("SELECT * FROM flashcards WHERE categoryId = :categoryId")
    fun getFlashCardsByCategory(categoryId: Int): Flow<List<FlashCard>>
}

