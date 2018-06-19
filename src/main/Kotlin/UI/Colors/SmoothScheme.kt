package UI.Colors

import Data.Complex
import javafx.scene.paint.Color


class SmoothScheme(private val maxIterations: Int) : ColorPalette {
    override fun getColor(i: Int, value: Complex): Color {
        if(i == maxIterations) return Color.BLACK
        val hue: Double = (i.toDouble() / maxIterations.toDouble()) * 256
        return Color.hsb(hue, 1.0, 1.0)
    }

}