package UI.TornadoFX.Views

import Data.FractalTypes.FractalTypes
import UI.Colors.ColorPalette
import UI.Colors.ColorPalettes
import UI.TornadoFX.Controllers.FractalController
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import tornadofx.*


class FractalView : View("Fractal") {
    private val fractalController: FractalController by inject()
    var realTextField: TextField by singleAssign()
    private var imagTextField: TextField by singleAssign()
    var powerTextField: TextField by singleAssign()
    var loadingLabel: Label by singleAssign()
    private var fractalyTypeView = FractalTypeView()
    private var colorPaletteView = ColorPaletteView(fractalController = fractalController)

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
                    submitFractal()
                }
            }
        }
        center = imageview(fractalController.image) {
            scaleY = -1.0
            setOnMouseClicked {
                fractalController.zoom(it.x, it.y, it.button == MouseButton.PRIMARY)

            }
        }
        left = fractalyTypeView.root

        right = colorPaletteView.root
    }

    private fun submitFractal() {
        val selectedFractal = fractalyTypeView.fractalType.selectedToggle as RadioButton
        val selectedColorPalette = colorPaletteView.colorPalette.selectedToggle as RadioButton
        fractalController.newFractal(

                FractalTypes.valueOf(selectedFractal.text.toUpperCase().replace(' ', '_')),
                ColorPalettes.valueOf(selectedColorPalette.text.toUpperCase().replace(' ', '_')),
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

    init {
        root.addEventFilter(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ENTER) {
                submitFractal()
            }
        }
    }
}
