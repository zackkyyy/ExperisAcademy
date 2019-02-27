import java.util.HashMap;
import java.util.Scanner;

/**
 * A game called 8-queens.
 * 
 * @author Jan Le, Quoc Viet Tran, Kenneth Ngo
 */

public class Task11 {
    // Global ANSI Colour code variables
    final public static String RESET = "\u001b[0m";
    final public static String RED = "\u001b[31m";
    final public static String CYAN = "\u001b[36m";
    final public static String GREEN = "\u001b[32m";
    final public static String YELLOW = "\u001b[33m";
    // Init queens and board
    static InitPlacement program;
    static Scanner scanner;
    static HashMap<String, Integer> tableLookUp;

    public static void main(String[] args) {
        program = new InitPlacement();
        scanner = new Scanner(System.in);
        tableLookUp = initTableLookUp();
        boolean loop = true;

        String x_input = "";
        String y_input = "";
        int x = -1;
        int y = -1;

        System.out.println("\n******************* 8-Queens game *******************");
        program.drawBoard();

        System.out.println("\nType" + GREEN + " HELP" + RESET + " to get help, " + RED + "EXIT or Q" + RESET
                + " to quit and SOLVE to solve the puzzle.");
        System.out.println("Or type in a co-ordinate: ");

        System.out.print("> x: ");
        x_input = scanner.nextLine();
        System.out.print("> y: ");
        y_input = scanner.nextLine();

        try {
            x = tableLookUp.get(x_input.toLowerCase());
            y = Integer.parseInt(y_input) - 1;

            if (program.placeQueen(x, y)) { // Places queen if possible
                Boolean result = program.solve(0);

                program.drawBoard();

                if (result) {
                    System.out.println("\nWe found a solution!");
                } else {
                    System.out.println("\nWe did not find a solution.");
                }

                System.out.println("Number of queens: " + program.getNumQueens());
            }
        } catch (Exception e) {
            System.out.println("********* Some of the co-ordinates are not valid. *********");
        }
    }

    /**
     * Prints statistic and exits program.
     * 
     * @param program
     * @author Jan Le
     */
    public static void finish() {
        System.out.println("\nYou managed to place: " + program.getNumQueens() + " queens.");

        scanner.close();
        System.exit(0);
    }

    /**
     * Draws the board with assistance
     * 
     * @param program
     * @author Jan Le
     */
    public static void help() {
        program.drawBoardHelp();
    }

    /**
     * Initialize tablelookup for row
     * 
     * @return a hashmap table with keys as character and int as index
     * @author Kenneth Ngo
     */
    public static HashMap<String, Integer> initTableLookUp() {
        final HashMap<String, Integer> newTableLookUp = new HashMap<>();
        newTableLookUp.put("a", 0);
        newTableLookUp.put("b", 1);
        newTableLookUp.put("c", 2);
        newTableLookUp.put("d", 3);
        newTableLookUp.put("e", 4);
        newTableLookUp.put("f", 5);
        newTableLookUp.put("g", 6);
        newTableLookUp.put("h", 7);
        return newTableLookUp;
    }
}