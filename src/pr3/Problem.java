package pr3;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/4/12 Time: 1:00 AM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        double startTime = System.nanoTime();
        double totalStartTime = startTime;
        long n = 600851475143L;

        //Find the floor of our factor tests:
        long floor = (long) Math.floor(Math.sqrt(n));

        // Check if the square root is within integer bounds for sake of array indexing
        if (floor > Integer.MAX_VALUE) {
            System.out.println("Number too large for algorithm");
            System.exit(-1);
        }

        int[] numbers = new int[(int) floor];
        boolean[] arePrime = new boolean[(int) floor];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
            arePrime[i] = true;
        }
        DecimalFormat df = new DecimalFormat("##.####");
        System.out.println("Done in " + df.format((float) ((System.nanoTime() - startTime) / new BigDecimal("1E9")
                .intValue())) + "s");

        startTime = System.nanoTime();
        //Sieve Algorithm
        for (int i = 2; i < floor; i++) {

            if (arePrime[i - 1]) {
                for (int j = (2 * i) - 1; j < arePrime.length; j += i) {
                    arePrime[j] = false;
                }
            }

        }

        System.out.println("Done in " + df.format((float) ((System.nanoTime() - startTime) / new BigDecimal("1E9")
                .intValue())) + "s");

        //Recreate array removing all non-primal numbers;
        List<Long> prime = new ArrayList<Long>();

        for (int i = 0; i < arePrime.length; i++) {
            if (arePrime[i]) {
                prime.add(n / numbers[i]);  // As we are working with factors on the larger side,
                // they themselves are factors.
            }
        }

        List<Integer> factorPrimes = new ArrayList<Integer>();

        for (Long el : prime) {
            double tempDivision = n / (double) el;
            if (tempDivision == (int) tempDivision) {
                factorPrimes.add((int) tempDivision);
            }
        }


        System.out.println(factorPrimes.get(factorPrimes.size() - 1) + " is the largest factor. ");
        System.out.println("Completed in " + df.format((float) ((System.nanoTime() - totalStartTime) / new BigDecimal
                ("1E9").intValue())) + "s");


    }


}
