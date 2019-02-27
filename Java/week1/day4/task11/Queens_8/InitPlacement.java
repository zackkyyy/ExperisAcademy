import java.util.ArrayList;

public class InitPlacement {
    private ArrayList<Queen> queenList = new ArrayList<>();
    private ChessBoard chessBoard;
    // private ArrayList<Integer[][]> boardList = new ArrayList<>();

    public InitPlacement() {
        chessBoard = new ChessBoard();
        // placeQueen(0, 0);
    }

    /**
     * Trying to solve queens 8 with one queen placement
     * 
     * @param col Recursiv col
     * @return True if it finds a solution | false if it does not find a solution
     * @author Kenneth Ngo, Quoc Viet Tran, Jan Le
     */
    public boolean solve(int col) {
        boolean checker = false;
        boolean skip;

        for (int i = 0; i < 8; i++) {
            skip = false;
            // Check if queen exists on the row
            for (Queen q : queenList) {
                if (q.getX() == i && q.getY() == col) {
                    skip = true;
                    solve(col + 1);
                    break;
                }
            }
            // Skip if queen exist
            if (!skip && col < 8) {
                if (placeQueen(i, col)) {
                    if (solve(col + 1) == true) {
                        return true;
                    }
                }
            }
        }
        // Backtrack queen
        if (queenList.size() != 8 && queenList.size() > 1 && col < 8) {

            Queen removeQueen = queenList.get(queenList.size() - 1);
            backtrackQueensRoute(removeQueen.getX(), removeQueen.getY());
            chessBoard.backtrackChangeValue(removeQueen.getX(), removeQueen.getY());
            queenList.remove(removeQueen);
            return false;
        }
        // Return false if it does not find a solution
        if (queenList.size() == 1)
            return false;
        return true;
    }

    /**
     * Places queen on the chessboard if possible
     * 
     * @param x
     * @param y
     * @return boolean
     * @author Jan Le
     */
    public boolean placeQueen(int x, int y) {
        if (chessBoard.getValue(x, y) == 0) {
            queenList.add(new Queen(x, y));
            chessBoard.changeValue(x, y);
            markQueensRoute(x, y);
            return true;
        }

        return false;
    }

    /**
     * Marks all the possible slots that the queen can take
     * 
     * @param x
     * @param y
     * @author Jan Le
     */
    private void markQueensRoute(int x, int y) {
        // Horizontal
        for (int i = 0; i < 8; i++) {
            chessBoard.changeValue(i, y);
        }

        // Vertical
        for (int i = 0; i < 8; i++) {
            chessBoard.changeValue(x, i);
        }

        // Diagonal from upper left to lower right
        int temp_x = x;
        int temp_y = y;

        while (temp_x < 8 && temp_y < 8) {
            chessBoard.changeValue(temp_x, temp_y);
            temp_x++;
            temp_y++;
        }

        temp_x = x;
        temp_y = y;

        while (temp_x >= 0 && temp_y >= 0) {
            chessBoard.changeValue(temp_x, temp_y);
            temp_x--;
            temp_y--;
        }

        // Diagonal from lower left to upper right
        temp_x = x;
        temp_y = y;

        while (temp_x < 8 && temp_y >= 0) {
            chessBoard.changeValue(temp_x, temp_y);
            temp_x++;
            temp_y--;
        }

        temp_x = x;
        temp_y = y;

        while (temp_y < 8 && temp_x >= 0) {
            chessBoard.changeValue(temp_x, temp_y);
            temp_x--;
            temp_y++;
        }
    }

    /**
     * Marks all the possible slots that the queen can take
     * 
     * @param x
     * @param y
     * @author Jan Le
     */
    private void backtrackQueensRoute(int x, int y) {
        // Horizontal
        for (int i = 0; i < 8; i++) {
            chessBoard.backtrackChangeValue(i, y);
        }

        // Vertical
        for (int i = 0; i < 8; i++) {
            chessBoard.backtrackChangeValue(x, i);
        }

        // Diagonal from upper left to lower right
        int temp_x = x;
        int temp_y = y;

        while (temp_x < 8 && temp_y < 8) {
            chessBoard.backtrackChangeValue(temp_x, temp_y);
            temp_x++;
            temp_y++;
        }

        temp_x = x;
        temp_y = y;

        while (temp_x >= 0 && temp_y >= 0) {
            chessBoard.backtrackChangeValue(temp_x, temp_y);
            temp_x--;
            temp_y--;
        }

        // Diagonal from lower left to upper right
        temp_x = x;
        temp_y = y;

        while (temp_x < 8 && temp_y >= 0) {
            chessBoard.backtrackChangeValue(temp_x, temp_y);
            temp_x++;
            temp_y--;
        }

        temp_x = x;
        temp_y = y;

        while (temp_y < 8 && temp_x >= 0) {
            chessBoard.backtrackChangeValue(temp_x, temp_y);
            temp_x--;
            temp_y++;
        }
    }

    /**
     * Draws the board without assistance
     * 
     * @author Quoc Viet Tran, Jan Le, Kenneth Ngo
     */
    public void drawBoard() {
        boolean foundQueen = false;
        // int[][] board = chessBoard.getBoard();

        System.out.println();
        System.out.println("    A   B   C   D   E   F   G   H   ");
        for (int i = 0; i < 8; i++) {
            System.out.println("  +---+---+---+---+---+---+---+---+");
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                // System.out.print("| " + board[i][j] + " ");
                foundQueen = false;

                for (Queen q : queenList) {
                    if (q.getX() == j && q.getY() == i) {
                        System.out.print("|" + Task11.RED + " X " + Task11.RESET);
                        foundQueen = true;
                        break;
                    }
                }

                if (!foundQueen) {
                    System.out.print("| 0 ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
    }

    /**
     * Draws the board with assistance
     * 
     * @author Quoc Viet Tran, Jan Le, Kenneth Ngo
     */
    public void drawBoardHelp() {
        boolean foundQueen = false;
        int[][] board = chessBoard.getBoard();

        System.out.println();
        System.out.println("    A   B   C   D   E   F   G   H   ");
        for (int i = 0; i < 8; i++) {
            System.out.println("  +---+---+---+---+---+---+---+---+");
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                foundQueen = false;

                for (Queen q : queenList) {
                    if (q.getX() == j && q.getY() == i) {
                        System.out.print("|" + Task11.RED + " X " + Task11.RESET);
                        foundQueen = true;
                        break;
                    }
                }

                if (!foundQueen) {
                    if (board[i][j] == 1) {
                        System.out.print("| " + Task11.YELLOW + board[i][j] + Task11.RESET + " ");
                    } else {
                        System.out.print("| " + board[i][j] + " ");
                    }

                }
            }
            System.out.println("|");
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
    }

    /**
     * Checks if the board has anymore slots left
     *
     * @return
     * @author Jan Le
     */
    public boolean noEmptySlots() {
        int count = chessBoard.getCountMarks();

        if (count == 8 * 8) {
            return true;
        }

        return false;
    }

    /**
     * Returns number of queens on the board
     * 
     * @return
     * @author Jan Le
     */
    public int getNumQueens() {
        return queenList.size();
    }
}