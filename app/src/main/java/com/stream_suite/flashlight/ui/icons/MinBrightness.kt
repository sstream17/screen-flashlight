package com.stream_suite.flashlight.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val MinBrightness: ImageVector
  get() {
    if (_min_brightness != null) {
      return _min_brightness!!
    }
    _min_brightness =
      ImageVector.Builder(
          name = "backlight_high_off",
          defaultWidth = 24.dp,
          defaultHeight = 24.dp,
          viewportWidth = 24f,
          viewportHeight = 24f,
        )
        .apply {
          path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero,
          ) {
            moveTo(1f, 15f)
            verticalLineTo(13f)
            horizontalLineTo(5f)
            verticalLineToRelative(2f)
            horizontalLineTo(1f)
            close()
            moveTo(11f, 7f)
            verticalLineTo(2f)
            horizontalLineToRelative(2f)
            verticalLineTo(7f)
            horizontalLineTo(11f)
            close()
            moveToRelative(6.65f, 2.75f)
            lineToRelative(-1.4f, -1.4f)
            lineTo(19.08f, 5.52f)
            lineToRelative(1.43f, 1.4f)
            lineTo(17.65f, 9.75f)
            close()
            moveTo(19f, 15f)
            verticalLineTo(13f)
            horizontalLineToRelative(4f)
            verticalLineToRelative(2f)
            horizontalLineTo(19f)
            close()
            moveToRelative(0.78f, 7.63f)
            lineTo(15.15f, 18f)
            horizontalLineTo(7f)
            verticalLineTo(15f)
            horizontalLineToRelative(5.15f)
            lineTo(1.38f, 4.22f)
            lineTo(2.8f, 2.8f)
            lineTo(21.2f, 21.2f)
            lineToRelative(-1.43f, 1.43f)
            close()
          }
        }
        .build()
    return _min_brightness!!
  }

private var _min_brightness: ImageVector? = null
