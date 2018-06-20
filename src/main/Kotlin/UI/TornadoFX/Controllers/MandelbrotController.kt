package UI.TornadoFX.Controllers

import Data.Complex
import Data.FractalTypes.FractalTypes
import Data.FractalTypes.Julia
import UI.Colors.ColorPalette
import javafx.scene.image.WritableImage
import tornadofx.*

class MandelbrotController(dimension: Int = 1000) : Controller(){
    private var makeFractal: MakeImage = MakeImage(dimension)
    var image: WritableImage
    var loading: String = ""

    init{
        image = makeFractal.writePixels()
    }

    fun zoom(x: Double, y: Double, zoomIn : Boolean){
        makeFractal.zoom(x,y,zoomIn)
    }

    fun newFractal(type: FractalTypes, real: Double, imag: Double, power: Int) {
        when (type){
            FractalTypes.JULIA -> makeFractal.toJulia(Complex(real, imag), power)
            FractalTypes.MANDELBROT -> makeFractal.toMandelbrot(power)
            FractalTypes.BURNINGSHIP -> makeFractal.toBurningShip(power)
        }
    }
}