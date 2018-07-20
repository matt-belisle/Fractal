package ui.tornadofx.views.fragments

import data.distances.GraphObjects.Line
import data.distances.GraphObjects.Point
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.TextField
import tornadofx.*


class LineForm(override val root: Form = Form(), val list: MutableList<Line>) : Fragment() {
    private var pt1X: TextField by singleAssign()
    private var pt1Y: TextField by singleAssign()
    private var pt2X: TextField by singleAssign()
    private var pt2Y: TextField by singleAssign()

    init {
        fieldset {
            field("X-1") {
                pt1X = textfield()
            }
            field("Y-1") {
                pt1Y = textfield()
            }
            field("X-2") {
                pt2X = textfield()
            }
            field("Y-2") {
                pt2Y = textfield()
            }
        }
        button {
            text = "Submit"
            action {
                val x1: Double? = pt1X.text.toDoubleOrNull()
                val y1: Double? = pt1Y.text.toDoubleOrNull()
                val x2: Double? = pt2X.text.toDoubleOrNull()
                val y2: Double? = pt2Y.text.toDoubleOrNull()
                if (x1 == null || y1 == null || x2 == null || y2 == null) {
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
                    list += Line(Pair(Point(x1, y1), Point(x2, y2)))
                    root.removeFromParent()
                }
            }
        }
    }
}