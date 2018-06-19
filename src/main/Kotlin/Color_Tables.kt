import javafx.scene.paint.Color

class Color_Tables {
    companion object {
        val colors = arrayOfNulls<Color>(513)
        init {
            colors.forEachIndexed { index, i -> colors[index] = Color.rgb(index%256,index%32,index%64) }
            colors[512] = Color.WHITE
            colors[511] = Color.WHITE
        }
    }
}