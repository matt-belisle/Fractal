package data.distances

import data.distances.GraphObjects.Circle
import data.distances.GraphObjects.Point

class DistanceToCircle(private val sourceCircle: Circle): DistanceToX {
    private val dToCentre = DistanceToPoint(sourceCircle.centre)
    override fun distance(point: Point): Double {
        val distToCentre = dToCentre.distance(point)
        return if(distToCentre < sourceCircle.radius){
            distToCentre
        } else{
            return distToCentre - sourceCircle.radius
        }
    }

}