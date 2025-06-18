package com.example.tp_flashcard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.models.FlashCardModels
import com.example.tp_flashcard.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onCategoryClick: (FlashCardModels.FlashCardCategory) -> Unit,
) {
    val categories by homeViewModel.categories.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        CategoryList(
            onCategoryClick = onCategoryClick,
            categories = categories,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun CategoryList(
    categories: List<FlashCardModels.FlashCardCategory>,
    onCategoryClick: (FlashCardModels.FlashCardCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = modifier
    ) {
        items(categories.size) { index ->
            CategoryItem(
                category = categories[index],
                onCategoryClick = onCategoryClick,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: FlashCardModels.FlashCardCategory,
    onCategoryClick: (FlashCardModels.FlashCardCategory) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCategoryClick(category) },
        shape = MaterialTheme.shapes.medium,
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
        )
    }
}