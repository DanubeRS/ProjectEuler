package pr011;

/**
 * Created with IntelliJ IDEA. User: Danie Date: 11/7/12 Time: 6:04 PM To change this template use File | Settings |
 * File Templates.
 */
public class Tools {


    public enum DirectionOfProduct {ACROSS, DOWN, DIAGONAL}

    public static int[][] splitTextMatrix(String raw, String row, String col) {
        String[] rows = raw.split(row);
        String[][] seperated = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            seperated[i] = rows[i].split(col);
        }

        //Provision a new int array the same size as the seperated array

        int[][] seperatedInt = new int[seperated.length][seperated[1].length];

        for (int i = 0; i < seperated.length; i++) {
            for (int j = 0; j < seperated[1].length; j++) {
                seperatedInt[i][j] = Integer.parseInt(seperated[i][j]);
            }
        }
        return seperatedInt;
    }

    private Tools() {
        throw new AssertionError();
    }


    public static int maxAdjacentProduct(int[][] matrix, DirectionOfProduct direction) {


        int iChange = 0, jChange = 0;

        switch (direction) {
            case ACROSS:
                iChange = 1;
                break;
            case DOWN:
                jChange = 1;
                break;
            case DIAGONAL:
                iChange = jChange = 1;
                break;
            default:
                System.out.println("Invalid Direction");
                throw new IllegalArgumentException("Argument must be of format 'DOWN', 'ACROSS' , or 'DIAGONAL'");
                break;
        }

        for (int iIterator = 0; iIterator < getMatrixDimensions(matrix)[1])

    }

    private static int[] getMatrixDimensions(int[][] matrix) {

        int matrixHeight = matrix.length;
        int matrixWidth = matrix[1].length;

        return new int[]{matrixHeight, matrixWidth};

    }
}
