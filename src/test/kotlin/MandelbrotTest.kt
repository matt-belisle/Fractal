import data.fractaltypes.Mandelbrot
import org.junit.Assert.*
import org.junit.Test


class MandelbrotTest{
    @Test
    fun testMandelbrotCreation(){
        val mandelbrot = Mandelbrot(dimension = 1)
        mandelbrot.createFractal()
        assertEquals(mandelbrot.afterIteration.size, 1)
        assertEquals(mandelbrot.afterIteration[0].size, 1)
        assert(mandelbrot.afterIteration[0][0]!!.iterations <= 127)
    }
}