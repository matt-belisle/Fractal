package UI.Colors

import Data.Complex
import javafx.scene.paint.Color
import kotlin.math.ln
import kotlin.math.log2


class BurningShipSmooth(private val maxIterations: Int) : ColorPalette {
    override fun getColor(i: Int, value: Complex): Color {
        if(i == maxIterations) return Color.BLACK
        val hue: Double = (i - log2(ln(value.magnitude())/ln(maxIterations.toFloat())))
        return Color.hsb(hue, 1.0, 1.0)
    }

}