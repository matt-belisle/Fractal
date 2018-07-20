package ui.tornadofx.controllers

import data.distances.DistanceToX
import data.distances.GraphObjects.Complex
import data.fractaltypes.*
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import tornadofx.*
import ui.colors.*
import java.awt.image.RenderedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

class FractalController(private val dimension: Int = 1000) : Controller(){


    //default to mandelbrot as initial fractal for app
    var fractal: Fractal = Mandelbrot(dimension = dimension,
            startX = -2.0, endX = 2.0, startY = 2.0, endY = -2.0)
    private var distances: List<DistanceToX> = emptyList()
    private var maxIterations: Int = 512
    private var colors: List<Color> = MakeGradient.makeGradient(maxIterations + 1,listOf(Color.BLACK, Color.WHITE))
    var colorPalette: ColorPalette = TwoSchemes(maxIterations, InternalAngleScheme(maxIterations), DistanceEstimation(maxIterations, colors))
    //if second is null then it is a single
    private var typeOfFractal: Pair<ColorPalettes, ColorPalettes?> = Pair(ColorPalettes.INTERNAL_ANGLE, ColorPalettes.DISTANCE_ESTIMATION)


    var image = WritableImage(dimension,dimension)


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
        typeOfFractal = Pair(color, null)
        runAsync {
            when (typeOfFractal.first) {
                ColorPalettes.BLACK_AND_WHITE -> colorPalette = BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> colorPalette = SmoothScheme(maxIterations, colors)
                ColorPalettes.BURNING_SHIP -> colorPalette = BurningShipSmooth(maxIterations, colors)
                ColorPalettes.DISTANCE_ESTIMATION -> colorPalette = DistanceEstimation(maxIterations, colors)
                ColorPalettes.DOUBLE_LOG -> colorPalette = DoubleLogScheme(maxIterations, colors)
                ColorPalettes.INTERNAL_ANGLE -> colorPalette = InternalAngleScheme(maxIterations)
                ColorPalettes.ITERATED -> colorPalette = IteratedScheme(colors)

            }
            colorFractal()
        }ui {
            image = it
        }
    }

    fun twoColoringScheme(innerColor: ColorPalettes, outerColor: ColorPalettes) {
        typeOfFractal = Pair(innerColor, outerColor)
        runAsync {
            val innerScheme = when (typeOfFractal.first) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> SmoothScheme(maxIterations, colors)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(maxIterations, colors)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(maxIterations, colors)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(maxIterations, colors)
                ColorPalettes.INTERNAL_ANGLE -> InternalAngleScheme(maxIterations)
                ColorPalettes.ITERATED -> IteratedScheme(colors)
            }
            //second will never be null as function doesnt take nulls and is immediately set to param
            val outerScheme = when (typeOfFractal.second!!) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(maxIterations)
                ColorPalettes.SMOOTH -> SmoothScheme(maxIterations, colors)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(maxIterations, colors)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(maxIterations, colors)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(maxIterations, colors)
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
                internalColorPalleteChange()
                runAsync {
                    fractal.setMaxIterations(maxIterations)
                    colorFractal()
                }ui{
                    image = it
                }
                return
            }
            internalColorPalleteChange()
            image = colorFractal()
        }

    }

    private fun internalColorPalleteChange() {
        if (typeOfFractal.second == null) {
            changeColoringScheme(typeOfFractal.first)
        } else {
            twoColoringScheme(typeOfFractal.first, typeOfFractal.second!!)
        }
    }

    fun setDistances(distances: List<DistanceToX> ) { this.distances = distances }
    
    
    //next is functions for creating and dealing with fractals

    //full fractal creation if you need a new type of fractal or want to zoom in/out use this
    private fun createFractal() {
        fractal.createFractal()
    }
    //just recolor the current fractal allows hot swapping of coloring
    private fun colorFractal(): WritableImage {
        val pixels = fractal.afterIteration
        val pixelWriter = image.pixelWriter!!
        pixels.forEachIndexed { indexRow, row ->
            row.forEachIndexed { indexColumn, data ->
                pixelWriter.setColor(indexRow, indexColumn, colorPalette.getColor(data
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
    private fun toJulia(c: Complex, power: Int){
        fractal = Julia(dimension, c = c, power = power, distanceToX = distances)
        createFractal()
    }
    private fun toMandelbrot(power: Int){
        fractal = Mandelbrot(dimension, power = power, distanceToX = distances)
        createFractal()
    }
    private fun toBurningShip(power: Int){
        fractal = BurningShip(dimension, power = power, distanceToX = distances)
        createFractal()
    }

    private fun toJuliaBS(c: Complex, power: Int) {
        fractal = JuliaBS(dimension, c = c, power = power, distanceToX = distances)
        createFractal()
    }

    fun save(directory: File) {
        val nameOfFile  = UUID.randomUUID()
        val file = File("${directory.absolutePath}/${nameOfFile.toString()}.png")
        val renderedFractal: RenderedImage = SwingFXUtils.fromFXImage(image,null)
        ImageIO.write(renderedFractal, "png", file)
    }

}