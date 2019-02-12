public class Task4 {
    public static void main(String[] args) {
        if(args.length <= 1) {
            System.out.println("Usage: java Task4 *width* *height*");
            System.exit(0);
        }

        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);

            if(width < 5 || height < 5) {
                System.out.println("\nWarning: If width or/and height is below 5, the square may not support a correct inner square.");
            }

            System.out.println();

            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    //Draw outer square
                    //draw -> top||bottom||right||left
                    if(i == 0 || i+1 >= height || j == 0 || j+1 >= width) {
                        System.out.print("#");
                    }
                    //Draw only inner square if height and width are greater than 4
                    else if(height > 4 && width > 4) {
                        //Draw inner square
                        //draw -> (top||bottom)&&..
                        if((i == 2 || i == height-3) && j > 1 && j < width-2) {
                            System.out.print("#");
                        }
                        //draw -> (left||right)&&..
                        else if((j == 2 || j == width-3) && i > 1 && i < height-2) {
                            System.out.print("#");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else {
                        System.out.print(" ");
                    }
                }

                System.out.println();
            }
        } catch(NumberFormatException e) {
            System.out.println("Usage: Width and height must be integers.");
            System.exit(0);
        }
    }
}
