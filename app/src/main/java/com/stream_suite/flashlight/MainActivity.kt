package com.stream_suite.flashlight

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.WindowCompat
import com.stream_suite.flashlight.ui.theme.FlashlightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val (previousBrightness, setPreviousBrightness) = remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
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
                    onClick = { setBrightness(context, 255, setPreviousBrightness) }
                ) {
                    Text("Max Brightness")
                }
                if (previousBrightness != null) {
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                        onClick = {
                            setBrightness(
                                context,
                                previousBrightness,
                                setPreviousBrightness,
                                true
                            )
                        }
                    ) {
                        Text("Restore Previous Brightness")
                    }
                }
            }
        }
    }
}

fun requestPermissions(context: Context) {
    val intent = Intent()
    intent.action = Settings.ACTION_MANAGE_WRITE_SETTINGS
    intent.data = Uri.parse("package:${context.packageName}")
    startActivity(context, intent, null)
}

fun setBrightness(
    context: Context,
    newValue: Int,
    setPreviousBrightness: (Int?) -> Unit,
    shouldClear: Boolean = false
) {
    if (Settings.System.canWrite(context)) {
        val contentResolver = context.contentResolver
        val previousBrightness = when (shouldClear) {
            true -> null
            else -> Settings.System.getInt(
                contentResolver, Settings.System.SCREEN_BRIGHTNESS
            )
        }
        setPreviousBrightness(previousBrightness)
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, newValue)
    } else {
        requestPermissions(context)
    }
}

// Preview should look the same in both dark and light mode
@Preview(showBackground = true, name = "Light mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark mode")
@Composable
fun DefaultPreview() {
    Content()
}