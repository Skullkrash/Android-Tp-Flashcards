package com.example.tp_flashcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.tp_flashcard.viewModel.FlashCardViewModel
import com.example.tp_flashcard.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val flashCardViewModel: FlashCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(
                homeViewModel = homeViewModel,
                flashCardviewModel = flashCardViewModel
            )
        }
    }
}