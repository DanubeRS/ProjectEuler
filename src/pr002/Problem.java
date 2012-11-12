package pr002;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/3/12 Time: 10:56 PM * ==================== * This is an
 * implementation of problem 1 in the ProjectEuler.net set. See http://projecteuler.net/problem=1 *
 * ====================
 */

public class Problem {

    private int fibLength;

    public Problem(int l) {
        //Empty Constructor
        fibLength = l;
    }

    public int calculate() {

        //Default starting values (constant)
        int
                first = 1,
                second = 2,
                sum = 2;

        for (int i = 0; second < fibLength; i++) {

            //Bump all sequence values using a temporary value
            int temp = first + second;
            first = second;
            second = temp;

            //Check if divisible by 2 (aka, even)
            if (second % 2 == 0) {
                sum += second;  //If even, add to sum
            }

        }
        return sum;


    }

    public static void main(String[] args) {

        //Create new Problem object, and run its calculate method
        Problem pr2 = new Problem(new BigDecimal("4E6").intValue());
        System.out.println("Fibbonaci addition:");
        System.out.println(pr2.calculate());
    }

}
