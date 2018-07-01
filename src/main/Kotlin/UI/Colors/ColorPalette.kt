package UI.Colors

import javafx.scene.paint.Color

interface ColorPalette {
    fun getColor(dataToColour: DataToColour): Color
}