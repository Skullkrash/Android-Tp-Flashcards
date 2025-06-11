package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import com.example.tp_flashcard.models.FlashCardModels.FlashCardUiState
import com.example.tp_flashcard.repository.FlashCardRepository

class FlashCardViewModel: ViewModel() {
    private val _questions = kotlinx.coroutines.flow.MutableStateFlow<FlashCardUiState>(
        FlashCardUiState(
            cardIndex = 0,
            cardList = emptyList(),
            revisionFinished = false
        )
    )
    val questions: kotlinx.coroutines.flow.StateFlow<FlashCardUiState> get() = _questions

    fun loadQuestionsOfCategory(categoryId: Int) {
        _questions.value = questions.value.copy(
            cardList = FlashCardRepository.flashcards.filter { it.categoryId == categoryId },
            cardIndex = 0,
            revisionFinished = false
        )
        if (_questions.value.cardList.isEmpty()) {
            throw IllegalArgumentException("No questions found for category ID: $categoryId")
        }
    }

    fun nextQuestion(currentIndex: Int) {
        if (currentIndex < questions.value.cardList.size - 1) {
            _questions.value = questions.value.copy(cardIndex = currentIndex + 1)
        } else {
            _questions.value = questions.value.copy(revisionFinished = true)
        }
    }
}