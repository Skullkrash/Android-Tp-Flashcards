package com.example.tp_flashcard.viewModel

import androidx.lifecycle.ViewModel
import com.example.tp_flashcard.models.FlashCardModels.FlashCard
import com.example.tp_flashcard.models.FlashCardModels.FlashCardCategory
import com.example.tp_flashcard.repository.FlashCardRepository

class HomeViewModel : ViewModel() {

    private val _categories = kotlinx.coroutines.flow.MutableStateFlow<List<FlashCardCategory>>(emptyList())
    val categories: kotlinx.coroutines.flow.StateFlow<List<FlashCardCategory>> get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = FlashCardRepository.categories
    }
}