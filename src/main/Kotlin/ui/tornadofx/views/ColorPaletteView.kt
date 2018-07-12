package ui.tornadofx.views

import ui.colors.ColorPalettes
import ui.tornadofx.controllers.FractalController
import ui.tornadofx.views.fragments.TwoSchemesFragment
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class ColorPaletteView(override val root: VBox = VBox(), private val fractalController: FractalController) : View() {
    val colorPalette = ToggleGroup()
    private val twoColorsRadio = radiobutton("Two Colouring Schemes", colorPalette)
    private val twoSchemesFragment = TwoSchemesFragment(fractalController = fractalController)
    private var maxIterations: TextField by singleAssign()
    private var colorList = mutableListOf<Color>(Color.BLACK, Color.WHITE).observable()

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
            form {
                fieldset {
                    ColorPalettes.values().forEachIndexed { index, color -> radiobutton(color.toString(), colorPalette) { if (index == 0) isSelected = true } }
                }
                fieldset {
                    this += twoColorsRadio
                }

                button("Change Coloring Scheme") {
                    action {
                        val selectedColor = if (twoColorsRadio.isSelected) {
                            RadioButton("Black And White")
                        } else {
                            colorPalette.selectedToggle as RadioButton
                        }

                        fractalController.changeColoringScheme(ColorPalettes.valueOf(selectedColor.text.toString()
                                .toUpperCase().replace(' ', '_')))
                    }
                    disableProperty().bind(twoColorsRadio.selectedProperty())
                }
                colorpicker(mode = ColorPickerMode.MenuButton){
                    valueProperty().onChange {
                        if(it != null) {
                            colorList.add(it)
                        }
                    }
                }
                fieldset {
                    field("Number Of colors") {
                        maxIterations = textfield()
                    }
                }
                button("Change Color Palette"){
                    action {
                        val maxIterations = maxIterations.text.toIntOrNull() ?: 512
                        fractalController.changeColoringPalette( maxIterations, colorList)
                    }
                }
               listview(colorList){
                   cellFormat {
                       text = it.toString()
                       style { baseColor = it } }
               }
            }

        }
    }
}