package com.example.tp_flashcard.models

class FlashCardModels {
    data class FlashCard(
        val id: Int = 0,
        val categoryId : Int = 0,
        val question: String = "",
        val answer: String = "",
    )

    data class FlashCardCategory(
        val id: Int = 0,
        val name: String = "",
    )

    data class FlashCardUiState(
        val cardIndex: Int = 0,
        val cardList: List<FlashCard> = emptyList(),
        val revisionFinished: Boolean = false,
    )
}