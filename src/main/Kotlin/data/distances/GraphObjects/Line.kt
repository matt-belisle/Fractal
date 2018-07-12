package data.distances.GraphObjects

import tornadofx.*

class Line(points: Pair<Point, Point>, id: Int = 0){
    var point1 by property (points.first)
    fun point1Property() = getProperty(Line::point1)

    var point2 by property (points.second)
    fun point2Property() = getProperty(Line::point2)

    var id by property(id)
    fun idProperty() = getProperty(Point::id)
}