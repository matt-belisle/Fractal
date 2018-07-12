package ui.colors

import javafx.scene.paint.Color

class TwoSchemes(private val maxIterations: Int, val inner: ColorPalette,  val outer: ColorPalette) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations == maxIterations){
            return inner.getColor(dataToColour)
        }
        return outer.getColor(dataToColour)
    }
}