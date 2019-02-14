public class Cash implements Payment {
    private float amountTendered;
    private float changeGiven;

    public Cash(float amountTendered) {
        this.amountTendered = amountTendered;
    }

    public void calcAmount(float total) {
        changeGiven = amountTendered - total;
    }

    public float getChangeGiven() {
        return changeGiven;
    }

    public float getAmountTendered() {
        return amountTendered;
    }
}
