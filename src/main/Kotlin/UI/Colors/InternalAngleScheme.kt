package UI.Colors

import Data.Complex
import javafx.scene.paint.Color
import kotlin.math.atan

class InternalAngleScheme(private val maxIterations: Int) : ColorPalette {
    override fun getColor(i: Int, value: Complex): Color {
        if(i != maxIterations) return Color.BLACK
        val hue = atan(value.getImag() / value.getReal()) * 128
        println(hue)
        return Color.hsb(hue,1.0,1.0)
    }

}