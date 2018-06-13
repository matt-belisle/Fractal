import Data.Mandelbrot
import org.junit.Assert.*
import org.junit.Test


class MandelbrotTest{
    @Test
    fun testMandelbrotCreation(){
        val mandelbrot = Mandelbrot(dimension = 1)
        mandelbrot.CreateFractal()
        assertEquals(mandelbrot.pixels.size, 1)
        assertEquals(mandelbrot.pixels[0].size, 1)
        assert(mandelbrot.pixels[0][0] <= 127)
    }
}