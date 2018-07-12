package ui.colors

import javafx.scene.paint.Color
import java.lang.Math.log


class DoubleLogScheme(private val maxIterations: Int, private val colors: List<Color>) : ColorPalette{
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations == maxIterations) return Color.BLACK
        val hue: Double = ((dataToColour.iterations - (log (log (dataToColour.endValue.magnitude())))/ log (4.0)) * 128  + 256) % 256
        return Color.hsb(hue, 1.0, 1.0)
    }

}