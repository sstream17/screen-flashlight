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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.stream_suite.flashlight.ui.icons.MaxBrightness
import com.stream_suite.flashlight.ui.icons.MinBrightness
import com.stream_suite.flashlight.ui.theme.FlashlightTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        setContent {
            Content()
        }
    }
}

@Composable
fun Content(activity: Activity = LocalContext.current.findActivity()) {
    var brightness by remember { mutableFloatStateOf(DEFAULT_BRIGHTNESS) }
    val window = activity.window
    FlashlightTheme {
        Surface(color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedButton(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(8.dp),
                        onClick = {
                            setBrightness(
                                window, MIN_BRIGHTNESS,
                            ) { brightness = it }
                        },
                    ) {
                        Icon(
                            imageVector = MinBrightness,
                            contentDescription = "Minimum brightness",
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Slider(
                        modifier = Modifier.weight(1f),
                        value = brightness,
                        valueRange = MIN_BRIGHTNESS..MAX_BRIGHTNESS,
                        onValueChange = { new ->
                            setBrightness(
                                window, new,
                            ) { brightness = new }
                        },
                    )
                    Spacer(Modifier.width(16.dp))
                    OutlinedButton(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(8.dp),
                        onClick = {
                            setBrightness(
                                window, MAX_BRIGHTNESS,
                            ) { brightness = it }
                        },
                    ) {
                        Icon(
                            imageVector = MaxBrightness,
                            contentDescription = "Maximum brightness",
                        )
                    }
                }
                OutlinedButton(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    enabled = brightness != DEFAULT_BRIGHTNESS,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    onClick = {
                        setBrightness(
                            window, DEFAULT_BRIGHTNESS,
                        ) { brightness = it }
                    }) {
                    Text("Restore Previous Brightness")
                }
            }
        }
    }
}

fun setBrightness(
    window: Window,
    newValue: Float,
    setBrightness: ((Float) -> Unit)? = null,
) {
    setBrightness?.invoke(newValue)
    window.attributes = window.attributes.apply { screenBrightness = newValue }
}

internal fun Context.findActivity(): Activity {
    val context = this
    while (context is ContextWrapper) {
        (context as? Activity)?.let { return it }
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