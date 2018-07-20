package ui.tornadofx.views

import data.distances.*
import data.distances.GraphObjects.Circle
import data.distances.GraphObjects.Line
import data.distances.GraphObjects.Point
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import ui.tornadofx.views.fragments.CircleForm
import ui.tornadofx.views.fragments.LineForm
import ui.tornadofx.views.fragments.PointForm


class StringPropertys(string: String){
    var string by property(string)
    fun stringProperty() = getProperty(StringPropertys::string)
}
class DistanceTableListView : View() {

    private val points = mutableListOf<Point>().observable()
    private val selectedObject = SimpleStringProperty()
    private val lines = mutableListOf<Line>().observable()
    private val circles = mutableListOf<Circle>().observable()
    private val objects = listOf("Point", "Line", "Circle").observable()
    private val strings = listOf(StringPropertys("Points"), StringPropertys("Lines"), StringPropertys("Circles")).observable()


    override val root = vbox {
        tableview(strings){
            column("Type", StringPropertys::stringProperty)
            rowExpander (expandOnDoubleClick = true){
                if(it.string == "Points"){
                    listview(points){
                        contextmenu {
                            item("Delete").action {
                                if(selectedItem != null) {
                                    points.remove(selectedItem)
                                }
                            }
                        }
                    }
                }else if(it.string == "Lines"){
                    listview(lines){
                        contextmenu {
                            item("Delete").action {
                                if(selectedItem != null) {
                                    lines.remove(selectedItem)
                                }
                            }
                        }
                    }
                }else if(it.string == "Circles"){
                    listview (circles){
                        contextmenu {
                            item("Delete").action {
                                if(selectedItem != null) {
                                    circles.remove(selectedItem)
                                }
                            }
                        }
                    }
                }
            }
            smartResize()
        }
        this += hbox {
            this += combobox(selectedObject, objects) { selectionModel.selectFirst() }
            this += button("+") {
                action {
                    val typeOfObject = DistanceObjects.valueOf(selectedObject.get().toUpperCase())
                    if (typeOfObject == DistanceObjects.POINT) {

                        openInternalWindow(PointForm(list = points))
                    }
                    else if (typeOfObject == DistanceObjects.LINE){
                        openInternalWindow(LineForm(list = lines))
                    }
                    else if(typeOfObject == DistanceObjects.CIRCLE){
                        openInternalWindow(CircleForm(list = circles))
                    }
                }
            }
        }
    }

    private fun nextId() = points.size + lines.size + circles.size

    fun getVals(): List<DistanceToX> {
        val distances = mutableListOf<DistanceToX>()
        points.forEach{distances.add(DistanceToPoint(it))}
        lines.forEach{distances.add(DistanceToLine(it))}
        circles.forEach{distances.add(DistanceToCircle(it))}
        return distances.toList()
    }
}


