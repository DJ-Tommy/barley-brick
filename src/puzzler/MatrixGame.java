package puzzler;

import java.util.Arrays;

public class MatrixGame {
    private static Coord size;
    private static int count;
    private static int [] [] matrix;
    private static int [] [] matrix_original;
    private static int arg;
    private static Coord zero;
    private static int count_moves = 0;
    private static final int[] moves = {0, 3, 6, 9};
    public static int count_mix;

    public static int getCount_moves() {
        return count_moves;
    }

    public static void setCount_moves() {
        count_moves++;
    }

    public MatrixGame() {
        matrix = new int[size.x][size.y];
        matrix_original = new int[size.x][size.y];
    }


    public static void setSize(Coord coord) {
        size = coord;
    }

    public static Coord getSize() {
        return size;
    }

    public static int getMatrix(Coord coord) {
        return matrix[coord.x][coord.y];
    }

    public static void setMatrix(Coord coord, int val) {
        matrix [coord.x][coord.y] = val;
    }

    public static void moveMouse(Coord coord, boolean setting) {
        if (gameOver()) return;
        if (matrix[coord.x][coord.y] != 0) {
            if ((coord.x + 1) < size.x && matrix[coord.x + 1][coord.y] == 0) {
                moveMatrix(new Coord(coord.x, coord.y), new Coord(coord.x + 1, coord.y));
            }

            if ((coord.x - 1) >= 0 && matrix[coord.x - 1][coord.y] == 0) {
                moveMatrix(new Coord(coord.x, coord.y), new Coord(coord.x - 1, coord.y));
            }

            if ((coord.y + 1) < size.y && matrix[coord.x][coord.y + 1] == 0) {
                moveMatrix(new Coord(coord.x, coord.y), new Coord(coord.x, coord.y + 1));
            }

            if ((coord.y - 1) >= 0 && matrix[coord.x][coord.y - 1] == 0) {
                moveMatrix(new Coord(coord.x, coord.y), new Coord(coord.x, coord.y - 1));
            }
        }
    }

    public static void moveMatrix(Coord coord1, Coord coord2) {
        arg = matrix[coord1.x][coord1.y];
        matrix[coord1.x][coord1.y] = matrix[coord2.x][coord2.y];
        matrix[coord2.x][coord2.y] = arg;
        zero = new Coord(coord1.x, coord1.y);
        setCount_moves();
    }

    public static boolean gameOver() {
        if (Arrays.deepEquals(matrix, matrix_original)) {
            return true;
        }
        return false;
    }

    public static void moveKeyboard(int key, boolean setting) {
        if (gameOver() && setting) return;
        if (key == 6) {
            if (zero.y - 1 >= 0) {
                moveMatrix(new Coord(zero.x, zero.y - 1), new Coord(zero.x, zero.y));
            }
        }

        if (key == 0) {
            if (zero.y + 1 < size.y) {
                moveMatrix(new Coord(zero.x, zero.y + 1), new Coord(zero.x, zero.y));
            }
        }

        if (key == 9) {
            if (zero.x + 1 < size.x) {
                moveMatrix(new Coord(zero.x + 1, zero.y), new Coord(zero.x, zero.y));
            }
        }

        if (key == 3) {
            if (zero.x - 1 >= 0) {
                moveMatrix(new Coord(zero.x - 1, zero.y), new Coord(zero.x, zero.y));
            }
        }
    }

    public static void mixStart() {
        count = 0;
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                count++;
                setMatrix(new Coord(x,y), count);
                matrix_original [x][y] = count;
                if (count >= size.x * size.y) {
                    setMatrix(new Coord(x,y), 0);
                    matrix_original [x][y] = 0;

                    zero = new Coord(x, y);
                }
            }
        }
        for (int ii = 0; ii < 300; ii++) {
            int t = (int) (Math.random() * 4);
            if (t > 3) t = 3;
            moveKeyboard(moves[t], false);
        }
        count_mix = count_moves;
        count_moves = 0;


    }

}
