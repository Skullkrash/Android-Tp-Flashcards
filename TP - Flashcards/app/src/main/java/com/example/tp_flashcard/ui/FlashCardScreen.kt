package com.example.tp_flashcard.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp_flashcard.viewModel.FlashCardViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel
) {
    val uiState = viewModel.questions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = uiState.value.cardList[0].question,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.tp_flashcard.viewModel.FlashCardViewModel
//
//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun FlashCardScreen(
//    viewModel: FlashCardViewModel
//) {
//    val uiState = viewModel.questions
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        ProgressBar(
//            current = uiState.value.cardIndex + 1,
//            total = uiState.value.cardList.size
//        )
//
//        FlashCardQuestion(
//            question = uiState.value.cardList[uiState.value.cardIndex].question
//        )
//
//        Button(
//            onClick = { viewModel.nextQuestion(uiState.value.cardIndex) },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Suivant")
//        }
//    }
//}
//
//@Composable
//fun ProgressBar(current: Int, total: Int) {
//    Column {
//        LinearProgressIndicator(progress = current / total.toFloat())
//        Text("$current / $total")
//    }
//}
//
//@Composable
//fun FlashCardQuestion(question: String) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 32.dp)
//    ) {
//        Text(
//            text = question,
//            modifier = Modifier.padding(24.dp),
//            style = MaterialTheme.typography.headlineSmall
//        )
//    }
//}