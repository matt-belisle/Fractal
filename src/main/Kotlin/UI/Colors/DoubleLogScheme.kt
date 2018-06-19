package UI.Colors

import Data.Complex
import javafx.scene.paint.Color
import java.lang.Math.log


class DoubleLogScheme(private val maxIterations: Int) : ColorPalette {
    override fun getColor(i: Int, value: Complex): Color {
        if(i == maxIterations) return Color.BLACK
        val hue: Double = (i - (log (log (value.magnitude())))/ log (2.0)) * 128 % 256
        return Color.hsb(hue, 1.0, 1.0)
    }

}