package UI.Colors

import javafx.scene.paint.Color

class IteratedScheme: ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        //will never be null as instantiated in init
        return colors[dataToColour.iterations]!!
    }

    companion object {
       private val colors = arrayOfNulls<Color>(513)
        init {
            colors.forEachIndexed { index, _ -> colors[index] = Color.rgb(index%256,index%32,index%64) }
            colors[512] = Color.WHITE
        }
    }
}