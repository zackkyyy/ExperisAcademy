public class ChessBoard {
    private int[][] board;

    public ChessBoard() {
        board = new int[8][8];
    }

    public int[][] getBoard() {
        return board;
    }

    /**
     * Changes all the values that the queen can go to value 1
     * 
     * @param x
     * @param y
     * @author Jan Le
     */
    public void changeValue(int x, int y) {
        board[y][x] += 1;
    }

    public void backtrackChangeValue(int x, int y) {
        board[y][x] -= 1;
    }

    /**
     * Gets the value on given co-ordinate
     * 
     * @param x
     * @param y
     * @return
     * @author Jan Le
     */
    public int getValue(int x, int y) {
        return board[y][x];
    }

    /**
     * Counts available value 1 on the board
     * 
     * @return
     * @author Jan Le
     */
    public int getCountMarks() {
        int countMarks = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] >= 1) {
                    countMarks++;
                }
            }
        }

        return countMarks;
    }
}