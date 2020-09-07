package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class AreaTapecios {

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
            double raiz = areaTapecios(a, b, n);
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

    public static double areaTapecios(double a, double b, double n) throws Exception {
        if (n > 0) {
            double h = (b - a) / n;
            double xi = a;
            double areaAux = 0;

            for (int i = 1; i < n; i++) {
                xi = xi + h;
                areaAux += f(xi);
            }

            return h / 2 * (f(a) + f(b) + (2 * areaAux));
        } else
            throw new Exception("n debe ser un numero mayor a cero");
    }
}
