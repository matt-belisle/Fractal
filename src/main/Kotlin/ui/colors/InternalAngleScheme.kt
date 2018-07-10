package ui.colors

import javafx.scene.paint.Color
import kotlin.math.atan

class InternalAngleScheme(private val maxIterations: Int) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations != maxIterations) return Color.BLACK
        val hue = atan(dataToColour.endValue.getImag() / dataToColour.endValue.getReal()) * 128
        return Color.hsb(hue,1.0,1.0)
    }

}