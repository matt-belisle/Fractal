package Data.FractalTypes

import Data.Complex

abstract class Fractal(private val dimension: Int, startX: Double, endX: Double, startY: Double, endY: Double) {
    internal var points = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    //this will be the actual pixel data to draw
    var pixels = Array(dimension) { IntArray(dimension, { 0 }) }
    var afterIteration = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    private var epsilonX = (Math.abs(startX) + Math.abs(endX)) / dimension
    private var epsilonY = (Math.abs(startY) + Math.abs(endY)) / dimension

    abstract fun createFractal()

    fun zoom( zoom: Double,origin: Pair<Int, Int> ){
        val newOrigin = points[origin.first][origin.second] ?: Complex(0.0, 0.0)
        epsilonX/= zoom
        epsilonY/= zoom
        println(epsilonX)
        setPoints(newOrigin.getReal() - dimension / 2 * epsilonX, newOrigin.getImag() + dimension / 2 * epsilonY)
    }

    internal fun setPoints(startX: Double, startY: Double){

        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                points[indexRow][indexColumn] = Complex(startX + indexRow * epsilonX, startY - indexColumn * epsilonY)
            }
        }
    }
}