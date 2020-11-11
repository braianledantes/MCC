package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class VolumenSimpson13 {

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
            double vol = volSimpson13(a, b, c, d, m, n);
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

    public static double volSimpson13(double a, double b, double c, double d, int m, int n) throws Exception {
        if (m >= 2 && (m % 2) == 0 && n >= 2 && (n % 2) == 0) {
            double h = (b - a) / m;
            double k = (d - c) / n;

            double Iimpar = 0;
            double Ipar = 0;
            double y = c;

            double I0 = volSimpson13Aux(a, b, h, m, c);
            for (int i = 1; i < n; i++) {
                y += k;
                if (i % 2 == 1) {
                    Iimpar += volSimpson13Aux(a, b, h, m, y);
                } else {
                    Ipar += volSimpson13Aux(a, b, h, m, y);
                }
            }
            double In = volSimpson13Aux(a, b, h, m, d);

            return k * h / 9 * (I0 + (4 * Iimpar) + (2 * Ipar) + In);
        } else {
            throw new Exception("Se necesita que n y m sean mayor a dos y par para que la cantidad de volumenes sea impar, m = " + m + ", n = " + n);
        }
    }

    public static double volSimpson13Aux(double a, double b, double h, int m, double y) {
        double areaImpar = 0;
        double areaPar = 0;
        double x = a;

        for (int i = 1; i < m; i++) {
            x += h;
            if (i % 2 == 1) {
                areaImpar += f(x, y);
            } else {
                areaPar += f(x, y);
            }
        }

        return f(a, y) + f(b, y) + (4 * areaImpar) + (2 * areaPar);
    }
}
