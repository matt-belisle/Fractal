package UI.TornadoFX.Views

import UI.TornadoFX.Controllers.MandelbrotController
import javafx.scene.control.TextField
import javafx.scene.input.MouseButton
import tornadofx.*


class FractalView : View("Fractal") {
    private val mandelbrotController: MandelbrotController by inject()
    var realTextField: TextField by singleAssign()
    var imagTextField: TextField by singleAssign()
    override val root = borderpane {
        top = form {

            fieldset {
                field("Real") {
                    realTextField = textfield()
                }
                field("Imag") {
                    imagTextField = textfield()
                }
            }
            button("Submit") {
                action {
                    mandelbrotController.changeConstant(
                            try {
                                realTextField.text.toDouble()
                            } catch (e: NumberFormatException) {
                                0.0
                            },
                            try {
                                imagTextField.text.toDouble()
                            } catch (e: NumberFormatException) {
                                0.0
                            })
                }
            }
            button("Reset To Mandelbrot"){
                action {
                    mandelbrotController.toMandelbrot()
                }
            }
        }
        center = imageview(mandelbrotController.image) {
            setOnMouseClicked {
                mandelbrotController.zoom(it.x, it.y, it.button == MouseButton.PRIMARY)
            }
        }
    }
}
