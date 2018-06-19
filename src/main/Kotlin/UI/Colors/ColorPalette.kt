package UI.Colors

import Data.Complex
import javafx.scene.paint.Color

interface ColorPalette {
    fun getColor(i:Int, value: Complex): Color
}