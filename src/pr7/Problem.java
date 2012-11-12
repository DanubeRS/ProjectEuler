package pr7;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/4/12 Time: 10:01 PM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        // Iterate through all primes until the 10 001st is reached
        int findPos = 10001;
        int number = 2;
        int count = 0;

        while (true) {
            if (count == findPos) {break;}
            if (primeTest(number)) {System.out.println("[" + count + "] " + number); count++; number++;} else {
                number++;
            }
        }
        System.out.println(number - 1);

    }

    private static boolean primeTest(int n) {

        if (n == 1 || n == 2) {return true;}

        int floor = (int) Math.floor(Math.sqrt(n));

        for (int i = 2; i <= floor; i++) {
            double test = ((double) n / (double) i);
            if (test == (int) test) {return false;}
        }
        return true;

    }
}
