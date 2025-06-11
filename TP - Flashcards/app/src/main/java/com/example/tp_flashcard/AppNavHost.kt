package com.example.tp_flashcard

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.FlashCardScreen
import com.example.tp_flashcard.ui.HomeScreen
import com.example.tp_flashcard.viewModel.FlashCardViewModel
import com.example.tp_flashcard.viewModel.HomeViewModel

@Composable
fun AppNavHost(homeViewModel: HomeViewModel, flashCardviewModel: FlashCardViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                homeViewModel = homeViewModel,
                onCategoryClick = { category ->
                    navController.navigate("study/${category.id}")
                }
            )
        }
        composable("study/{categoryId}") { backStackEntry ->
            val categoryId = Integer.parseInt(backStackEntry.arguments?.getString("categoryId").toString())

            flashCardviewModel.loadQuestionsOfCategory(categoryId)

            FlashCardScreen(
                viewModel = flashCardviewModel
            )
        }
    }
}
