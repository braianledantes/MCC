package algoritms

import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("Ingrese una funcion f(x,y):")
    val exp1 = scanner.nextLine()
    val funcion = ExpressionBuilder(exp1)
            .variables("x", "y")
            .build()

    println("Ingrese el valor de m:")
    val m = scanner.nextInt()
    println("Ingrese el valor de n:")
    val n = scanner.nextInt()
    println("Ingrese el valor del rango inicial en x:")
    val rangox1 = scanner.nextDouble()
    println("Ingrese el valor del rango final en x:")
    val rangox2 = scanner.nextDouble()
    println("Ingrese el valor del rango inicial en y:")
    val rangoy1 = scanner.nextDouble()
    println("Ingrese el valor del rango final en y:")
    val rangoy2 = scanner.nextDouble()

    println("El volumen aproximado es ${calcularSumaDeRiemann(funcion, m, n, rangox1, rangox2, rangoy1, rangoy2)}")
}

fun calcularSumaDeRiemann(funcion: Expression, m: Int, n: Int, rangox1: Double, rangox2: Double, rangoy1: Double, rangoy2: Double): Double {
    var result = 0.0
    // calculo el lado del area
    val ladox = (rangox2 - rangox1) / m
    val ladoy = (rangoy2 - rangoy1) / n

    val area = ladox * ladoy
    // comienza a iterar
    var x = 0.0
    var y: Double
    for (i in 1..m) {
        x += ladox
        y = 0.0
        funcion.setVariable("x", x)
        for (j in 1..n) {
            y += ladoy
            funcion.setVariable("y", y)
            result += funcion.evaluate()
        }
    }
    return result * area
}

