package data.fractaltypes

import data.distances.DistanceToLine
import data.distances.GraphObjects.Complex
import data.distances.DistanceToX
import data.distances.GraphObjects.Line
import data.distances.GraphObjects.Point
import ui.colors.DataToColour
import kotlin.math.min

class Julia(private val dimension: Int = 500, private val bound: Int = 2,
             startX: Double = -2.0,
            endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0,
            private val c: Complex, private val power: Int = 1,
            private val distanceToX: List<DistanceToX> = listOf<DistanceToX>(DistanceToLine(Line(Pair(Point(0.0, 1.0), Point(0.0, 0.0)))), DistanceToLine(Line(Pair(Point(0.0, 0.0), Point(1.0, 0.0)))))):
        Fractal(dimension, startX, endX, startY, endY) {

    init {
        setPoints(startX, startY)
    }


    override fun createFractal() {
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                pixels[indexRow][indexColumn] = getPointValue(complex ?: Complex(0.0, 0.0),  indexRow, indexColumn )
            }
        }
    }

    private fun getPointValue(point: Complex, row: Int, col: Int): Int {
        var tempPoint = point
        var iters = 0
        var distance = 10000.0
        while (tempPoint.magnitude() <= 4.0 && iters < maxIterations) {
            tempPoint = tempPoint.pow(power) + c
            if (tempPoint == point) {
                afterIteration[row][col] = DataToColour(point, distance, maxIterations)
                return maxIterations
            }
            distance  = min(distance ,distanceToX.map{it.distance(tempPoint.toPoint())}.min()!!)
            iters++
        }
        afterIteration[row][col] = DataToColour(point, distance, iters)
        return iters
    }

}
