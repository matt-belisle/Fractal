package UI.TornadoFX.Controllers

import Data.Complex
import Data.Mandelbrot
import UI.Colors.ColorPalette
import UI.Colors.InternalAngleScheme
import UI.Colors.SmoothScheme

import javafx.scene.image.WritableImage


class MakeImage(val dimension: Int) {
    private val dimensionOfImage = dimension
    private var mandelbrot  = Mandelbrot(dimension = dimensionOfImage,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0, c = Complex(-0.61803398875, 0.0))
    private val image = WritableImage(dimensionOfImage,dimensionOfImage)
    public var colorPalette: ColorPalette = SmoothScheme(512)



    fun writePixels(): WritableImage{
        mandelbrot.CreateFractal()
        val pixels = mandelbrot.pixels
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row -> row.forEachIndexed { indexColumn, color ->
            pixelWriter.setColor(indexRow, indexColumn, colorPalette.getColor(color, mandelbrot.afterIteration[indexRow][indexColumn] ?: Complex(0.0,0.0)))
        } }
        return image
    }

    fun zoom(x: Double, y: Double, zoomIn: Boolean){
        val zoomFactor = if(zoomIn) {  4.0 } else { 0.25 }
        val origin = Pair(x.toInt() , y.toInt())
        mandelbrot.zoom(zoomFactor, origin = origin)
        writePixels()
    }
    fun changeConstant(c: Complex){
        mandelbrot = Mandelbrot(dimension, c = c)
        writePixels()
    }
    fun toMandelbrot(){
        mandelbrot = Mandelbrot(dimension)
        writePixels()
    }

}