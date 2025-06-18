package com.example.tp_flashcard.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
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
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp_flashcard.viewModel.FlashCardViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel,
    navController: NavController
) {
    val uiState by viewModel.questions.collectAsState()


    // Navigate back when revision is finished
    LaunchedEffect(uiState.revisionFinished) {
        if (uiState.revisionFinished) {
            navController.popBackStack()
        }
    }

    if (uiState.cardList.isNotEmpty()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressIndicator(
                    currentIndex = uiState.cardIndex + 1,
                    total = uiState.cardList.size
                )

                // Animated transition between cards
                AnimatedContent(
                    targetState = uiState.cardIndex,
                    transitionSpec = {
                        (slideInHorizontally(
                            animationSpec = tween(350),
                            initialOffsetX = { fullWidth -> fullWidth }
                        ) + fadeIn(animationSpec = tween(350))) togetherWith
                                (slideOutHorizontally(
                                    animationSpec = tween(350),
                                    targetOffsetX = { fullWidth -> -fullWidth }
                                ) + fadeOut(animationSpec = tween(350)))
                    }
                ) { index ->
                    val animatedCard = uiState.cardList[index]
                    Flashcard3D(
                        question = animatedCard.question,
                        answer = animatedCard.answer,
                        resetKey = index // triggers reset on card change
                    )
                }

                Button(
                    onClick = { viewModel.nextQuestion(uiState.cardIndex) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 8.dp)
                        .height(56.dp)
                ) {
                    val buttonText = if (uiState.cardIndex < uiState.cardList.size - 1) "Suivant" else "Terminer"
                    Text(
                        buttonText,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressIndicator(currentIndex: Int, total: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = { currentIndex / total.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$currentIndex / $total",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Flashcard3D(
    question: String,
    answer: String,
    resetKey: Int
) {
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current.density
    var isFront by remember { mutableStateOf(true) }

    // Reset rotation and face on card change
    LaunchedEffect(resetKey) {
        rotation.snapTo(0f)
        isFront = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 8 * density
            }
            .clickable {
                scope.launch {
                    val target = if (isFront) 180f else 0f
                    rotation.animateTo(
                        target,
                        animationSpec = tween(durationMillis = 400, easing = LinearEasing)
                    )
                    isFront = !isFront
                }
            },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            val displayText = if (rotation.value <= 90f) question else answer
            Text(
                text = displayText,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .graphicsLayer {
                        // Mirror the back face so text is not reversed
                        if (rotation.value > 90f) {
                            rotationY = 180f
                        }
                    }
            )
        }
    }
}