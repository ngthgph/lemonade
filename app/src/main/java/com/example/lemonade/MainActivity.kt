package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonTextAndImage()
                }
            }
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadePreview (){
    LemonTextAndImage()
}

@Composable
fun LemonTextAndImage() {

    lateinit var image: Painter
    lateinit var description: String
    lateinit var select: String

    var step by remember { mutableStateOf(1) }

    var randomNum by remember { mutableStateOf(2) }
    var times by remember { mutableStateOf(1) }

    when(step) {
        1 -> {
            image = painterResource(id = R.drawable.lemon_tree)
            description = stringResource(id = R.string.lemon_tree)
            select = stringResource(id = R.string.select_tree)
        }
        2 -> {
            image = painterResource(id = R.drawable.lemon_squeeze)
            description = stringResource(id = R.string.lemon)
            select = stringResource(id = R.string.select_lemon)
        }
        3 -> {
        image = painterResource(id = R.drawable.lemon_drink)
        description = stringResource(id = R.string.glass_of_lemonade)
        select = stringResource(id = R.string.select_lemonade)
        }
        4 -> {
        image = painterResource(id = R.drawable.lemon_restart)
        description = stringResource(id = R.string.empty_glass)
        select = stringResource(id = R.string.select_glass)
        }
    }

    Box {
        // Add the yellow title bar with App name
        Row {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = Color(0xffffee58))
                    .weight(1f)
                    .padding(12.dp)
            )
        }

        // Add the button with image and the guideline in the center of screen
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = image,
                contentDescription = description,
                modifier = Modifier
                    .wrapContentSize()
                    .background(shape = RoundedCornerShape(32.dp), color = Color(0xffc8e6c9))
                    .size(180.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .clickable {
                        step = when (step) {
                            4 -> 1
                            2 -> {
                                when(times) {
                                    1 -> {
                                        randomNum = (2..4).random()
                                        times++
                                        2
                                    }
                                    else -> if (times == randomNum) {
                                        times = 1
                                        3
                                    } else {
                                        times++
                                        2
                                    }
                                }
                            }
                            else -> step + 1
                        }
                    }
                    .padding(32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add the text below it to guide users
            Text(
                text = select,
                fontSize = 15.sp
            )
        }
    }
}
