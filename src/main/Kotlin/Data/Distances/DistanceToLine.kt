package Data.Distances

import Data.Distances.GraphObjects.Point
import kotlin.math.abs
import kotlin.math.sqrt
// 0 = ax + by + c
class DistanceToLine(private val pt1: Point, private val pt2: Point): DistanceToX{
    private var isPoint: Boolean = pt1 == pt2
    private val a: Double
    private val b: Double
    private val c: Double
    private val denominatorForDistance: Double
    init{
        if(isPoint) {
            a = 0.0
            b = 0.0
            c=0.0
            denominatorForDistance = 0.0
        }
        else{
            a = pt1.y-pt2.y
            b = pt1.x - pt2.x
            c = pt1.x*pt2.y - pt2.x*pt1.y
            denominatorForDistance = a*a + b*b
        }

    }
    override fun distance(point: Point): Double {
        if(isPoint){
            val xDist = point.x + pt1.x
            val yDist = point.y + pt2.y
            return sqrt(xDist * xDist + yDist * yDist)
        }
        val numerator = abs(a*point.x + b*point.y + c)
        return numerator / denominatorForDistance
    }

}