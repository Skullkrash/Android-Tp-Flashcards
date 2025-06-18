package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import com.example.tp_flashcard.repository.FlashCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<FlashCardCategory>>(emptyList())
    val categories: StateFlow<List<FlashCardCategory>> get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = FlashCardRepository.categories
    }
}