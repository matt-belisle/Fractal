package UI.TornadoFX.Views

import UI.Colors.ColorPalettes
import UI.TornadoFX.Controllers.FractalController
import UI.TornadoFX.events.NotSelectedTwoColoursEvent
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.VBox
import tornadofx.*

class TwoSchemesFragment(override val root: VBox = VBox(), private val fractalController: FractalController) : Fragment() {
    val innerColor = ToggleGroup()
    val outerColor = ToggleGroup()

    init {
        with(root) {
            form {

                fieldset("Inner Colour") {
                    ColorPalettes.values().forEachIndexed { index, color -> radiobutton(color.toString(), innerColor) { if (index == 0) isSelected = true } }
                }
                fieldset("Outer Colour") {
                    ColorPalettes.values().forEachIndexed { index, color -> radiobutton(color.toString(), outerColor) { if (index == 0) isSelected = true } }
                }
            }
            button("Change Color Palette") {
                action {
                    val innerColor = innerColor.selectedToggle as RadioButton
                    val outerColor = outerColor.selectedToggle as RadioButton
                    fractalController.twoColorPalette(
                            ColorPalettes.valueOf(innerColor.text.toString()
                                    .toUpperCase().replace(' ', '_')),
                            ColorPalettes.valueOf(outerColor.text.toString()
                                    .toUpperCase().replace(' ', '_')))
                    fire(NotSelectedTwoColoursEvent)
                    root.removeFromParent()
                }
            }
        }
    }
}