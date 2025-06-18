package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.models.FlashCardModels.FlashCardUiState
import com.example.tp_flashcard.repository.FlashCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FlashCardViewModel(private val repository: FlashCardRepository) : ViewModel() {
    private val _questions = MutableStateFlow(
        FlashCardUiState(
            cardIndex = 0,
            cardList = emptyList(),
            revisionFinished = false
        )
    )
    val questions: StateFlow<FlashCardUiState> get() = _questions

    fun loadQuestionsOfCategory(categoryId: Int) {
        repository.getFlashCardsByCategory(categoryId)
            .onEach { list ->
                _questions.value = questions.value.copy(
                    cardList = list,
                    cardIndex = 0,
                    revisionFinished = false
                )
            }
            .launchIn(viewModelScope)
    }

    fun nextQuestion(currentIndex: Int) {
        if (currentIndex < questions.value.cardList.size - 1) {
            _questions.value = questions.value.copy(cardIndex = currentIndex + 1)
        } else {
            _questions.value = questions.value.copy(revisionFinished = true)
        }
    }
}