package algo.perm;

public class MatrixInt {
    private int[][] data = null;
    private int rows = 0, cols = 0;

    public MatrixInt(int rows, int cols) {
        data = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public MatrixInt(int[][] data) {
        this.data = data.clone();
        rows = this.data.length;
        cols = this.data[0].length;
    }

    public boolean isSquare() {
        return rows == cols;
    }

    public void display() {
        System.out.print("[");
        for (int row = 0; row < rows; ++row) {
            if (row != 0) {
                System.out.print(" ");
            }

            System.out.print("[");

            for (int col = 0; col < cols; ++col) {
                System.out.printf("%8.3f", data[row][col]);

                if (col != cols - 1) {
                    System.out.print(" ");
                }
            }

            System.out.print("]");

            if (row == rows - 1) {
                System.out.print("]");
            }

            System.out.println();
        }
    }

    public MatrixInt transpose() {
        MatrixInt result = new MatrixInt(cols, rows);

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                result.data[col][row] = data[row][col];
            }
        }

        return result;
    }

    // Note: exclude_row and exclude_col starts from 1
    public static MatrixInt subMatrix(MatrixInt matrix, int exclude_row, int exclude_col) {
        MatrixInt result = new MatrixInt(matrix.rows - 1, matrix.cols - 1);

        for (int row = 0, p = 0; row < matrix.rows; ++row) {
            if (row != exclude_row - 1) {
                for (int col = 0, q = 0; col < matrix.cols; ++col) {
                    if (col != exclude_col - 1) {
                        result.data[p][q] = matrix.data[row][col];

                        ++q;
                    }
                }

                ++p;
            }
        }

        return result;
    }
}
