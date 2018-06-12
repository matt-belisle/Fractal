class Mandelbrot(dimension: Int = 500, bound: Int = 2,
                 maxIterations: Int = 127, startX: Double = -2.0, endX: Double = 2.0, startY: Double = 2.0, endY: Double = -2.0 ){
    private val maxIterations = maxIterations
    private val bound = bound
    private val dimension = dimension

    //this will be the points the operations done on
    private var points = Array(dimension) { arrayOfNulls<Complex>(dimension) }
    //this will be the actual pixel data to draw
    var pixels = Array(dimension) { IntArray (dimension, {i -> 0}) }
    init{
        val epsilonX = (Math.abs(startX) + Math.abs(endX))/ dimension
        val epsilonY = (Math.abs(startY) + Math.abs(endY))/ dimension
        for(i in 0 until dimension){
            for(j in 0 until dimension) {
                points[i][j] = Complex(startX + i * epsilonX, 2 - j * epsilonY)
            }
        }
    }


    fun CreateFractal() {
        for (i in 0 until dimension) {
            for (j in 0 until dimension) {
                pixels[i][j] = getPointValue(points[i][j] ?: Complex(0.0, 0.0))

            }
        }
    }
    private fun getPointValue(point: Complex): Int {
        var tempPoint = point
        var iters = 0
        while(tempPoint.magnitude() <= 3.0 && iters < maxIterations){
            tempPoint = tempPoint * tempPoint + point
            if(tempPoint == point){
                return maxIterations
            }
            iters++
        }
            return iters
    }

}