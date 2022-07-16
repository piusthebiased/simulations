package algo.perm.tests;

import algo.perm.MatrixInt;

public class impl {
    public static void main(String[] args) {
        int[][] x = new int[3][2];

        int count = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                x[i][j] = count++;
            }
        }
        MatrixInt m = new MatrixInt(x);
    }
}
