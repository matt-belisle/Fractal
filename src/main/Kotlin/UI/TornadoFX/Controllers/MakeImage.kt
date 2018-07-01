package UI.TornadoFX.Controllers

import Data.Distances.GraphObjects.Complex
import Data.FractalTypes.*
import UI.Colors.*

import javafx.scene.image.WritableImage


class MakeImage(val dimension: Int) {
    private val dimensionOfImage = dimension
    //default to mandelbrot as initial fractal for app
    var fractal: Fractal = Mandelbrot(dimension = dimensionOfImage,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0)
    private val image = WritableImage(dimensionOfImage,dimensionOfImage)
    var colorPalette: ColorPalette = TwoSchemes(512, InternalAngleScheme(512), DistanceEstimation(512))


//full fractal creation if you need a new type of fractal or want to zoom in/out use this
    fun createFractal() {
        fractal.createFractal()
    }
//just recolor the current fractal allows hot swapping of coloring
    fun colorFractal(): WritableImage {
        val pixels = fractal.pixels
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row ->
            row.forEachIndexed { indexColumn, _ ->
                pixelWriter.setColor(indexRow, indexColumn, colorPalette.getColor(fractal.afterIteration[indexRow][indexColumn]
                        ?: DataToColour(Complex(0.0, 0.0), 0.0, 0)))
            }
        }
        return image
    }

    fun zoom(x: Double, y: Double, zoomIn: Boolean){
        val zoomFactor = if(zoomIn) {  4.0 } else { 0.25 }
        val origin = Pair(x.toInt() , y.toInt())
        fractal.zoom(zoomFactor, origin = origin)
        createFractal()
    }
    fun toJulia(c: Complex, power: Int){
        fractal = Julia(dimension, c = c, power = power)
        createFractal()
    }
    fun toMandelbrot(power: Int){
        fractal = Mandelbrot(dimension,power = power)
        createFractal()
    }
    fun toBurningShip(power: Int){
        fractal = BurningShip(dimension,power = power)
        createFractal()
    }

    fun toJuliaBS(c: Complex, power: Int) {
        fractal = JuliaBS(dimension, c = c, power = power)
        createFractal()
    }

}