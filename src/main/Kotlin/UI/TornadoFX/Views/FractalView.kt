package UI.TornadoFX.Views

import UI.TornadoFX.Controllers.MandelbrotController
import javafx.scene.control.TextField
import tornadofx.*


class FractalView : View("Fractal") {
    private val mandelbrotController: MandelbrotController by inject()
    var realTextField: TextField by singleAssign()
    var imagTextField: TextField by singleAssign()
    override val root = borderpane {
        top = vbox {
            this.hbox {
                this.vbox {
                    label("Real").
                    label("Imag")
                }
                this.vbox {
                    realTextField = textfield()
                    imagTextField = textfield()
                }
            }

            this.button("Submit") {
                useMaxWidth = true
                action { mandelbrotController.changeConstant(
                        try{ realTextField.text.toDouble() }
                        catch(e:NumberFormatException) { 0.0 },
                        try{ imagTextField.text.toDouble() }
                        catch(e:NumberFormatException){ 0.0 } )
                }
            }
        }
        center  = imageview(mandelbrotController.image){
            setOnMouseClicked {
                mandelbrotController.zoom(it.sceneX, it.sceneY)
            }
        }
    }
}
