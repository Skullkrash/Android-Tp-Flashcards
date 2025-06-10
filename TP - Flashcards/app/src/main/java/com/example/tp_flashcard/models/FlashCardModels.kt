package com.example.tp_flashcard.models

class FlashCardModels {
    data class FlashCard(
        val id: Int,
        val categoryId : Int,
        val question: String,
        val answer: String,
    )

    data class FlashCardCategory(
        val id: Int,
        val name: String,
        //val flashCards: List<FlashCard> = emptyList()
    )
}