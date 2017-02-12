/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

/**
 *
 * @author jiraw
 */
public class TestRecursion {

    public static void main(String[] args) {
        System.out.println("S(9) = " + series(9));
        System.out.println("S(10) = " + series(10));

        System.out.println("10! = " + fact(10));
        System.out.println("5! = " + fact(5));
    }

    private static int series(int n) {
        if (n > 1) {
            return n + series(n - 1);
        } else {
            return 1;
        }
    }

    private static int fact(int n) {
        if (n > 1) {
            return n * fact(n - 1);
        } else {
            return 1;
        }
    }

    private static String decToBin(int n) {
        if (n > 1) {
            return decToBin(n - 1) + n % 2;
        } else {
            return "" + n;
        }
    }
}
