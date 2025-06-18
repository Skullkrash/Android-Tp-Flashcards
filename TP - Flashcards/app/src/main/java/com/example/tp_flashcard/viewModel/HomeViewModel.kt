package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import com.example.tp_flashcard.repository.FlashCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val repository: FlashCardRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<FlashCardCategory>>(emptyList())
    val categories: StateFlow<List<FlashCardCategory>> get() = _categories

    init {
        repository.getCategories().onEach { _categories.value = it }.launchIn(viewModelScope)
    }
}