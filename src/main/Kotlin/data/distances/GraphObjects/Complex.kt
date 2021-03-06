package data.distances.GraphObjects


class Complex(private val real: Double = 0.0, private val imag: Double = 0.0){
    fun getReal(): Double = real
    fun getImag(): Double = imag

    operator fun plus(other: Complex): Complex = Complex(real + other.real, imag + other.imag)
    operator fun minus(other: Complex): Complex = Complex(real - other.real, imag - other.imag)
    operator fun times(other: Complex): Complex = Complex((real * other.real - imag * other.imag), (real * other.imag + imag * other.real))
    fun conjugate(): Complex = Complex(real, -1 * imag)
    operator fun div(denom: Complex): Complex {
        if(denom == Complex(0.0, 0.0)){
            throw Exception("Denominator magnitude was zero cannot divide")
        }
        val numerator = this * denom.conjugate()
        val denominator = denom.multByConjugate()
        return Complex(numerator.real / denominator, numerator.imag / denominator)

    }
    private fun multByConjugate(): Double {
        val real =(this * this.conjugate())
        if(real.imag != 0.0){
            print(real.imag)
            throw(Exception("Multiply by conjugate unsuccessful"))
        }
        return real.real
    }

    fun magnitude(): Double = Math.sqrt(real*real + imag*imag)

    override fun equals(other: Any?): Boolean {
        return if(other is Complex){
            real == other.real && imag == other.imag
        }else{
            false
        }
    }

    override fun toString(): String {
        return "$real $imag i"
    }
//TODO: Make better way to do this, should be a faster way.
    fun pow(power: Int): Complex {
        var temp = Complex(real, imag)
        for (i in 1  until power) {
            temp = temp*this
        }
        return temp
    }

    fun toPoint(): Point {
        return Point(real, imag)
    }
}