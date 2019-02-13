public class Task8 {
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Usage: java Task5 *name*");
            System.exit(0);
        }

        Person[] contacts = {new Person("Bruce", "Lee", "12345678"),
                            new Person("Jackie", "Chan", "87654321"),
                            new Person("Ip", "Man"),
                            new Person("Mike", "Tyson"),
                            new Person("Jet", "Li")};
        String pattern = args[0];
        boolean atLeastOneFound = false;

        System.out.println();
        System.out.println("Searching for: " + pattern);
        System.out.println("\nFound:");

        String name;

        for(Person person : contacts) {
            name = person.getFullname();
            //Turns both name and pattern to lower case to ignore case
            if(name.toLowerCase().contains(pattern.toLowerCase())) {
                System.out.println(name + ", " + person.getTelephone());

                if(!atLeastOneFound) {
                    atLeastOneFound = true;
                }
            }
        }

        if(!atLeastOneFound) {
            System.out.println("None");
        }
    }
}
