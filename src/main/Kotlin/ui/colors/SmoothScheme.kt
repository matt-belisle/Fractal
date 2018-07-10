package ui.colors

import javafx.scene.paint.Color


class SmoothScheme(private val maxIterations: Int) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations == maxIterations) return Color.BLACK
        val hue: Double = (dataToColour.iterations.toDouble() / maxIterations.toDouble()) * 256
        return Color.hsb(hue, 1.0, 1.0)
    }

}