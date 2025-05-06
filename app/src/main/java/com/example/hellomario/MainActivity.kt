package com.example.hellomario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellomario.ui.theme.HelloMarioTheme

import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloMarioTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }

    val imageSize = 200.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
    ) {
        fun moveKirbyRandomly() {
            val maxX = screenWidth - imageSize.value.toInt()
            val maxY = screenHeight - imageSize.value.toInt()

            val randomX = (0..maxX).random().dp
            val randomY = (0..maxY).random().dp

            offsetX = randomX
            offsetY = randomY
        }
        Image(
            painter = painterResource(id = R.drawable.kirby),
            contentDescription = "Kirby pulante",
            modifier = Modifier
                .size(imageSize)
                .offset(x = offsetX, y = offsetY)
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){ moveKirbyRandomly() },
            contentScale = ContentScale.Fit
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloMarioTheme {
        Greeting()
    }
}