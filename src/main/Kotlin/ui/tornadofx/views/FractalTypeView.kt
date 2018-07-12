package ui.tornadofx.views

import data.fractaltypes.FractalTypes
import javafx.scene.control.ToggleGroup
import tornadofx.*

class FractalTypeView(override val root: Form = Form()) : View() {
    val fractalType = ToggleGroup()
    init {
        with (root) {
            fieldset {
                FractalTypes.values().forEachIndexed { index, fractal -> radiobutton(fractal.toString(), fractalType) {if(index == 0) isSelected = true} }

            }
        }
    }
}