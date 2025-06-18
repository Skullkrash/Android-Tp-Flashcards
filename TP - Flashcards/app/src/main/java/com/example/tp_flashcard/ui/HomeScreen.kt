package com.example.tp_flashcard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Flashcards App",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Practice and test your knowledge by category!",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )
            CategoryList(
                onCategoryClick = onCategoryClick,
                categories = categories,
            )
        }
    }
}

@Composable
fun CategoryList(
    categories: List<FlashCardModels.FlashCardCategory>,
    onCategoryClick: (FlashCardModels.FlashCardCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
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