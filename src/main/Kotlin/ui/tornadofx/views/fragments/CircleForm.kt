package ui.tornadofx.views.fragments

import data.distances.GraphObjects.Circle
import data.distances.GraphObjects.Point
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane
import tornadofx.*

class CircleForm(override val root: Form = Form(), list: MutableList<Circle>, window: InternalWindow? = null): Fragment() {
    private var ptX: TextField by singleAssign()
    private var ptY: TextField by singleAssign()
    private var radius: TextField by singleAssign()

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
                    list += Circle(Point(x, y), radiusD)
                    findParentOfType(InternalWindow::class)?.close()
                    //that is basically a return but if that doesnt work then just remove from parent
                    root.removeFromParent()
                }
            }
        }
    }
}
