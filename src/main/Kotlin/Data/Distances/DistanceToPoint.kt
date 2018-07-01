package Data.Distances

import Data.Distances.GraphObjects.Point
import kotlin.math.sqrt

class DistanceToPoint(private val sourcePoint: Point): DistanceToX {
    override fun distance(point: Point): Double {
        val xDist = point.x + sourcePoint.x
        val yDist = point.y + sourcePoint.y
        return sqrt(xDist * xDist + yDist * yDist)
    }

}