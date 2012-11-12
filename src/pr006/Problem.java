package pr006;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/4/12 Time: 3:53 AM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        int range = 100;

        int sum = 0, sumSq = 0;

        for (int i = 1; i <= range; i++) {
            sum += i;
            sumSq += (int) Math.pow(i, 2);
        }
        int sqSum = (int) Math.pow(sum, 2);

        System.out.println("Sum of Squares = " + sumSq + " | Sq of Sums = " + sqSum);
        System.out.println("Difference = " + Math.abs(sumSq - sqSum));
    }

}
