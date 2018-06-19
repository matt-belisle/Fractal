package UI.TornadoFX.Controllers

import Data.Complex
import Data.Mandelbrot
import UI.Colors.Color_Tables

import javafx.scene.image.WritableImage


class MakeImage(val dimension: Int) {
    //zoom will be one after int is called
    private var zoomFactor = 1.0
    private val dimensionOfImage = dimension
    private var mandelbrot  = Mandelbrot(dimension = dimensionOfImage,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0, c = Complex(-0.8, 0.156))
    private val image = WritableImage(dimensionOfImage,dimensionOfImage)



    fun writePixels(): WritableImage{
        mandelbrot.CreateFractal()
        val pixels = mandelbrot.pixels
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row -> row.forEachIndexed { indexColumn, color ->
            pixelWriter.setColor(indexRow, indexColumn, Color_Tables.colors[color])
        } }
        return image
    }

    fun zoom(x: Double, y: Double, zoomIn: Boolean){
        zoomFactor = if(zoomIn) {  4.0 } else { 1.0 / 4.0 }
        val origin = Pair(x.toInt() , y.toInt())
        mandelbrot.zoom(zoomFactor, origin = origin)
        writePixels()
    }
    fun changeConstant(c: Complex){
        mandelbrot = Mandelbrot(dimension, c = c)
        writePixels()
    }

}