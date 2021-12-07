package com.stream_suite.flashlight

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stream_suite.flashlight.ui.theme.FlashlightTheme

class MainActivity : ComponentActivity() {
    private fun requestPermissions() {
        val intent = Intent()
        intent.action = Settings.ACTION_MANAGE_WRITE_SETTINGS
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setMaxBrightness() {
        if (Settings.System.canWrite(this)) {
            Settings.System.putInt(this.contentResolver, Settings.System.SCREEN_BRIGHTNESS, 255)
        }
        else {
            requestPermissions()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(setMaxBrightness = { setMaxBrightness() })
        }
    }
}

@Composable
fun Content(setMaxBrightness: () -> Unit) {
    FlashlightTheme {
        Surface(color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    onClick = setMaxBrightness
                ) {
                    Text("Max Brightness")
                }
            }
        }
    }
}

// Preview should look the same in both dark and light mode
@Preview(showBackground = true, name = "Light mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark mode")
@Composable
fun DefaultPreview() {
    Content(setMaxBrightness = {})
}