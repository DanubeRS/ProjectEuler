package pr1;

/**
 * Created with IntelliJ IDEA. User: Daniel Date: 11/3/12 Time: 7:55 PM * ==================== * This is an
 * implementation of problem 1 in the ProjectEuler.net set. See http://projecteuler.net/problem=1 *
 * ====================
 */
public class Problem {

    private int upperLimit, sum = 0;
    private int[] multiples;

    //Construct the Problem1 Object
    public Problem(int u, int[] m) {
        upperLimit = u;
        multiples = m;
    }

    //Calculate the sum
    public int Calculate() {

        //For each number from 1 to the upper limit, test for multiplicity, and add if true
        for (int i = 1; i < upperLimit; i++) {
            if (isMultiple(multiples, i)) {
                sum += i;
            }
        }
        return sum;

    }

    private boolean isMultiple(int[] mul, int subject) {
        for (int i = 0; i < mul.length; i++) {
            if (subject % mul[i] == 0) {return true;}
        }
        return false;
    }

    public static void main(String[] args) {

        pr1.Problem pr1 = new pr1.Problem(1000, new int[]{3, 5});
        System.out.println("Multiple addition:");
        System.out.println(pr1.Calculate());

    }


}
