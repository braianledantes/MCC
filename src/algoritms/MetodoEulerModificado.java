package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class MetodoEulerModificado {
    private static Expression derivada;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la funcion derivada:");
        String exp1 = scanner.nextLine();
        System.out.println("Ingrese el valor de x0:");
        double x0 = scanner.nextDouble();
        System.out.println("Ingrese el valor de y0:");
        double y0 = scanner.nextDouble();
        System.out.println("Ingrese el incremento de x:");
        double h = scanner.nextDouble();
        System.out.println("Ingresela cantidad de puntos:");
        int cantPuntos = scanner.nextInt();
        System.out.println("Ingresela cantidad de decimales:");
        int cantDecimales = scanner.nextInt();

        derivada = new ExpressionBuilder(exp1)
                .variables("x", "y")
                .build();

        metodoEulerModificado(x0, y0, h, cantPuntos, cantDecimales);
    }

    private static void metodoEulerModificado(double x0, double y0, double h, int cantPuntos, int cantDecimales) {
        double xi = x0;
        double yi = y0;
        double yiAux;
        double yi_mas_1;

        double fxy;
        double fxy_aux;

        for (int i = 0; i < cantPuntos; i++) {
            fxy = D(xi, yi);

            //yi_mas_1 = yi + (h *);

            yiAux = redondear(yi + (h / 2 * fxy), cantDecimales);

            System.out.println("y${i + 1} " +
                    "= y$i + (h/2 * (D(x${i}, y${i}) + D(x${i}+1, y${i}+1))) " +
                    "= $yi + (${h / 2} * (D($xi, $yi) + D($xi, $yi))) = $yiAux");

            xi = redondear(xi + h, cantDecimales);
            yi = yiAux;
        }
    }

    private static double D(double x, double y) {
        derivada.setVariable("x", x);
        derivada.setVariable("y", y);
        return derivada.evaluate();
    }

    private static double redondear(double num,int decimales){
        BigDecimal redondeado = new BigDecimal(num)
                .setScale(decimales, RoundingMode.HALF_EVEN);
        return redondeado.doubleValue();
    }
}
