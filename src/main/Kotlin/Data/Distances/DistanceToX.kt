package Data.Distances

import Data.Distances.GraphObjects.Point

interface DistanceToX {
    fun distance(point: Point): Double
}