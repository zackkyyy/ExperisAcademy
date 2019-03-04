import java.io.*;
import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("\nUsage: java Task9 *full filepath*");
            System.out.println("\nE.g java Task9 \"C:\\Users\\janle1\\Desktop\\test.txt\"");
            System.out.println("\nIf same path:");
            System.out.println("E.g java Task9 test.txt");
            System.exit(0);
        }

        try {
            Scanner input = new Scanner(System.in);
            String path = args[0];
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lines = 0;
            String currentLine;
            String answer;
            StringBuilder contentBuilder = new StringBuilder();
            String content;

            //Count and read lines from file
            while((currentLine = bufferedReader.readLine()) != null) {
                lines++;
                contentBuilder.append(currentLine).append("\n");
            }

            content = contentBuilder.toString();
            bufferedReader.close();

            System.out.println("\nPath: " + path);
            System.out.println("Statistics for this file: ");
            System.out.println("Filename: " + file.getName());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Number of lines: " + lines);
            System.out.println("\nDo you want to search for a specific word in the file? [y/n]");
            System.out.print("> ");
            answer = input.next();
            System.out.println();

            //If user wants to search for a word in file
            if(answer.equalsIgnoreCase("y")) {
                answer = "";

                System.out.println("Type in the word you want to look up:");
                System.out.print("> ");
                answer = input.next();
                System.out.println();
                content = content.toLowerCase();

                //Counts chosen word if found in file
                if(content.contains(answer.toLowerCase())) {
                    int counter = 0;
                    String[] words = content.split(" ");

                    for(String s : words) {
                        //Removes . or , from word
                        if(s.endsWith(".") || s.endsWith(",")) {
                            s = s.substring(0, s.length()-1);
                        }

                        if(s.equalsIgnoreCase(answer)) {
                            counter++;
                        }
                    }

                    System.out.println("The word \"" + answer + "\" is found " + counter + " times.");
                }
                else {
                    System.out.println("File does not contain the word \"" + answer + "\"");
                }
            }

        } catch(Exception e) {
            System.out.println("File not found, please run program again with a valid .txt file.");
            System.exit(0);
        }
    }
}
