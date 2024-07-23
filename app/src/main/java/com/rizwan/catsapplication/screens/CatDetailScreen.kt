package com.rizwan.catsapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rizwan.catsapplication.models.Cat

@Composable
fun CatDetailScreen (
    navHostController: NavHostController
) {
    val data =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Cat>("data") ?: Cat()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Header(isDarkBackground = false)
            CatItem(data, isDarkBackground = false)
            Text(
                text = "Lifespan : ${data.lifeSpan}",
                modifier = Modifier.fillMaxWidth().padding(all = 20.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = data.description,
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = 20.dp,
                ),
                textAlign = TextAlign.Center
            )
        }

    }
}