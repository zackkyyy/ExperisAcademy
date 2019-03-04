class Task2 {
    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Usage: java Task2 *yourname*");
            System.exit(0);
        }

        String name = args[0];
        System.out.printf("Hello %s, your name is %s characters long and starts with a %s.",
            name,
            name.length(),
            name.charAt(0));
    }
}
