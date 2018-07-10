package ui.tornadofx.Views

import data.distances.*
import data.distances.GraphObjects.Circle
import data.distances.GraphObjects.Line
import data.distances.GraphObjects.Point
import ui.tornadofx.Views.Fragments.CircleForm
import ui.tornadofx.Views.Fragments.LineForm
import ui.tornadofx.Views.Fragments.PointForm
import javafx.beans.property.SimpleStringProperty
import tornadofx.*


class DistanceTableListView : View() {
    val points = mutableListOf<Point>().observable()
    private val selectedObject = SimpleStringProperty()
    private val lines = mutableListOf<Line>(Line(Pair(Point(1.0, 0.0), Point(1.0, 1.0)))).observable()
    private val circles = mutableListOf<Circle>().observable()
    private val objects = listOf("Point", "Line", "Circle").observable()
    private val tableViewPoints = tableview(points) {
        column("id", Point::idProperty)
        column("x", Point::xProperty)
        column("y", Point::yProperty)
        contextmenu {
            item("Delete").action {
                points.remove(selectedItem)
                removeID(selectedItem?.id ?: 1000)
            }
        }
    }
    private val tableViewLines = tableview(lines) {
        column("id", Line::idProperty)
        column("Point 1", Line::point1Property)
        column("Point 2", Line::point2Property)
        contextmenu {
            item("Delete").action {
                if(selectedItem != null) {
                    lines.remove(selectedItem)
                    removeID(selectedItem?.id ?: 1000)
                }
            }
        }
    }
    private val tableViewCircles = tableview(circles) {
        column("id", Circle::idProperty)
        column("Centre Point", Circle::centreProperty)
        column("Radius", Circle::radiusProperty)
        contextmenu {
            item("Delete").action {
                if(selectedItem != null) {
                    circles.remove(selectedItem)
                    removeID(selectedItem?.id ?: 1000)
                }
            }
        }
    }
    override val root = vbox {
        this += tableViewPoints
        this += tableViewLines
        this += tableViewCircles
        this += hbox {
            this += combobox(selectedObject, objects) { selectionModel.selectFirst() }
            this += button("+") {
                action {
                    val typeOfObject = DistanceObjects.valueOf(selectedObject.get().toUpperCase())
                    if (typeOfObject == DistanceObjects.POINT) {

                        openInternalWindow(PointForm(list = points, nextID = nextId()))
                    }
                    else if (typeOfObject == DistanceObjects.LINE){
                        openInternalWindow(LineForm(list = lines, nextID = nextId()))
                    }
                    else if(typeOfObject == DistanceObjects.CIRCLE){
                        openInternalWindow(CircleForm(list = circles, nextId = nextId()))
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
    fun removeID(id: Int){
        points.filter { it.id > id }.map { it.id-=1 }
        lines.filter { it.id > id }.map { it.id-=1 }
    }
}


