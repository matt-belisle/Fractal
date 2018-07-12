package data.fractaltypes

import data.distances.GraphObjects.Complex
import ui.colors.DataToColour

abstract class Fractal(private val dimension: Int, startX: Double, endX: Double, startY: Double, endY: Double, internal var maxIterations: Int = 512) {
    internal var points = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    //this will be the actual pixel data to draw
    var afterIteration = Array(dimension) { arrayOfNulls<DataToColour>(dimension) }
    private var epsilonX = (Math.abs(startX) + Math.abs(endX)) / dimension
    private var epsilonY = (Math.abs(startY) + Math.abs(endY)) / dimension

    abstract fun createFractal()

    fun zoom( zoom: Double,origin: Pair<Int, Int> ){
        val newOrigin = points[origin.first][origin.second] ?: Complex(0.0, 0.0)
        epsilonX/= zoom
        epsilonY/= zoom
        setPoints(newOrigin.getReal() - dimension / 2 * epsilonX, newOrigin.getImag() + dimension / 2 * epsilonY)
    }

    internal fun setPoints(startX: Double, startY: Double){
        points.forEachIndexed { indexRow, arrayOfComplexes ->
            arrayOfComplexes.forEachIndexed { indexColumn, _ ->
                points[indexRow][indexColumn] = Complex(startX + indexRow * epsilonX, startY - indexColumn * epsilonY)
            }
        }
    }
    fun setMaxIterations(maxIterations: Int){
        this.maxIterations = maxIterations
        createFractal()
    }
}