package pr010;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/7/12 Time: 2:46 PM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    private static boolean isPrime(int a) {

        //Test for primality given naive iterative means

        if (a == 1) {
            return false;
        }

        int floor = (int) Math.floor(Math.sqrt((a)));

        for (int i = 2; i <= floor; i++) {
            if (a % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        //Iterative sum of all primes
        long sum = 0;

        //i is the iterate between 1 and 2E9
        for (int i = 1; i < 2000000; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }

        System.out.print("Sum of all primes below 2 million: " + sum);

    }


}
