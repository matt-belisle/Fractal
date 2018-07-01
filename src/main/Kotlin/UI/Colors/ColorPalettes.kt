package UI.Colors

enum class ColorPalettes {
    BLACK_AND_WHITE, BURNING_SHIP, DISTANCE_ESTIMATION, DOUBLE_LOG, INTERNAL_ANGLE,ITERATED,SMOOTH;

    override fun toString(): String = super.toString()
            .replace('_', ' ')
            .toLowerCase()
            .split(' ')
            .map{it.capitalize()}
            .foldRight("") { it, next -> "$it $next" }
            .trim()
}