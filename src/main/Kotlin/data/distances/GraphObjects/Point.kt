package data.distances.GraphObjects

import tornadofx.*


class Point(x: Double, y: Double, id: Int = 0){
    var x by property(x)
    fun xProperty() = getProperty(Point::x)

    var y by property(y)
    fun yProperty() = getProperty(Point::y)

    var id by property(id)
    fun idProperty() = getProperty(Point::id)

    override fun toString(): String {
        return "($x , $y)"
    }
}