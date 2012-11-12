package pr001;

/**
 * @author Daniel J. Park DanubeRS@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class Problem {

    //Define assisting, and operational variables
    private int upperLimit, sum = 0;
    private int[] multiples;        //To be populated with final listing of multiples

    /**
     * Creates a Problem object with specified parameters.
     *
     * @param u
     *         Upper Limit
     * @param m
     *         List of common multiples.
     *
     * @since 1.0
     */
    public Problem(int u, int[] m) {
        upperLimit = u;
        multiples = m;
    }

    /**
     * Invoked from a Problem object. Tests if in multiples list, and if true, adds to the sum.
     *
     * @return Sum of included multiples
     */
    public int Calculate() {

        //For each number from 1 to the upper limit, test for multiplicity, and add if true
        for (int i = 1; i < upperLimit; i++) {
            if (isMultiple(i)) { //Tests for multiplicity
                sum += i;
            }
        }
        return sum;

    }

    /**
     * Private method tests if a passed int contains a multiple.
     *
     * @param subject
     *         Integer to be tested
     *
     * @return True of multiple, false otherwise
     */
    private boolean isMultiple(int subject) {
        for (int multiple : multiples) {
            if (subject % multiple == 0) {return true;} // If modulus is zero, then the value is a whole factor
        }
        return false;
    }

    /**
     * The main running class. Invoked on program start
     *
     * @param args
     *         Command line arguments (Not required)
     */
    public static void main(String[] args) {

        pr001.Problem pr1 = new pr001.Problem(1000, new int[]{3, 5});   //Initialise new problem object

        //Console Output
        System.out.println("Multiple addition:");
        System.out.println(pr1.Calculate());

    }

}
