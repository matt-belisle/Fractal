package data.distances.GraphObjects

class Line(points: Pair<Point, Point>){
    val point1 = points.first

    val point2 = points.second

    override fun toString(): String {
        return "$point1, $point2"
    }
}