package algoritms

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val exp1 = "x-y"
    val derivada = ExpressionBuilder(exp1)
            .variables("x", "y")
            .build()

    calcularMetodoEuler(derivada, 0.0, 1.0, 0.1, 5, 4)
}

fun calcularMetodoEuler(derivada: Expression, x0: Double, y0: Double, h: Double, cantPuntos: Int, cantDecimales: Int) {
    var xi = x0
    var yi = y0
    var yii: Double

    var fx0y0: Double
    for (i in 0 until cantPuntos) {
        derivada.setVariable("x", xi)
        derivada.setVariable("y", yi)
        fx0y0 = derivada.evaluate()
        yii = redondear(yi + (h * fx0y0), cantDecimales)
        println("y${i + 1} = y$i + (${h} * F(x${i}, y${i})) = $yi + (${h} * F($xi, $yi)) = $yii")
        xi = redondear(xi + h, cantDecimales)
        yi = yii
    }
}

fun redondear(num: Double, decimales: Int): Double {
    val redondeado = BigDecimal(num)
            .setScale(decimales, RoundingMode.HALF_EVEN)
    return redondeado.toDouble()
}