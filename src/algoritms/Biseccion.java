package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Biseccion {

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
        System.out.println("Ingrese el valor del error: ");
        double error = getDouble(scanner.nextLine());
        double raiz = biseccion(a, b, error);
        System.out.println("Raíz aproximada en x = " + raiz);
    }

    public static double getDouble(String s) {
        return Double.parseDouble(s);
    }

    public static double f(double x) {
        expression.setVariable("x", x);
        return expression.evaluate();
    }

    static Expression expression;

    public static double biseccion(double a, double b, double error) throws IllegalArgumentException {
        double r;
        if (f(a) * f(b) < 0) {
            do {
                r = (a + b) / 2;

                System.out.println("+-------+");
                System.out.println("a = " + a);
                System.out.println("r = " + r);
                System.out.println("b = " + b);

                System.out.println("f(a) = " + f(a));
                System.out.println("f(r) = " + f(r));
                System.out.println("f(b) = " + f(b));

                if (f(a) * f(r) < 0) {
                    b = r;
                } else {
                    a = r;
                }

                System.out.println("|a-b| = " + Math.abs(a - b));
                System.out.println("|f(r)| = " + Math.abs(f(r)));
            } while (Math.abs(f(r)) > error); //  Condición de corte del algoritmo. Puede modificarse por abs(b-a)≤E

            return  r;
        } else {
            throw new IllegalArgumentException("No se cumple la condición inicial f(a)*f(b) < 0");
        }
    }
}
