package pr004;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/4/12 Time: 2:46 AM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        //Initiate a list of all 3-digit numbers
        int[] numbers = new int[(999 - 100 + 1)];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 100 + i;
            System.out.println(numbers[i]);
        }

        //Create a nested loop containing a product of all elements in the form of a cross product

        isPalindrome(10001);

        int maxPal = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {

                int currentProduct = numbers[i] * numbers[j];
                if (currentProduct > maxPal && isPalindrome(currentProduct)) {
                    maxPal = currentProduct;
                }

            }
        }

        System.out.println(maxPal);

    }

    private static boolean isPalindrome(int val) {

        int[] temp = flipInt(val);
        if (temp[0] == temp[1]) {
            return true;
        }
        return false;


    }

    private static int[] flipInt(int i) {
        List<Integer> digitsF = new ArrayList<Integer>();
        List<Integer> digitsB = new ArrayList<Integer>();
        while (i > 0) {
            digitsF.add(i % 10);
            i /= 10;
        }

        for (int j = digitsF.size(); j > 0; j--) {
            digitsB.add(digitsF.get(j - 1));
        }


        return new int[]{toInt(digitsF), toInt(digitsB)};
    }


    private static int toInt(List<Integer> digitsList) {
        int returnInt = 0;
        for (int pos = 0; pos < digitsList.size(); pos++) {
            returnInt += digitsList.get(pos) * Math.pow(10, pos);
        }
        return returnInt;

    }


}
