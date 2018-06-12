import org.junit.Assert.*
import org.junit.Test

class ComplexTest{
    @Test
    fun testAdd(){
        assertEquals(Complex(0.0,0.0), Complex(0.0,0.0) + Complex(0.0, 0.0))
        assertEquals(Complex(1.0,2.0), Complex(1.0,0.0) + Complex(0.0, 2.0))
        assertEquals(Complex(1.0,2.0), Complex(0.6,1.2) + Complex(0.4, 0.8))
        assertEquals(Complex(1.0,2.0), Complex(1.0,-4.0) + Complex(0.0, 6.0))
    }
    @Test
    fun testMult(){
        assertEquals(Complex(0.0,0.0), Complex(0.0,0.0) * Complex(0.0, 0.0))
        assertEquals(Complex(0.0,2.0), Complex(1.0,0.0) * Complex(0.0, 2.0))
        assertEquals(Complex(-0.72,0.96), Complex(0.6,1.2) * Complex(0.4, 0.8))
        assertEquals(Complex(24.0,6.0), Complex(1.0,-4.0) * Complex(0.0, 6.0))
    }

    @Test
    fun testSub(){
        assertEquals(Complex(0.0,0.0), Complex(0.0,0.0) + Complex(0.0, 0.0))
        assertEquals(Complex(1.0,2.0), Complex(1.0,0.0) + Complex(0.0, 2.0))
    }

    @Test
    fun testDiv(){
        assertEquals(Complex(0.0,-0.5), Complex(1.0,0.0) / Complex(0.0, 2.0))
        assertEquals(1.5, (Complex(0.6,1.2) / Complex(0.4, 0.8)).getReal(), 0.001)
    }
}