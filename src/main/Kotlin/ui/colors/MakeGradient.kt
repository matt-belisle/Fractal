package ui.colors

import javafx.scene.paint.Color

class MakeGradient {
    companion object {
        fun makeGradient(numColors: Int, colors: List<Color>): List<Color> {
            //number of groups is size of list -1
            if(numColors <= 0){
                return emptyList()
            }
            if(colors.isEmpty()){
                return emptyList()
            }
            if(colors.size == 1){
                return List(numColors){colors[0]}
            }
            val gradient = arrayOfNulls<Color>(numColors)
            val numDivs = colors.size - 1
            val numColorsPerDiv = ( numColors - 1 ) / numDivs
            var firstColor = colors[0]
            var secondColor = colors[1]
            for (i in 1 until numDivs) {
                for (j in 0 .. numColorsPerDiv) {
                        gradient[(i-1) * numColorsPerDiv + j] = calcColor(j, numColorsPerDiv, secondColor, firstColor)
                }
                firstColor = secondColor
                secondColor = colors[i + 1]
            }
            val firstNull = gradient.indexOfFirst { it == null }
            val numColorsFinalDiv = (numColors - 1) - firstNull

            for (j in firstNull until numColors){
                gradient[j] = calcColor(j - firstNull, numColorsFinalDiv, secondColor, firstColor)
            }
            //there shouldnt be any nulls just converts Color? to Color for accessing
            return gradient.toList().requireNoNulls()
        }

        private fun calcColor(j: Int, numColorsPerDiv: Int, secondColor: Color, firstColor: Color): Color{
            val percentage: Double = j.toDouble() / numColorsPerDiv
            val hue = secondColor.hue * percentage + firstColor.hue * (1 - percentage)
            val sat = secondColor.saturation * percentage + firstColor.saturation * (1 - percentage)
            val bright = secondColor.brightness * percentage + firstColor.brightness * (1 - percentage)
            println("$hue , $sat , $bright")
            return Color.hsb(hue, sat, bright)
        }
    }
}