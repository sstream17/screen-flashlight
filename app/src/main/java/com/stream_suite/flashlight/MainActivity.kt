package com.stream_suite.flashlight

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun Content(activity: Activity = LocalContext.current.findActivity()) {
    val (shouldRestore, setShouldRestore) = remember { mutableStateOf(false) }
    val window = activity.window
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
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    onClick = {
                        setBrightness(
                            window,
                            1F,
                            setShouldRestore
                        )
                    }
                ) {
                    Text("Max Brightness")
                }
                if (shouldRestore) {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        onClick = {
                            setBrightness(
                                window,
                                -1F,
                                setShouldRestore
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

fun setBrightness(
    window: Window,
    newValue: Float,
    setShouldRestore: (Boolean) -> Unit,
) {
    setShouldRestore(newValue == 1F)
    val lp = window.attributes
    lp.screenBrightness = newValue
    window.attributes = lp
}

internal fun Context.findActivity(): Activity {
    val context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
    }

    throw IllegalStateException("Cannot find activity for given context")
}

// Preview should look the same in both dark and light mode
@Preview(showBackground = true, name = "Light mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark mode")
@Composable
fun DefaultPreview() {
    Content(Activity())
}