import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        float price = 500;
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        String answer = "";
        int amount = 0;

        SavingsCard savingsCard = new SavingsCard(1234, 10000);
        CreditCard creditCard = new CreditCard(4321);
        Cash cash;
        String cardChoice = "";
        int pin = 0;

        printBalance(savingsCard, creditCard);

        do {
            answer = "";
            System.out.println("\nHello my friend, the total for you is $500. How do you wish to pay?");
            System.out.println("\n1. Cash\n2. Card\n3. Never mind, im leaving.\n");
            System.out.print("> ");

            answer = input.nextLine();

            if(answer.equals("1")) {
                System.out.println("\nHow much do you want to give?");
                System.out.print("> $");
                answer = input.nextLine();

                try {
                    amount = Integer.parseInt(answer);
                    cash = new Cash(amount);
                    cash.calcAmount(price);

                    System.out.println("\n************* You handed in: $" + cash.getAmountTendered() + " *************");
                    System.out.println("************* Cashier handed you back: $" + cash.getChangeGiven() + " *************");
                } catch(Exception e) {
                    System.out.println("\n************* Not a valid amount. *************");
                } finally {
                    amount = 0;
                }

            } else if(answer.equals("2")) {
                System.out.println("\n1. Savings card");
                System.out.println("2. Credit card\n");
                System.out.print("> ");
                cardChoice = input.nextLine();


                System.out.println("\nEnter your pin: ");
                System.out.print("> ");
                answer = input.nextLine();

                try {
                    pin = Integer.parseInt(answer);

                } catch(Exception e) {
                    System.out.println("\n************* Not a valid pin code. *************");
                    cardChoice = "";
                }

                if(cardChoice.equals("1")) {
                    if(savingsCard.authorize(pin)) {
                        savingsCard.calcAmount(price);
                        printBalance(savingsCard, creditCard);
                    } else {
                        System.out.println("************* Pin code is wrong. *************");
                    }
                } else if(cardChoice.equals("2")) {
                    if(creditCard.authorize(pin)) {
                        creditCard.calcAmount(price);
                        printBalance(savingsCard, creditCard);
                    } else {
                        System.out.println("************* Pin code is wrong. *************");
                    }
                } else if(!cardChoice.equals("")){
                    System.out.println("\n************* Not a valid command. *************");
                }

                cardChoice = "";
                pin = -1;

            } else if(answer.equals("3")) {
                System.out.println("\nPlease come again.");
                loop = false;
            } else {
                System.out.println("\n************* Not a valid command. *************");
            }
        } while(loop);
    }

    public static void printBalance(SavingsCard savingsCard, CreditCard creditCard) {
        System.out.println("\n~~~~~~~~~~ Your payment information ~~~~~~~~~~\n");
        System.out.println("Savings card");
        System.out.println("Pin code: " + savingsCard.getPin());
        System.out.println("Balance: $" + savingsCard.getBalance());
        System.out.println();
        System.out.println("Credit card");
        System.out.println("Pin code: " + creditCard.getPin());
        System.out.println("Charged: $" + creditCard.getBalance());
        System.out.println();
        System.out.println("Cash");
        System.out.println("Balance: $infinite");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
