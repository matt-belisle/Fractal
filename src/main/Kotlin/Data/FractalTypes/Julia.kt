package Data.FractalTypes

import Data.Complex

class Julia(private val dimension: Int = 500, private val bound: Int = 2,
                 private val maxIterations: Int = 512, startX: Double = -2.0,
                 endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0,
                 private val c: Complex, private val power: Int = 1) :
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
        while (tempPoint.magnitude() <= 4.0 && iters < maxIterations) {
            tempPoint = tempPoint.pow(power) + c
            if (tempPoint == point) {
                afterIteration[row][col] = point
                return maxIterations
            }
            afterIteration[row][col] = tempPoint
            iters++
        }
        return iters
    }

}
