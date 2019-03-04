public class CreditCard extends Card {
    private float charged;

    public CreditCard(int pinCode) {
        super(pinCode);
    }

    public void calcAmount(float total) {
        System.out.println();
        charged += total;
        System.out.println("************* Payment successful. *************");
    }

    public float getBalance() {
        return charged;
    }
}
