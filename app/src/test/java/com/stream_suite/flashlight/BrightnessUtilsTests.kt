package com.stream_suite.flashlight

import com.stream_suite.flashlight.BrightnessUtils.convertGammaToLinear
import org.junit.Test

import org.junit.Assert.*

class BrightnessUtilsTests {
    @Test
    fun `convertGammaToLinear 1f returns 1f`() {
        val linear = convertGammaToLinear(1f)
        assertEquals(1f, linear)
    }

    @Test
    fun `convertGammaToLinear -1f doesn't return -1f`() {
        val linear = convertGammaToLinear(-1f)
        assertNotEquals(-1f, linear)
    }
}