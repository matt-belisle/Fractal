package ui.colors

import javafx.scene.paint.Color

class BlackAndWhiteScheme(private var maxIterations: Int) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        return if (dataToColour.iterations == maxIterations) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

}