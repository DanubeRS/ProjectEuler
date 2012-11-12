package pr012;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/7/12 Time: 3:19 PM To change this template use File | Settings |
 * File Templates.
 */
public class TriangleNumber {

    private int count;
    private long number;


    public int getCount() {
        return count;
    }

    public TriangleNumber() {

        count = 1;
        number = 1;

    }

    public long getNumber() {
        return number;
    }


    // The incrementor adds the next in the natural number sequence to the triangle number
    public long incrementNumber() {
        count++;
        number += count;
        return number;
    }

}
