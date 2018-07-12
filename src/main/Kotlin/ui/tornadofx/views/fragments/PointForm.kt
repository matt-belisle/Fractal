package ui.tornadofx.views.fragments

import data.distances.GraphObjects.Point
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.TextField
import tornadofx.*

class PointForm(override val root: Form = Form(), val list: MutableList<Point>, nextID: Int) : Fragment() {
    var ptX: TextField by singleAssign()
    var ptY: TextField by singleAssign()

    init {
        fieldset {
            field("X") {
                ptX = textfield()
            }
            field("Y") {
                ptY = textfield()
            }
        }
        button {
            text = "Submit"
            action {
                val x: Double? = ptX.text.toDoubleOrNull()
                val y: Double? = ptY.text.toDoubleOrNull()
                if (x == null || y == null) {
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
                    list += Point(x, y, nextID)
                    root.removeFromParent()
                }
            }
        }
    }
}