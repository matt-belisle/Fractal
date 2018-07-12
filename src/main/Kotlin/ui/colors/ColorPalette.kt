package ui.colors

import javafx.scene.paint.Color

interface ColorPalette {
    fun getColor(dataToColour: DataToColour): Color

}