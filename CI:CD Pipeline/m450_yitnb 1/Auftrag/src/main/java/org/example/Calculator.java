package org.example;

public class Calculator {

    public static void main(String[] args){
        Calculator calculator = new Calculator();

        double sum = calculator.add(5, 3);
        double difference = calculator.subtract(10, 5);
        double product = calculator.multiply(4, 2);
        double quotient = calculator.divide(9, 3);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
    }

    public double add(double summand1, double summand2) {
        return summand1 + summand2;
    }

    public double subtract(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }

    public double multiply(double factor1, double factor2) {
        return factor1 * factor2;
    }

    public double divide(double dividend, double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return dividend / divisor;
    }
}
