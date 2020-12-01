package algoritms

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("Ingrese la funcion derivada:")
    val exp1 = scanner.nextLine()
    println("Ingrese el valor de x0:")
    val x0 = scanner.nextDouble()
    println("Ingrese el valor de y0:")
    val y0 = scanner.nextDouble()
    println("Ingrese el incremento de x:")
    val h = scanner.nextDouble()
    println("Ingresela cantidad de puntos:")
    val cantPuntos = scanner.nextInt()
    println("Ingresela cantidad de decimales:")
    val cantDecimales = scanner.nextInt()

    val derivada = ExpressionBuilder(exp1)
            .variables("x", "y")
            .build()

    calcularMetodoEulerModificado(derivada, x0, y0, h, cantPuntos, cantDecimales)
}

fun calcularMetodoEulerModificado(derivada: Expression, x0: Double, y0: Double, h: Double, cantPuntos: Int, cantDecimales: Int) {
    var xi = x0
    var yi = y0
    var ecuacionPredictoria: Double
    var ecuacionCorrecta: Double

    for (i in 0 until cantPuntos) {
        println("ecuacion predictoria: y*(i+1) = yi + (h * D(xi, yi))")
        println("ecuacion correcta:    y(i+1) = yi + (h/2 * [D(xi, yi) + D(x(i+1), y*(i+1)])")

        println("i = $i")
        ecuacionPredictoria = yi + (h * D(derivada, xi, yi))
        ecuacionPredictoria = redondear(ecuacionPredictoria, cantDecimales)

        ecuacionCorrecta = yi + (h / 2 * (D(derivada, xi, yi) + D(derivada, xi + h, ecuacionPredictoria)))
        println("y*${i + 1} " +
                "= y$i + (h * D(x${i}, y${i})) " +
                "= $yi + ($h * (D($xi, $yi))) = $ecuacionPredictoria")

        ecuacionCorrecta = redondear(ecuacionCorrecta, cantDecimales)

        println("y${i + 1} " +
                "= y$i + (h/2 * (D(x${i}, y${i}) + D(x${i + 1}, y*${i + 1}))) " +
                "= $yi + (${h / 2} * (D($xi, $yi) + D(${xi + h}, $ecuacionPredictoria))) = $ecuacionCorrecta")

        xi = redondear(xi + h, cantDecimales)
        yi = ecuacionCorrecta
    }
}

private fun D(funcion: Expression, x: Double, y: Double): Double {
    funcion.setVariable("x", x)
    funcion.setVariable("y", y)
    return funcion.evaluate()
}

private fun redondear(num: Double, decimales: Int): Double {
    val redondeado = BigDecimal(num)
            .setScale(decimales, RoundingMode.HALF_EVEN)
    return redondeado.toDouble()
}