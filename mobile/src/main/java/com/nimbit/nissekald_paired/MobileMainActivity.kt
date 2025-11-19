package com.nimbit.nissekald_paired

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MobileMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d("nissekald", "context is null? " + (applicationContext == null));
        MobileJavaHelper.context = applicationContext;

        setContent {
            TestView()
        }
    }
}

@Composable
fun TestView() {
    Column(
        Modifier.background(Color(0xFF143250)).fillMaxWidth().fillMaxHeight().safeContentPadding()
    ) {
        Column(
            Modifier.safeContentPadding()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )  {
                Text(text="Nissekald",
                    // Modifier.background(Color.Red),
                    color = Color(0xFFff7928),
                    fontSize = 64.sp
                )
            }
            Row(
                Modifier.fillMaxWidth().fillMaxHeight().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Button(
                    onClick = {
                        MobileJavaHelper().sendMessage();
                    },
                    modifier = Modifier.size(350.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF46a0ff))
                ) {
                    Text(
                        text="Kom så!",
                        fontSize = 48.sp,
                        color = Color.White,
                    )
                }
            }
        }

    }

}

@Preview()
@Composable
fun DefaultPreview() {
    TestView()
}