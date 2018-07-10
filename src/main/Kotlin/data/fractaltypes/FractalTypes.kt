package data.fractaltypes

enum class FractalTypes {
    MANDELBROT, BURNING_SHIP, JULIA, JULIA_BURNING_SHIP;

    override fun toString(): String = super.toString()
            .replace('_', ' ')
            .toLowerCase()
            .split(' ')
            .map{it.capitalize()}
            .foldRight("") { it, next -> "$it $next" }
            .trim()
}