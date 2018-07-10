package ui.colors

import javafx.scene.paint.Color

class TwoSchemes(private var maxIterations: Int, private var inner: ColorPalette, private var outer: ColorPalette) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.iterations == maxIterations){
            return inner.getColor(dataToColour)
        }
        return outer.getColor(dataToColour)
    }
}