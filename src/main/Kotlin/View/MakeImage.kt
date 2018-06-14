package View

import Data.Mandelbrot
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color


class MakeImage {
    private val mandelbrot = Mandelbrot()
    private val image = WritableImage(500,500)


    fun writePixels(): WritableImage{
        mandelbrot.CreateFractal()
        val pixels = mandelbrot.pixels
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row -> row.forEachIndexed { indexColumn, color ->
            var pixelColor: Color = Color.WHITE
            if(color == 127){
                pixelColor = Color.BLACK
            }
            pixelWriter.setColor(indexRow, indexColumn, pixelColor)
        } }
        return image
    }
}