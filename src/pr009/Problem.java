package pr009;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/5/12 Time: 8:43 PM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        /**
         * It is known that a pithagorean triplet can be made with Eucliuds formula;
         * i.e: for m & n ( m > n) :-
         * a = m^2 - n^2,
         * b = 2mn
         * c = m^2 + n^2
         *
         * Using this formula, we can use algebra to find whole number values for m given an iterated n;
         * i.e: n = (1000-2m^2) / 2m
         */

        int finalM = 0, finalN = 0;

        for (int m = 1; m < Integer.MAX_VALUE; m++) {

            double n = (1000 - 2 * Math.pow(m, 2)) / (2 * m);

            double a = m * m + n * n;
            if (a == 375 || a == 200 || a == 425) {System.out.println(a + "When" + n + " and " + m);}


            if (n < m && (n > 0) && (m - n) % 2 == 1 && isWhole(n) && isGCD(m, (int) n)) {
                finalN = (int) n;
                finalM = m;
                break;
            }
        }

        int a = (int) Math.pow(finalM, 2) - (int) Math.pow(finalN, 2);
        int b = 2 * finalM * finalN;
        int c = (int) Math.pow(finalM, 2) + (int) Math.pow(finalN, 2);

        System.out.println("A: " + a + " B: " + b + " C: " + c);
        System.out.println("Product: " + (a * b * c));

    }

    private static boolean isGCD(int a, int b) {

        int GCD = getGCD(a, b);
        if (GCD == 1 || GCD == a || GCD == b) {
            return true;
        }
        return false;
    }

    private static int getGCD(int a, int b) {
        int x = 0, y = 0;

        if (a > b) {
            x = a;
            y = b;
        } else {
            x = b;
            y = a;
        }

        while (x % y != 0) {
            int temp = x;
            x = y;
            y = temp % x;
        }
        return y;
    }

    public static boolean isWhole(double i) {

        return (i == (int) i);

    }

}
