package data.fractaltypes

import data.distances.DistanceToLine
import data.distances.DistanceToX
import data.distances.GraphObjects.Complex
import data.distances.GraphObjects.Line
import data.distances.GraphObjects.Point
import ui.colors.DataToColour
import kotlin.math.abs
import kotlin.math.min

class JuliaBS(private val dimension: Int = 500, private val bound: Int = 2,
              startX: Double = -2.0,
              endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0, private val power: Int = 2, private val c: Complex,
              private val distanceToX: List<DistanceToX> = listOf<DistanceToX>(DistanceToLine(Line(Pair(Point(0.0, 1.0), Point(0.0, 0.0)))), DistanceToLine(Line(Pair(Point(0.0, 0.0), Point(1.0, 0.0)))))) :
        Fractal(dimension, startX, endX, startY, endY) {

    init {
        setPoints(startX, startY)
    }


    override fun createFractal() {
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                afterIteration[indexRow][indexColumn] = getPointValue(complex ?: Complex(0.0, 0.0))
            }
        }
    }

    private fun getPointValue(point: Complex): DataToColour {
        var tempPoint = point
        var iters = 0
        var distance = 10000.0
        while (tempPoint.magnitude() <= 4.0 && iters < maxIterations) {
            tempPoint =  Complex(abs(tempPoint.getReal()), abs(tempPoint.getImag())).pow(power) + c

            if (tempPoint == point) {
               return  DataToColour(point, 0.0, maxIterations)

            }
            distance  = min(distance ,distanceToX.map{it.distance(tempPoint.toPoint())}.min()!!)
            iters++
        }
        return DataToColour(tempPoint, distance, iters)

    }

}

