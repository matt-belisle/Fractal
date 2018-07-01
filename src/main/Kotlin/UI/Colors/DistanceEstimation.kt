package UI.Colors

import javafx.scene.paint.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class DistanceEstimation(private val maxIterations: Int) : ColorPalette{
    override fun getColor(dataToColour: DataToColour): Color {
        if(dataToColour.distance < 0.01){
            return Color.BLACK
        }

        return Color.hsb(((sin(dataToColour.distance * 400) + 1) * 100  ) % 255 , 1.0, 1.0)
    }

}