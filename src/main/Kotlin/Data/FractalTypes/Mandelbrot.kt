package Data.FractalTypes

import Data.Distances.DistanceToLine
import Data.Distances.GraphObjects.Complex
import Data.Distances.DistanceToPoint
import Data.Distances.DistanceToX
import Data.Distances.GraphObjects.Point
import UI.Colors.DataToColour
import kotlin.math.min

class Mandelbrot(private val dimension: Int = 500, private val bound: Int = 2,
                 private val maxIterations: Int = 512, startX: Double = -2.0,
                 endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0, private val power: Int = 2,
                 private val distanceToX: List<DistanceToX> = listOf<DistanceToX>( DistanceToLine(Point(0.0,0.0), Point(1.0,0.0)), DistanceToLine(Point(1.0,0.0),Point(2.0,1.0)) )) :
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
            tempPoint = tempPoint.pow(power) + point

            distance  = min(distance ,distanceToX.map{it.distance(tempPoint.toPoint())}.min()!!)

            if (tempPoint == point) {
                afterIteration[row][col] = DataToColour(point, distance, maxIterations)
                return maxIterations
            }
            iters++
        }
        afterIteration[row][col] = DataToColour(tempPoint, distance, iters)
        return iters
    }

}