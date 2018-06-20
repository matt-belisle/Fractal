package UI.TornadoFX.Controllers

import Data.Complex
import Data.FractalTypes.BurningShip
import Data.FractalTypes.Fractal
import Data.FractalTypes.Julia
import Data.FractalTypes.Mandelbrot
import UI.Colors.BlackAndWhiteScheme
import UI.Colors.BurningShipSmooth
import UI.Colors.ColorPalette
import UI.Colors.DoubleLogScheme

import javafx.scene.image.WritableImage


class MakeImage(val dimension: Int) {
    private val dimensionOfImage = dimension
    //default to mandelbrot as initial fractal for app
    var fractal: Fractal = Mandelbrot(dimension = dimensionOfImage,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0)
    private val image = WritableImage(dimensionOfImage,dimensionOfImage)
    public var colorPalette: ColorPalette = BurningShipSmooth(512)



    fun writePixels(): WritableImage{
        fractal.createFractal()
        val pixels = fractal.pixels
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row -> row.forEachIndexed { indexColumn, color ->
            pixelWriter.setColor(indexRow, indexColumn, colorPalette.getColor(color, fractal.afterIteration[indexRow][indexColumn] ?: Complex(0.0,0.0)))
        } }
        return image
    }

    fun zoom(x: Double, y: Double, zoomIn: Boolean){
        val zoomFactor = if(zoomIn) {  4.0 } else { 0.25 }
        val origin = Pair(x.toInt() , y.toInt())
        fractal.zoom(zoomFactor, origin = origin)
        writePixels()
    }
    fun toJulia(c: Complex, power: Int){
        fractal = Julia(dimension, c = c, power = power)
        writePixels()
    }
    fun toMandelbrot(power: Int){
        fractal = Mandelbrot(dimension,power = power)
        writePixels()
    }
    fun toBurningShip(power: Int){
        fractal = BurningShip(dimension,power = power)
        writePixels()
    }

}