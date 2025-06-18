package com.example.tp_flashcard.repository

import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import com.example.tp_flashcard.room.FlashCardDao
import kotlinx.coroutines.flow.Flow

class FlashCardRepository(private val dao: FlashCardDao) {
    fun getCategories(): Flow<List<FlashCardCategory>> = dao.getCategories()
    fun getFlashCardsByCategory(categoryId: Int): Flow<List<FlashCard>> = dao.getFlashCardsByCategory(categoryId)
}