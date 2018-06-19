package Data

class Mandelbrot(private val dimension: Int = 500, private val bound: Int = 2,
                 private val maxIterations: Int = 512, startX: Double = -2.0, endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0, private val c: Complex? = null ) {
    private var epsilonX = (Math.abs(startX) + Math.abs(endX)) / dimension
    private var epsilonY = (Math.abs(startY) + Math.abs(endY)) / dimension
    //this will be the points the operations done on
    private var points = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    //this will be the actual pixel data to draw
    var pixels = Array(dimension) { IntArray(dimension, { 0 }) }
    var afterIteration = Array(dimension) { arrayOfNulls<Complex>(dimension) }

    init {

       setPoints(startX, startY)
    }


    fun CreateFractal() {
        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                pixels[indexRow][indexColumn] = getPointValue(complex ?: Complex(0.0, 0.0), maxIterations, c ?: (complex ?: Complex(0.0,0.0)), indexRow, indexColumn )
            }
        }
    }

    private fun getPointValue(point: Complex, maxIterations: Int, c: Complex, row: Int, col: Int): Int {
        var tempPoint = point
        var iters = 0
        while (tempPoint.magnitude() <= 4.0 && iters < maxIterations) {
            tempPoint = tempPoint * tempPoint + c
            if (tempPoint == point) {
                afterIteration[row][col] = point
                return maxIterations
            }
            afterIteration[row][col] = tempPoint
            iters++
        }
        return iters
    }
    fun zoom( zoom: Double,origin: Pair<Int, Int> ){
        val newOrigin = points[origin.first][origin.second] ?: Complex(0.0,0.0)
        epsilonX/= zoom
        epsilonY/= zoom
        println(epsilonX)
        setPoints(newOrigin.getReal() - dimension / 2 * epsilonX, newOrigin.getImag() + dimension / 2 * epsilonY)
    }
    private fun setPoints( startX: Double, startY: Double){

        points.forEachIndexed { indexRow, arrayOfComplexs ->
            arrayOfComplexs.forEachIndexed { indexColumn, complex ->
                points[indexRow][indexColumn] = Complex(startX + indexRow * epsilonX, startY - indexColumn * epsilonY)
            }
        }
    }
}
