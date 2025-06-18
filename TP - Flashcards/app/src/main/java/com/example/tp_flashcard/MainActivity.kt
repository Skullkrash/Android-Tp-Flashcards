package com.example.tp_flashcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp_flashcard.repository.FlashCardRepository
import com.example.tp_flashcard.room.FlashCardDatabase
import com.example.tp_flashcard.viewModel.FlashCardViewModel
import com.example.tp_flashcard.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = FlashCardDatabase.getDatabase(this)
        val repository = FlashCardRepository(db.flashCardDao())
        val homeViewModel = HomeViewModel(repository)
        val flashCardViewModel = FlashCardViewModel(repository)

        setContent {
            AppNavHost(
                homeViewModel = homeViewModel,
                flashCardviewModel = flashCardViewModel
            )
        }
    }
}