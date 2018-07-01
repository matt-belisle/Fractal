package UI.Colors

import javafx.scene.paint.Color
import kotlin.math.ln
import kotlin.math.log2


class BurningShipSmooth(private val maxIterations: Int) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations == maxIterations) return Color.BLACK
        val hue: Double = (dataToColour.iterations - log2(ln(dataToColour.endValue.magnitude())/ln(maxIterations.toFloat())))
        return Color.hsb(hue, 1.0, 1.0)
    }

}