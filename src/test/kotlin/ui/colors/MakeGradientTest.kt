package ui.colors


import javafx.scene.paint.Color
import org.junit.Assert.assertEquals
import org.junit.Test


class MakeGradientTest {

    @Test
    fun noColors(){
        val gradient = MakeGradient.makeGradient(1, emptyList())
        assertEquals(0, gradient.size)
    }
    @Test
    fun badSize(){
        val gradient = MakeGradient.makeGradient(-1, emptyList())
        assertEquals(0, gradient.size)
    }
    @Test
    fun singleColorAllSameColor() {
        val colors = listOf<Color>(Color.hsb(1.0,1.0,1.0))
        val gradient = MakeGradient.makeGradient(1, colors)
        assertEquals(1, gradient.size)
    }
    @Test
   fun  singleColorsallSameLargeSize(){
        val testColor = Color.hsb(1.0,1.0,1.0)
        val colors = listOf<Color>(Color.hsb(1.0,1.0,1.0))
        val gradient = MakeGradient.makeGradient(256, colors)
        assertEquals(256, gradient.size)
        assertEquals(256, gradient.filter { it == testColor}.size)
    }

    @Test
    fun twoColorsCheckFirstLast(){
        val testColor1 = Color.hsb(1.0,1.0,1.0)
        val testColor2 = Color.hsb(0.5,0.5,1.0)
        val gradient = MakeGradient.makeGradient(256, listOf(testColor1, testColor2))
        assertEquals(256, gradient.size)
        assertEquals(testColor1, gradient[0])
        assertEquals(testColor2, gradient[255])
    }
    @Test
    fun threeColorsCheckSpots(){
        val testColor1 = Color.hsb(1.0,1.0,1.0)
        val testColor2 = Color.hsb(0.5,0.5,1.0)
        val testColor3 = Color.hsb(0.25,0.24,0.1)
        val gradient = MakeGradient.makeGradient(300, listOf(testColor1, testColor2, testColor3))
        assertEquals(300, gradient.size)
        assertEquals(testColor1, gradient[0])
        assertEquals(testColor2, gradient[150])
        assertEquals(testColor3, gradient[299])
    }

    @Test
    fun notGoodDivisionFiveColorsCheckAll(){
        val testColor1 = Color.hsb(1.0,1.0,1.0)
        val testColor2 = Color.hsb(0.5,0.5,1.0)
        val testColor3 = Color.hsb(0.25,0.24,0.1)
        val testColor4 = Color.hsb(0.75,1.0,1.0)
        val testColor5 = Color.hsb(0.5,0.5,1.0)
        val gradient = MakeGradient.makeGradient(253, listOf(testColor1, testColor2, testColor3, testColor4, testColor5))
        assertEquals(253, gradient.size)
        assertEquals(testColor1, gradient[0])
        println(gradient.indexOf(testColor2))
        assertEquals(testColor2, gradient[63])
        assertEquals(testColor3, gradient[126])
        assertEquals(testColor4, gradient[189])
        assertEquals(testColor5, gradient[252])

    }

}
