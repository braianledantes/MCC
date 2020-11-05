package algoritms

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("Ingrese una funcion f(x,y): ")
    val exp1 = scanner.nextLine()
    val funcion = ExpressionBuilder(exp1)
            .variables("x", "y")
            .build()

    println("Ingrese el valor de m: ")
    val m = scanner.nextInt()
    println("Ingrese el valor de n: ")
    val n = scanner.nextInt()
    println("Ingrese el valor del area: ")
    val area = scanner.nextDouble()
    println("El volumen aproximado es ${calcularSumaDeRiemann(funcion, m, n, area)}")
}

fun calcularSumaDeRiemann(funcion: Expression, m: Int, n: Int, area: Double): Double {
    var result = 0.0
    for (i in 1..m) {
        funcion.setVariable("x", i.toDouble())
        for (j in 1..n) {
            funcion.setVariable("y", j.toDouble())
            result += funcion.evaluate() * area
        }
    }
    return result
}

