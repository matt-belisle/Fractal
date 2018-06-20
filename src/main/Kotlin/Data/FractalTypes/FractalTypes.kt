package Data.FractalTypes

enum class FractalTypes(s:String) {
    JULIA("Julia"), BURNINGSHIP("Burningship"), MANDELBROT("Mandelbrot");

    override fun toString(): String{
        return super.toString().toLowerCase().capitalize()
    }
}