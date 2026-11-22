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
import com.stream_suite.flashlight.BrightnessUtils.convertGammaToLinear
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
    var brightnessGamma by remember { mutableFloatStateOf(DEFAULT_BRIGHTNESS_LINEAR) }
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
                            setWindowBrightness(
                                window, convertGammaToLinear(MIN_BRIGHTNESS_GAMMA),
                            ) { brightnessGamma = MIN_BRIGHTNESS_GAMMA }
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
                        value = brightnessGamma,
                        valueRange = MIN_BRIGHTNESS_GAMMA..MAX_BRIGHTNESS_GAMMA,
                        onValueChange = { newValue ->
                            setWindowBrightness(
                                window, convertGammaToLinear(newValue),
                            ) { brightnessGamma = newValue }
                        },
                    )
                    Spacer(Modifier.width(16.dp))
                    OutlinedButton(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(8.dp),
                        onClick = {
                            setWindowBrightness(
                                window, MAX_BRIGHTNESS_LINEAR,
                            ) { brightnessGamma = MAX_BRIGHTNESS_GAMMA }
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
                    enabled = brightnessGamma != DEFAULT_BRIGHTNESS_LINEAR,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    onClick = {
                        setWindowBrightness(
                            window, DEFAULT_BRIGHTNESS_LINEAR,
                        ) { brightnessGamma = it }
                    }) {
                    Text("Restore Previous Brightness")
                }
            }
        }
    }
}

/**
 * Set the [android.view.WindowManager.LayoutParams.screenBrightness] using [newLinearValue] in the
 * linear space
 */
fun setWindowBrightness(
    window: Window,
    newLinearValue: Float,
    setSliderPosition: ((Float) -> Unit)? = null,
) {
    setSliderPosition?.invoke(newLinearValue)
    window.attributes = window.attributes.apply { screenBrightness = newLinearValue }
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