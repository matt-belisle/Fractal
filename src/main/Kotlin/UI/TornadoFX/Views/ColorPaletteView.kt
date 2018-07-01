package UI.TornadoFX.Views

import UI.Colors.ColorPalettes
import UI.TornadoFX.Controllers.FractalController
import UI.TornadoFX.events.NotSelectedTwoColoursEvent
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import tornadofx.*

class ColorPaletteView(override val root: Form = Form(), private val fractalController: FractalController) : View() {
    val colorPalette = ToggleGroup()
    private val twoColorsRadio = radiobutton("Two Colouring Schemes", colorPalette)
    private val twoSchemesFragment = TwoSchemesFragment(fractalController = fractalController)

    init {
        with(twoColorsRadio) {
            selectedProperty().addListener { _, _, isSelected ->
                if (isSelected) {
                    root.add(twoSchemesFragment)

                } else {
                    twoSchemesFragment.removeFromParent()

                }
            }
        }

        with(root) {
            fieldset {
                ColorPalettes.values().forEachIndexed { index, color -> radiobutton(color.toString(), colorPalette) { if (index == 0) isSelected = true } }
            }
            fieldset {
                this += twoColorsRadio
            }

            button("Change Color Palette") {
                action {
                    val selectedColor = if (twoColorsRadio.isSelected) {
                        RadioButton("Black And White")
                    } else {
                        colorPalette.selectedToggle as RadioButton
                    }

                    fractalController.changePalette(ColorPalettes.valueOf(selectedColor.text.toString()
                            .toUpperCase().replace(' ', '_')))
                }
                disableProperty().bind(twoColorsRadio.selectedProperty())
            }

        }
    }

}