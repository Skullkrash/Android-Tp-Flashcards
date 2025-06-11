package com.example.tp_flashcard.repository

import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory

object FlashCardRepository {
    // List of categories available in the app
    val categories: List<FlashCardCategory> = listOf(
        FlashCardCategory(0, "History"),
        FlashCardCategory(1, "Geography"),
        FlashCardCategory(2, "Science"),
        FlashCardCategory(3, "Literature")
    )

    // List of flashcards with references to categories
    val flashcards: List<FlashCard> = listOf(
        FlashCard(1, 0, "Who was the first president of the United States?", "George Washington"),
        FlashCard(2, 0, "What year did World War II end?", "1945"),
        FlashCard(1, 1, "What is the capital of France?", "Paris"),
        FlashCard(2, 1, "What is the largest desert in the world?", "Sahara"),
        FlashCard(3, 2, "What is the chemical symbol for water?", "H2O"),
        FlashCard(4, 2, "What is the powerhouse of the cell?", "Mitochondria"),
        FlashCard(5, 3, "Who wrote 'To Kill a Mockingbird'?", "Harper Lee"),
        FlashCard(6, 3, "What is the main theme of '1984'?", "Totalitarianism")
    )
}