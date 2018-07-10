package data.distances

import data.distances.GraphObjects.Point

interface DistanceToX {
    fun distance(point: Point): Double
}