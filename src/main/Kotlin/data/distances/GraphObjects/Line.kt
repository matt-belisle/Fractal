package data.distances.GraphObjects

import tornadofx.*

class Line(points: Pair<Point, Point>){
    var point1 by property (points.first)
    fun point1Property() = getProperty(Line::point1)

    var point2 by property (points.second)
    fun point2Property() = getProperty(Line::point2)

    override fun toString(): String {
        return "1st Point: $point1, 2nd Point: $point2"
    }
}