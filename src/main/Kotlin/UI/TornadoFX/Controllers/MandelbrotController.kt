package UI.TornadoFX.Controllers

import Data.Complex
import javafx.scene.image.WritableImage
import tornadofx.Controller

class MandelbrotController(dimension: Int = 1000) : Controller(){
    private var makeFractal: MakeImage = MakeImage(dimension)
    var image: WritableImage

    init{
        image = makeFractal.writePixels()
    }

    fun changeConstant(real: Double, imag: Double){
        makeFractal.changeConstant(Complex(real, imag))
    }

    fun zoom(x: Double, y: Double){
        makeFractal.zoom(x,y,zoomIn = true)
    }
}