package data.distances.GraphObjects

import tornadofx.*


class Point(x: Double, y: Double){
    var x by property(x)
    fun xProperty() = getProperty(Point::x)

    var y by property(y)
    fun yProperty() = getProperty(Point::y)

    override fun toString(): String {
        return "($x , $y)"
    }
}