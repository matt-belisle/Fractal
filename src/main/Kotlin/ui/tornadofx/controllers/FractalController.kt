package ui.tornadofx.controllers

import data.distances.DistanceToX
import data.distances.GraphObjects.Complex
import data.fractaltypes.*
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import tornadofx.*
import ui.colors.*

class FractalController(private val dimensionOfImage: Int = 1000) : Controller(){

    private var dimension = 2
    //default to mandelbrot as initial fractal for app
    var fractal: Fractal = Mandelbrot(dimension = dimension,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0)
    private var distances: List<DistanceToX> = emptyList()
    private var maxIterations: Int = 512
    var colorPalette: ColorPalette = TwoSchemes(maxIterations, InternalAngleScheme(maxIterations), DistanceEstimation(maxIterations))
    private var colors: List<Color> = listOf()

    var image = WritableImage(dimensionOfImage,dimensionOfImage)


    init{
            fractal.createFractal()
            image = colorFractal()
    }

   

    fun newFractal(type: FractalTypes, real: Double, imag: Double, power: Int) {
        runAsync {
            changeFractalType(type, real, imag, power)
            colorFractal()
        }ui {
            image = it
        }
    }

    private fun changeFractalType(type: FractalTypes, real: Double, imag: Double, power: Int) {
            when (type) {
                FractalTypes.JULIA -> toJulia(Complex(real, imag), power)
                FractalTypes.MANDELBROT -> toMandelbrot(power)
                FractalTypes.BURNING_SHIP -> toBurningShip(power)
                FractalTypes.JULIA_BURNING_SHIP -> toJuliaBS(Complex(real, imag), power)
            }
    }

    fun changeColoringScheme(color: ColorPalettes) {
        runAsync {
            when (color) {
                ColorPalettes.BLACK_AND_WHITE -> colorPalette = BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> colorPalette = SmoothScheme(maxIterations)
                ColorPalettes.BURNING_SHIP -> colorPalette = BurningShipSmooth(maxIterations)
                ColorPalettes.DISTANCE_ESTIMATION -> colorPalette = DistanceEstimation(maxIterations)
                ColorPalettes.DOUBLE_LOG -> colorPalette = DoubleLogScheme(maxIterations)
                ColorPalettes.INTERNAL_ANGLE -> colorPalette = InternalAngleScheme(maxIterations)
                ColorPalettes.ITERATED -> colorPalette = IteratedScheme(colors)

            }
            colorFractal()
        }ui {
            image = it
        }
    }

    fun twoColoringScheme(innerColor: ColorPalettes, outerColor: ColorPalettes) {
        runAsync {
            val innerScheme = when (innerColor) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> SmoothScheme(maxIterations)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(maxIterations)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(maxIterations)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(maxIterations)
                ColorPalettes.INTERNAL_ANGLE -> InternalAngleScheme(maxIterations)
                ColorPalettes.ITERATED -> IteratedScheme(colors)
            }
            val outerScheme = when (outerColor) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> SmoothScheme(maxIterations)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(maxIterations)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(maxIterations)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(maxIterations)
                ColorPalettes.INTERNAL_ANGLE -> InternalAngleScheme(maxIterations)
                ColorPalettes.ITERATED -> IteratedScheme(colors)
            }
            colorPalette = TwoSchemes(maxIterations, innerScheme, outerScheme)
            colorFractal()
        }ui{
            image = it
        }

    }
    fun changeColoringPalette(maxIterations: Int, colors:List<Color>){
        //plus one is as this is accessed as a max iterations count so we need one more color to access at eg [512]
        val unsafeColors = MakeGradient.makeGradient(maxIterations + 1, colors)

        //only want to alter the things if the given info was valid.
        if(unsafeColors.isNotEmpty()) {
            this.colors = unsafeColors
            //only need to recreate if the number of colours used has changed
            if(maxIterations != this.maxIterations) {
                this.maxIterations = maxIterations
                runAsync {
                    fractal.setMaxIterations(maxIterations)
                    colorFractal()
                }ui{
                    image = it
                }
                return
            }
            changeColoringScheme(color = ColorPalettes.ITERATED)
            image = colorFractal()
        }

    }
    fun setDistances(distances: List<DistanceToX> ) { this.distances = distances }
    
    
    //next is functions for creating and dealing with fractals

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
        runAsync {
            val zoomFactor = if (zoomIn) {
                4.0
            } else {
                0.25
            }
            val origin = Pair(x.toInt(), y.toInt())
            fractal.zoom(zoomFactor, origin = origin)
            createFractal()
            colorFractal()
        }ui{
            image = it
        }
    }
    fun toJulia(c: Complex, power: Int){
        fractal = Julia(dimension, c = c, power = power, distanceToX = distances)
        createFractal()
    }
    fun toMandelbrot(power: Int){
        fractal = Mandelbrot(dimension, power = power, distanceToX = distances)
        createFractal()
    }
    fun toBurningShip(power: Int){
        fractal = BurningShip(dimension, power = power, distanceToX = distances)
        createFractal()
    }

    fun toJuliaBS(c: Complex, power: Int) {
        fractal = JuliaBS(dimension, c = c, power = power, distanceToX = distances)
        createFractal()
    }

}