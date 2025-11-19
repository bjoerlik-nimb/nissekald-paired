package com.nimbit.nissekald_paired.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.nimbit.nissekald_paired.presentation.theme.NissekaldpairedTheme

class WatchMainActivity : ComponentActivity() {

    private var model: MessageViewModel = MessageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        BatteryHelper.setContext(applicationContext);

        val message = intent.extras?.getString("message");

        if (message != null) {
            model.updateMessage(message);
        }
        WatchJavaHelper.context = applicationContext;
        WatchJavaHelper.messageViewModel = model;

        setTheme(android.R.style.Theme_DeviceDefault)


        setContent {
            WearApp(model)
        }
    }

    override fun onResume() {
        super.onResume()
        // model.updateBattery()
    }

    override fun onPostResume() {
        super.onPostResume()
    }
}

@Composable
fun WearApp(messageViewModel: MessageViewModel) {
    val messageUiState by messageViewModel.uiMsgState.collectAsState()
    val battery by messageViewModel.uiBatteryState.collectAsState()
    var support = WatchJavaHelper();
    val listState = rememberLazyListState()
    NissekaldpairedTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                if (messageUiState.isNotEmpty()) {
                    Button(
                        onClick = {
                            Log.d("nisseur", "on click");
                            support.stop();
                            messageViewModel.resetMessage();

                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(132.dp),
                        colors = ButtonDefaults.primaryButtonColors(
                            backgroundColor = Color(
                                0xFF46a0ff
                            )
                        ),
                        enabled = true,
                    ) {
                        Text(
                            "Kommer!",
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(255, 255, 255)
                            )
                        )
                    }
                } else {
                    Text(text = "Intet nyt :)", style = TextStyle(
                        color = Color(0xFF46a0ff),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold)
                    )
                    Text(text = battery, style = TextStyle(
                        color = Color(0xFFFFFFFF),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}


@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp(MessageViewModel())
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun MessagePreview() {
    val model = MessageViewModel();
    model.updateMessage("preview");
    WearApp(model)
}