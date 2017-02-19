/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.from.other;

import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author jiraw
 */
public class InfixToPostfix {

    public static void main(String[] args) {
        String exp = "( 16 * ( 13 + 5 ) ) * 2 - 12 % ( 4 + 8 )";
        double result = calculateinfix(exp);
        System.out.println("Result " + result);
    }

    private static double calculateinfix(String exp) {
        LinkedList<String> stackOperator = new LinkedList<>();
        LinkedList<Double> stackOperand = new LinkedList<>();
        double result = 0.0;
        StringTokenizer stkn = new StringTokenizer(exp);
        while (stkn.hasMoreElements()) {
            String token = (String) stkn.nextElement();
            if (stackOperator.isEmpty() && getTokenType(token).equalsIgnoreCase("operator")) {
                stackOperator.push(token);
            } else if (!stackOperator.isEmpty() && getTokenType(token).equalsIgnoreCase("operator")) {
                if (precedence(token) > precedence(stackOperator.peek())) {
                    stackOperator.push(token);
                } else if (precedence(token) <= precedence(stackOperator.peek())) {
                    while (!stackOperator.isEmpty() && !getTokenType(stackOperator.peek()).equalsIgnoreCase("left-parenthesis")) {
                        if (precedence(token) <= precedence(stackOperator.peek())) {
                            double secondOperand = stackOperand.pop();
                            double firstOperand = stackOperand.pop();
                            stackOperand.push(calculate(stackOperator.pop(), firstOperand, secondOperand));
                        }
                    }
                    stackOperator.push(token);
                }
            } else if (getTokenType(token).contains("parenthesis")) {
                if (getTokenType(token).equalsIgnoreCase("left-parenthesis")) {
                    stackOperator.push(token);
                } else if (getTokenType(token).equalsIgnoreCase("right-parenthesis")) {
                    while (!getTokenType(stackOperator.peek()).equalsIgnoreCase("left-parenthesis")) {
                        double secondOperand = stackOperand.pop();
                        double firstOperand = stackOperand.pop();
                        stackOperand.push(calculate(stackOperator.pop(), firstOperand, secondOperand));
                    }
                    if (getTokenType(stackOperator.peek()).equalsIgnoreCase("left-parenthesis")) {
                        stackOperator.pop();
                    }
                }
            } else if (getTokenType(token).equalsIgnoreCase("operand")) {
                stackOperand.push(Double.parseDouble(token));
            }
        }
        while (!stackOperator.isEmpty()) {
            double secondOperand = stackOperand.pop();
            double firstOperand = stackOperand.pop();
            result += calculate(stackOperator.pop(), firstOperand, secondOperand);
            stackOperand.push(result);
        }
        return result;
    }

    private static double calculate(String operator, double operand1, double operand2) {
        double result = 0.0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }
        return result;
    }

    private static String getTokenType(String token) {
        String type = "operand";
        switch (token) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                type = "operator";
                break;
            case "(":
                type = "left-parenthesis";
                break;
            case ")":
                type = "right-parenthesis";
                break;
        }
        return type;
    }

    private static boolean isHigher(String op1, String op2) {
        return precedence(op1) > precedence(op2);
    }

    private static int precedence(String op) {
        int precValue = 0;
        switch (op) {
            case "+":
            case "-":
                precValue = 2;
                break;
            case "*":
            case "/":
            case "%":
                precValue = 4;
                break;
            case "(":
            case ")":
                precValue = 0;
                break;
        }
        return precValue;
    }
}
