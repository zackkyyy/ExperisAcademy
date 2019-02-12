public class Task3 {
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Usage: java Task3 *size*");
            System.exit(0);
        }

        try {
            int size = Integer.parseInt(args[0]);

            System.out.println();
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(i == 0 || i+1 >= size) {
                        System.out.print("#");
                    }
                    else {
                        if(j == 0 || j+1 >= size) {
                            System.out.print("#");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
        } catch(NumberFormatException e) {
            System.out.println("Usage: Size must be an integer.");
            System.exit(0);
        }
    }
}
