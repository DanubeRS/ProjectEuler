package pr5;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/4/12 Time: 3:35 AM To change this template use File | Settings |
 * File Templates.
 */
public class Problem {

    public static void main(String[] args) {

        final int range = 20;

        int currentMin = range;
        boolean searching = true;
        masterLoop:
        do {
            for (int i = 1; i <= range; i++) {
                if (currentMin % (range + 1 - i) != 0) { break;}
                if ((range + 1 - i) == 1) {
                    searching = false;
                    break masterLoop;
                }
            }
            currentMin += range;
        } while (searching);

        System.out.println(currentMin);


    }
}
