package UI.Colors

import Data.Complex
import javafx.scene.paint.Color

class BlackAndWhiteScheme(private var maxIterations: Int) : ColorPalette {
    override fun getColor(i: Int, value: Complex): Color {
        return if (i == maxIterations) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

}