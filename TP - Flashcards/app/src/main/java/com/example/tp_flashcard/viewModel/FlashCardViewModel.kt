package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.models.FlashCardModels.FlashCardUiState
import com.example.tp_flashcard.repository.FlashCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlashCardViewModel : ViewModel() {
    private val _questions = MutableStateFlow(
        FlashCardUiState(
            cardIndex = 0,
            cardList = emptyList(),
            revisionFinished = false
        )
    )
    val questions: StateFlow<FlashCardUiState> get() = _questions

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