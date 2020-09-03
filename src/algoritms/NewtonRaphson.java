package algoritms;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class NewtonRaphson {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese una funcion f(x): ");
        String exp1 = scanner.nextLine();
        funcion = new ExpressionBuilder(exp1)
                .variable("x")
                .build();

        System.out.println("Ingrese una funcion derivada f'(x): ");
        String exp2 = scanner.nextLine();
        funcionDerivada = new ExpressionBuilder(exp2)
                .variable("x")
                .build();

        System.out.println("Ingrese el valor de x0: ");
        double x0 = getDouble(scanner.nextLine());

        System.out.println("Ingrese el valor del error: ");
        double error = getDouble(scanner.nextLine());

        try {
            double raiz = newtonRaphson(x0, error);
            System.out.println("Raíz aproximada en x = " + raiz);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static double getDouble(String s) {
        return Double.parseDouble(s);
    }

    public static double f(double x) {
        funcion.setVariable("x", x);
        return funcion.evaluate();
    }

    public static double fd(double x) {
        funcionDerivada.setVariable("x", x);
        return funcionDerivada.evaluate();
    }

    static Expression funcion, funcionDerivada;

    public static double newtonRaphson(double x0, double error) throws Exception {
        double x1 = x0;
        double fdx0;
        int iteracionesMaximas = 100;
        int i = 1; // para que no itere infinitamente
        while (Math.abs(f(x1)) > error && i < iteracionesMaximas) {
            fdx0 = fd(x0);

            System.out.println("(" + i + "/" + iteracionesMaximas + ")+------------+");
            System.out.println("x0 = " + x0);
            System.out.println("f(x0) = " + f(x0));
            System.out.println("f'(x0) = " + fdx0);

            if (fdx0 == 0) {
                throw new Exception("Error en derivada f(" + x0 + ") = " + fdx0);
            }
            x1 = x0 - f(x0) / fdx0;
            x0 = x1;

            i++;

            System.out.println("x1 = " + x1);
            System.out.println("f(x1) = " + f(x1));
        }

        if (i >= iteracionesMaximas) {
            throw new Exception("Supero la cantidad maxima de iteraciones");
        }
        return x1;
    }
}
