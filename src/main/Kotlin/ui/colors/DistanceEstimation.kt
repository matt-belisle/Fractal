package ui.colors

import javafx.scene.paint.Color
import kotlin.math.sin

class DistanceEstimation(private val maxIterations: Int, private val colors: List<Color>) : ColorPalette {
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.distance < 0.01){
            return Color.BLACK
        }
        val location = ((sin(dataToColour.distance * 400) + 1) * 100  % maxIterations).toInt()

        return colors[location]
    }

}