package Data

class Mandelbrot(private val dimension: Int = 500, private val bound: Int = 2,
                 private val maxIterations: Int = 127, startX: Double = -2.0, endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0 ) {


    //this will be the points the operations done on
    private var points = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    //this will be the actual pixel data to draw
    var pixels = Array(dimension) { IntArray(dimension, { 0 }) }

    init {
        val epsilonX = (Math.abs(startX) + Math.abs(endX)) / dimension
        val epsilonY = (Math.abs(startY) + Math.abs(endY)) / dimension
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                points[indexRow][indexColumn] = Complex(startX + indexRow * epsilonX, 2 - indexColumn * epsilonY)
            }
        }
    }


    fun CreateFractal() {
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                pixels[indexRow][indexColumn] = getPointValue(complex ?: Complex(0.0, 0.0), maxIterations)
            }
        }
    }

    private fun getPointValue(point: Complex, maxIterations: Int): Int {
        var tempPoint = point
        var iters = 0
        while (tempPoint.magnitude() <= 3.0 && iters < maxIterations) {
            tempPoint = tempPoint * tempPoint + point
            if (tempPoint == point) {
                return maxIterations
            }
            iters++
        }
        return iters
    }
}
