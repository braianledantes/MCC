package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class VolumenTrapecios {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una funcion de x e y: ");
        String exp = scanner.nextLine();
        expression = new ExpressionBuilder(exp)
                .variables("x", "y")
                .build();

        System.out.println("Ingrese el valor de a en x: ");
        double a = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de b en x: ");
        double b = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de c en y: ");
        double c = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de d en y: ");
        double d = getDouble(scanner.nextLine());
        System.out.println("Ingrese el valor de m en x: ");
        int m = scanner.nextInt();
        System.out.println("Ingrese el valor de n en y: ");
        int n = scanner.nextInt();
        try {
            double vol = volumenTapecios(a, b, c, d, m, n);
            System.out.println("Volumen aproximado en [" + a + "," + b + "],[" + c + "," + d + "] = " + vol);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static double getDouble(String s) {
        return Double.parseDouble(s);
    }

    public static double f(double x, double y) {
        expression.setVariable("x", x);
        expression.setVariable("y", y);
        return expression.evaluate();
    }

    static Expression expression;

    public static double volumenTapecios(double a, double b, double c, double d, int m, int n) throws Exception {
        if (n > 0 && m > 0 && a < b && c < d) {
            final double h = (b - a) / m;
            final double k = (d - c) / n;
            double y = c;
            double volAux = 0;

            for (int i = 1; i < n; i++) {
                y += k;
                volAux = volumenTapeciosAux(a, b, h, m, y);
            }
            return k * h / 4 * (volumenTapeciosAux(a, b, h, m, c) + (2 * volAux) + volumenTapeciosAux(a, b, h, m, y + k));
        } else
            throw new Exception("m y n deben ser mayor a cero");
    }

    public static double volumenTapeciosAux(double a, double b, double h, int m, double y) {
        double areaAux = 0;
        double x = a;
        for (int i = 1; i < m; i++) {
            x += h;
            areaAux += f(x, y);
        }
        return (f(a, y) + f(b, y) + (2 * areaAux));
    }

}
