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
    fun `convertGammaToLinear 0_01f doesn't return 0_01f`() {
        val linear = convertGammaToLinear(0.01f)
        assertNotEquals(0.01f, linear)
    }

    @Test
    fun `convertGammaToLinear -1f doesn't return -1f`() {
        val linear = convertGammaToLinear(-1f)
        assertNotEquals(-1f, linear)
    }
}