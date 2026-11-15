package com.stream_suite.flashlight

import androidx.core.math.MathUtils
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Utility class providing functions to convert between linear and perceptual gamma space.
 *
 * Internally, this implements the Hybrid Log Gamma electro-optical transfer function, which is a
 * slight improvement to the typical gamma transfer function for displays whose max brightness
 * exceeds the 120 nit reference point, but doesn't set a specific reference brightness like the PQ
 * function does.
 *
 * Note that this transfer function is only valid if the display's backlight value is a linear
 * control. If it's calibrated to be something non-linear, then a different transfer function
 * should be used.
 *
 * Note: This code is based on the same class in the com.android.settingslib.display package.
 */
object BrightnessUtils {
    // Hybrid Log Gamma constant values
    private const val R = 0.5f
    private const val A = 0.17883277f
    private const val B = 0.28466892f
    private const val C = 0.55991073f

    /**
     * A function for converting from the gamma space into the linear space.
     *
     * @param value The value in the gamma space [0 .. 1.0]
     * @return The corresponding value in the linear space [0 .. 1.0].
     */
    fun convertGammaToLinear(value: Float): Float {
        val ret: Float = if (value <= R) {
            (value / R).pow(2)
        } else {
            exp((value - C) / A) + B
        }
        // HLG is normalized to the range [0, 12], ensure that value is within that range,
        // it shouldn't be out of bounds.
        val normalizedRet: Float = MathUtils.clamp(ret, 0f, 12f)
        // Re-normalize to the range [0, 1]
        // in order to derive the correct setting value.
        return normalizedRet / 12
    }

    /**
     * A function for converting from the linear space into the gamma space.
     *
     * @param value The value in linear space [0 .. 1.0]
     * @return The corresponding value in gamma space [0 .. 1.0]
     */
    fun convertLinearToGamma(value: Float): Float {
        // For some reason, HLG normalizes to the range [0, 12] rather than [0, 1]
        val normalizedVal = value * 12
        val ret: Float = if (normalizedVal <= 1f) {
            sqrt(normalizedVal) * R
        } else {
            A * ln(normalizedVal - B) + C
        }
        return ret
    }
}