public class Task5 {
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Usage: java Task5 *name*");
            System.exit(0);
        }

        String[] contacts = {"Bruce Lee", "Jackie Chan", "Ip Man", "Mike Tyson", "Jet Li"};
        String pattern = args[0];
        boolean atLeastOneFound = false;

        System.out.println();
        System.out.println("Searching for: " + pattern);
        System.out.println("\nMatches:");

        for(String name : contacts) {
            //Turns both name and pattern to lower case to ignore case
            if(name.toLowerCase().contains(pattern.toLowerCase())) {
                System.out.println(name);
            }
        }

        if(!atLeastOneFound) {
            System.out.println("None");
        }
    }
}
