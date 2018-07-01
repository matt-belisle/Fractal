package UI.TornadoFX.Controllers

import Data.Distances.GraphObjects.Complex
import Data.FractalTypes.FractalTypes
import UI.Colors.*
import javafx.scene.image.WritableImage
import tornadofx.*

class FractalController(dimension: Int = 1000) : Controller(){
    private var makeFractal: MakeImage = MakeImage(dimension)
    var image: WritableImage

    init{
            makeFractal.createFractal()
            image = makeFractal.colorFractal()
    }

    fun zoom(x: Double, y: Double, zoomIn : Boolean){
        runAsync {
            makeFractal.zoom(x, y, zoomIn)
        }ui{
            image = makeFractal.colorFractal()
        }
    }

    fun newFractal(type: FractalTypes,color: ColorPalettes, real: Double, imag: Double, power: Int) {
        changePalette(color)
        changeFractalType(type, real, imag, power)
    }

    private fun changeFractalType(type: FractalTypes, real: Double, imag: Double, power: Int) {
            when (type) {
                FractalTypes.JULIA -> makeFractal.toJulia(Complex(real, imag), power)
                FractalTypes.MANDELBROT -> makeFractal.toMandelbrot(power)
                FractalTypes.BURNING_SHIP -> makeFractal.toBurningShip(power)
                FractalTypes.JULIA_BURNING_SHIP -> makeFractal.toJuliaBS(Complex(real, imag), power)
            }
    }

    fun changePalette(color: ColorPalettes) {
        runAsync {
            when (color) {
                ColorPalettes.BLACK_AND_WHITE -> makeFractal.colorPalette = BlackAndWhiteScheme(512)
                ColorPalettes.SMOOTH -> makeFractal.colorPalette = SmoothScheme(512)
                ColorPalettes.BURNING_SHIP -> makeFractal.colorPalette = BurningShipSmooth(512)
                ColorPalettes.DISTANCE_ESTIMATION -> makeFractal.colorPalette = DistanceEstimation(512)
                ColorPalettes.DOUBLE_LOG -> makeFractal.colorPalette = DoubleLogScheme(512)
                ColorPalettes.INTERNAL_ANGLE -> makeFractal.colorPalette = InternalAngleScheme(512)
                ColorPalettes.ITERATED -> makeFractal.colorPalette = IteratedScheme()
            }
        }ui {
            image = makeFractal.colorFractal()
        }
    }

    fun twoColorPalette(innerColor: ColorPalettes, outerColor: ColorPalettes) {
        runAsync {
            val innerScheme = when (innerColor) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(512)
                ColorPalettes.SMOOTH -> SmoothScheme(512)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(512)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(512)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(512)
                ColorPalettes.INTERNAL_ANGLE -> InternalAngleScheme(512)
                ColorPalettes.ITERATED -> IteratedScheme()
            }
            val outerScheme = when (outerColor) {
                ColorPalettes.BLACK_AND_WHITE -> BlackAndWhiteScheme(512)
                ColorPalettes.SMOOTH -> SmoothScheme(512)
                ColorPalettes.BURNING_SHIP -> BurningShipSmooth(512)
                ColorPalettes.DISTANCE_ESTIMATION -> DistanceEstimation(512)
                ColorPalettes.DOUBLE_LOG -> DoubleLogScheme(512)
                ColorPalettes.INTERNAL_ANGLE -> InternalAngleScheme(512)
                ColorPalettes.ITERATED -> IteratedScheme()
            }
            makeFractal.colorPalette = TwoSchemes(512, innerScheme, outerScheme)
        }ui{
            image = makeFractal.colorFractal()
        }
    }
}