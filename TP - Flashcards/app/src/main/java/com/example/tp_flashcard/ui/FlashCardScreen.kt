package com.example.tp_flashcard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp_flashcard.viewModel.FlashCardViewModel

@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel,
    navController: NavController
) {
    val uiState by viewModel.questions.collectAsState()
    var isQuestionVisible by remember { mutableStateOf(true) }

    // Reset flip state when card changes
    LaunchedEffect(uiState.cardIndex) {
        isQuestionVisible = true
    }

    // Navigate back when revision is finished
    LaunchedEffect(uiState.revisionFinished) {
        if (uiState.revisionFinished) {
            navController.popBackStack()
        }
    }

    if (uiState.cardList.isNotEmpty()) {
        val card = uiState.cardList[uiState.cardIndex]
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressIndicator(
                    currentIndex = uiState.cardIndex + 1,
                    total = uiState.cardList.size
                )

                Flashcard(
                    text = if (isQuestionVisible) card.question else card.answer,
                    onClick = { isQuestionVisible = !isQuestionVisible }
                )

                Button(
                    onClick = { viewModel.nextQuestion(uiState.cardIndex) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    val buttonText = if (uiState.cardIndex < uiState.cardList.size - 1) "Suivant" else "Terminer"
                    Text(buttonText)
                }
            }
        }
    }
}

@Composable
fun ProgressIndicator(currentIndex: Int, total: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = { currentIndex / total.toFloat() },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$currentIndex / $total")
    }
}

@Composable
fun Flashcard(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}