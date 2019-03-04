public class SavingsCard extends Card {
    private float balance;

    public SavingsCard(int pinCode, float balance) {
        super(pinCode);

        this.balance = balance;
    }

    public void calcAmount(float total) {
        System.out.println();

        if(total <= balance) {
            balance -= total;
            System.out.println("************* Payment successful. *************");
        } else {
            System.out.println("************* Payment failed. You are too poor. *************");
        }
    }

    public float getBalance() {
        return balance;
    }
}
