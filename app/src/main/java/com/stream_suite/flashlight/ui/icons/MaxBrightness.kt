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
val MaxBrightness: ImageVector
  get() {
    if (_max_brightness != null) {
      return _max_brightness!!
    }
    _max_brightness =
      ImageVector.Builder(
          name = "clear_day",
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
            moveTo(11f, 5f)
            verticalLineTo(1f)
            horizontalLineToRelative(2f)
            verticalLineTo(5f)
            horizontalLineTo(11f)
            close()
            moveToRelative(6.65f, 2.75f)
            lineTo(16.28f, 6.38f)
            lineTo(19.08f, 3.5f)
            lineToRelative(1.4f, 1.43f)
            lineTo(17.65f, 7.75f)
            close()
            moveTo(19f, 13f)
            verticalLineTo(11f)
            horizontalLineToRelative(4f)
            verticalLineToRelative(2f)
            horizontalLineTo(19f)
            close()
            moveTo(11f, 23f)
            verticalLineTo(19f)
            horizontalLineToRelative(2f)
            verticalLineToRelative(4f)
            horizontalLineTo(11f)
            close()
            moveTo(6.35f, 7.7f)
            lineTo(3.5f, 4.93f)
            lineTo(4.93f, 3.52f)
            lineTo(7.75f, 6.35f)
            lineTo(6.35f, 7.7f)
            close()
            moveToRelative(12.7f, 12.8f)
            lineTo(16.28f, 17.63f)
            lineToRelative(1.35f, -1.35f)
            lineToRelative(2.85f, 2.75f)
            lineTo(19.05f, 20.5f)
            close()
            moveTo(1f, 13f)
            verticalLineTo(11f)
            horizontalLineTo(5f)
            verticalLineToRelative(2f)
            horizontalLineTo(1f)
            close()
            moveToRelative(3.93f, 7.5f)
            lineTo(3.53f, 19.08f)
            lineToRelative(2.8f, -2.8f)
            lineToRelative(0.73f, 0.68f)
            lineToRelative(0.72f, 0.7f)
            lineTo(4.93f, 20.5f)
            close()
            moveTo(7.75f, 16.25f)
            quadTo(6f, 14.5f, 6f, 12f)
            reflectiveQuadTo(7.75f, 7.75f)
            reflectiveQuadTo(12f, 6f)
            reflectiveQuadToRelative(4.25f, 1.75f)
            reflectiveQuadTo(18f, 12f)
            reflectiveQuadToRelative(-1.75f, 4.25f)
            reflectiveQuadTo(12f, 18f)
            reflectiveQuadTo(7.75f, 16.25f)
            close()
            moveToRelative(7.08f, -1.43f)
            quadTo(16f, 13.65f, 16f, 12f)
            reflectiveQuadTo(14.83f, 9.17f)
            reflectiveQuadTo(12f, 8f)
            reflectiveQuadTo(9.18f, 9.17f)
            reflectiveQuadTo(8f, 12f)
            reflectiveQuadToRelative(1.18f, 2.82f)
            reflectiveQuadTo(12f, 16f)
            reflectiveQuadToRelative(2.83f, -1.18f)
            close()
            moveTo(12f, 12f)
            close()
          }
        }
        .build()
    return _max_brightness!!
  }

private var _max_brightness: ImageVector? = null
