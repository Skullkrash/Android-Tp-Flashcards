package com.example.tp_flashcard

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.ui.FlashCardScreen
import com.example.tp_flashcard.ui.HomeScreen
import com.example.tp_flashcard.viewModel.FlashCardViewModel
import com.example.tp_flashcard.viewModel.HomeViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    flashCardviewModel: FlashCardViewModel
) {
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
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 0
            flashCardviewModel.loadQuestionsOfCategory(categoryId)
            FlashCardScreen(
                viewModel = flashCardviewModel,
                navController = navController
            )
        }
    }
}
