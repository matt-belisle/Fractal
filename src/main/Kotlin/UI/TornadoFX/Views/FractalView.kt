package UI.TornadoFX.Views

import Data.FractalTypes.FractalTypes
import UI.TornadoFX.Controllers.MandelbrotController
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.scene.input.MouseButton
import tornadofx.*


class FractalView : View("Fractal") {
    private val mandelbrotController: MandelbrotController by inject()
    var realTextField: TextField by singleAssign()
    var imagTextField: TextField by singleAssign()
    var powerTextField: TextField by singleAssign()
    var loadingLabel: Label by singleAssign()
    private val fractalType = ToggleGroup()
    override val root = borderpane {
        top = form {

            fieldset {
                field("Real") {
                    realTextField = textfield()
                }
                field("Imag") {
                    imagTextField = textfield()
                }
                field("Power") {
                    powerTextField = textfield("2")
                }
            }
            loadingLabel = label("Loading") {
                isVisible = false
            }
            button("Submit") {
                action {
                    val selected = fractalType.selectedToggle as RadioButton
                    mandelbrotController.newFractal(

                            FractalTypes.valueOf(selected.text.toUpperCase()),
                            try {
                                realTextField.text.toDouble()
                            } catch (e: NumberFormatException) {
                                0.0
                            },
                            try {
                                imagTextField.text.toDouble()
                            } catch (e: NumberFormatException) {
                                0.0
                            }, try {
                                powerTextField.text.toInt()
                            } catch (e: NumberFormatException) {
                                1
                    })
                }
            }
        }
        center = imageview(mandelbrotController.image) {
            scaleY = -1.0
            setOnMouseClicked {
                mandelbrotController.zoom(it.x, it.y, it.button == MouseButton.PRIMARY)

            }
        }
        left = form {
            fieldset {

                radiobutton(FractalTypes.MANDELBROT.toString(), fractalType) { isSelected = true }
                radiobutton(FractalTypes.JULIA.toString(), fractalType)
                radiobutton(FractalTypes.BURNINGSHIP.toString(), fractalType)

            }
        }
    }
}
