public abstract class Card implements Payment {
    private int pinCode;

    public Card(int pinCode) {
        this.pinCode = pinCode;
    }

    public boolean authorize(int pinCode) {
        return this.pinCode == pinCode;
    }

    public int getPin() {
        return pinCode;
    }
}
