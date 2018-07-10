package ui.colors

import javafx.scene.paint.Color

class IteratedScheme(private val colors: List<Color>): ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        return colors[dataToColour.iterations]
    }
}