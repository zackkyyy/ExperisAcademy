public class Task6 {
    public static void main(String[] args) {
        if(args.length <= 1) {
            System.out.println("Usage: java Task6 *weight in kilograms* *height in meters*");
            System.exit(0);
        }

        try {
            float weight = Float.parseFloat(args[0]);
            float height = Float.parseFloat(args[1]);
            float bmi = weight/(height*height);

            System.out.println();
            System.out.println("Your weight: " + weight + " kg");
            System.out.println("Your height: " + height + " m");
            System.out.println("Your bmi: " + bmi);
            System.out.print("Your category: ");

            if(bmi < 18.5) { //Underweight: BMI is less than 18.5
                System.out.print("Underweight\n");
            }
            else if(bmi < 25) { //Normal weight: BMI is 18.5 to 24.9
                System.out.print("Normal\n");
            }
            else if(bmi < 30) { //Overweight: BMI is 25 to 29.9
                System.out.print("Overweight\n");
            }
            else { //Obese: BMI is 30 or more
                System.out.print("Obese\n");
            }
        } catch(NumberFormatException e) {
            System.out.println("Usage: Weight and height must be integers.");
            System.exit(0);
        }
    }
}
