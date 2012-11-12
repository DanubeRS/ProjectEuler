package pr12;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/7/12 Time: 3:19 PM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        TriangleNumber triangle = new TriangleNumber();

        /** Test for generation of triangle numbers specified in the problem
         for(int i = 1; triangle.getNumber() < 56; i++){
         System.out.println(triangle.getNumber());
         triangle.incrementNumber();
         }
         */

        //Increment the number until we remove total impossibility of having 500 factors
        // I.e; 499 will never have any more than 500 distinct factors;
        while (triangle.getNumber() < 500) {
            triangle.incrementNumber();
        }

        while (true) {

            if (FactorTool.numberOfFactors(triangle.getNumber()) > 500) {
                break;
            }

            triangle.incrementNumber();
        }
        System.out.println(triangle.getNumber() + " is the lowest number to have over 500 divisors with exactly "
                + FactorTool.numberOfFactors(triangle.getNumber()));


    }

}
