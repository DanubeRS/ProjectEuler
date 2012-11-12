package pr012;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/7/12 Time: 3:28 PM To change this template use File | Settings |
 * File Templates.
 */
public class FactorTool {

    public static int numberOfFactors(long n) {

        long floor = (long) Math.floor(Math.sqrt(n));
        int factorCount = 0;

        for (int i = 1; i < floor; i++) {

            if ((double) n % (double) i == 0) {
                factorCount++;
            }

        }
        return factorCount * 2;
    }

    private FactorTool() {
        throw new AssertionError();
    }

}
