package ui.tornadofx.Views

import data.fractaltypes.FractalTypes
import ui.tornadofx.controllers.FractalController
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.input.MouseButton
import tornadofx.*


class FractalView : View("Fractal") {
    private val fractalController: FractalController by inject()
    var realTextField: TextField by singleAssign()
    private var imagTextField: TextField by singleAssign()
    var powerTextField: TextField by singleAssign()
    private var fractalyTypeView = FractalTypeView()
    private var colorPaletteView = ColorPaletteView(fractalController = fractalController)

    private var distanceTableListView = DistanceTableListView()

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
            button("Submit") {
                action {
                    submitFractal()
                }
                shortcut("Enter")
            }
        }
        center = imageview(fractalController.image) {
            scaleY = -1.0
            setOnMouseClicked {
                fractalController.zoom(it.x, it.y, it.button == MouseButton.PRIMARY)

            }
        }
        left = vbox {
           this += fractalyTypeView
           this += distanceTableListView

        }

        right = colorPaletteView.root
    }

    private fun submitFractal() {
        fractalController.setDistances(distanceTableListView.getVals())
        val selectedFractal = fractalyTypeView.fractalType.selectedToggle as RadioButton
        fractalController.newFractal(

                FractalTypes.valueOf(selectedFractal.text.toUpperCase().replace(' ', '_')),
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
