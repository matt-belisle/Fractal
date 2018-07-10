package ui.tornadofx.Views.Fragments

import data.distances.GraphObjects.Circle
import data.distances.GraphObjects.Point
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.TextField
import tornadofx.*

class CircleForm(override val root: Form = Form(), list: MutableList<Circle>, private val nextId: Int): Fragment() {
    var ptX: TextField by singleAssign()
    var ptY: TextField by singleAssign()
    var radius: TextField by singleAssign()

    init {
        fieldset {
            field("Centre-X-Coord") {
                ptX = textfield()
            }
            field("Centre-Y-Coord") {
                ptY = textfield()
            }
            field("Radius") {
                radius = textfield()
            }
        }
        button {
            text = "Submit"
            action {
                val x: Double? = ptX.text.toDoubleOrNull()
                val y: Double? = ptY.text.toDoubleOrNull()
                val radiusD: Double? = radius.text.toDoubleOrNull()
                if (x == null || y == null || radiusD == null) {
                    alert(
                            type = Alert.AlertType.ERROR,
                            header = "Null Data",
                            content = "Fill in Text Boxes with valid numbers",
                            actionFn = { btnType ->
                                if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                                    close()
                                }
                            })
                } else{
                    list += Circle(Point(x, y), radiusD, nextId)
                    root.removeFromParent()
                }
            }
        }
    }
}
