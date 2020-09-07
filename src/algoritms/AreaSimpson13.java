package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class AreaSimpson13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una funcion de x: ");
        String exp = scanner.nextLine();
        expression = new ExpressionBuilder(exp)
                .variable("x")
                .build();

        System.out.println("Ingrese el valor de a: ");
        double a = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de b: ");
        double b = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de n: ");
        double n = getDouble(scanner.nextLine());
        try {
            double raiz = areaSimpson13(a, b, n);
            System.out.println("Raíz aproximada en x = " + raiz);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static double getDouble(String s) {
        return Double.parseDouble(s);
    }

    public static double f(double x) {
        expression.setVariable("x", x);
        return expression.evaluate();
    }

    static Expression expression;

    public static double areaSimpson13(double a, double b, double n) throws Exception {
        if (n >= 2 && (n % 2) == 0) {
            double h = (b - a) / n;
            double areaImpar = 0;
            double areaPar = 0;
            double xi = a;

            for (int i = 1; i < n; i++) {
                xi = xi + h;
                if (i % 2 == 1) {
                    areaImpar += f(xi);
                } else {
                    areaPar += f(xi);
                }
            }

            return h / 3 * (f(a) + f(b) + (4 * areaImpar) + (2 * areaPar));
        } else {
            throw new Exception("Se necesita que n sea mayor a dos y  par para que la cantidad de areas sea impar, n = " + n);
        }
    }

}
