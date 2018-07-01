package Data.FractalTypes

import Data.Distances.DistanceToLine
import Data.Distances.DistanceToX
import Data.Distances.GraphObjects.Complex
import Data.Distances.GraphObjects.Point
import UI.Colors.DataToColour
import kotlin.math.abs
import kotlin.math.min

class BurningShip(private val dimension: Int = 500, private val bound: Int = 2,
                  private val maxIterations: Int = 512, startX: Double = -2.5,
                  endX: Double = 1.5, startY: Double = 1.0, endY: Double = -2.0, private val power: Int = 2,
                  private val distanceToX: List<DistanceToX> = listOf<DistanceToX>( DistanceToLine(Point(0.0, 1.0), Point(0.0,0.0)), DistanceToLine(Point(0.0,0.0), Point(1.0,0.0)) )) :
        Fractal(dimension, startX, endX, startY, endY) {

    init {
        setPoints(startX, startY)
    }


    override fun createFractal() {
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                pixels[indexRow][indexColumn] = getPointValue(complex ?: Complex(0.0, 0.0), indexRow, indexColumn)
            }
        }
    }

    private fun getPointValue(point: Complex, row: Int, col: Int): Int {
        var tempPoint = point
        var iters = 0
        var distance = 10000.0
        while (tempPoint.magnitude() <= 4.0 && iters < maxIterations) {
            tempPoint =  Complex(abs(tempPoint.getReal()), abs(tempPoint.getImag())).pow(power) + point
            if (tempPoint == point) {
                afterIteration[row][col] = DataToColour(point, 0.0, maxIterations)
                return maxIterations
            }
            distance  = min(distance ,distanceToX.map{it.distance(tempPoint.toPoint())}.min()!!)
            iters++
        }
        afterIteration[row][col] = DataToColour(tempPoint, distance, iters)
        return iters
    }

}