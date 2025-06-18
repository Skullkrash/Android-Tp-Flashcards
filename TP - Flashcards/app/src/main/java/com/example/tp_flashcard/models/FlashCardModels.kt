package com.example.tp_flashcard.models

import androidx.room.Entity
import androidx.room.PrimaryKey

class FlashCardModels {
    @Entity(tableName = "flashcards")
    data class FlashCard(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val categoryId: Int = 0,
        val question: String = "",
        val answer: String = "",
    )

    @Entity(tableName = "categories")
    data class FlashCardCategory(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String = "",
    )

    data class FlashCardUiState(
        val cardIndex: Int = 0,
        val cardList: List<FlashCard> = emptyList(),
        val revisionFinished: Boolean = false,
    )
}