package ui.tornadofx.views.fragments

import ui.colors.ColorPalettes
import ui.tornadofx.controllers.FractalController
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
                    fractalController.twoColoringScheme(
                            ColorPalettes.valueOf(innerColor.text.toString()
                                    .toUpperCase().replace(' ', '_')),
                            ColorPalettes.valueOf(outerColor.text.toString()
                                    .toUpperCase().replace(' ', '_')))
                    root.removeFromParent()
                }
            }
        }
    }
}