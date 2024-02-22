package fun.debaucherydungeon.asset;

import java.util.Objects;

public final class Holding {

    private final String ticker;
    private float quantity;

    public Holding(String ticker, float quantity) {
        this.ticker = ticker;
        this.quantity = quantity;
    }

    public String ticker() {
        return ticker;
    }

    public float quantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Holding) obj;
        return Objects.equals(this.ticker, that.ticker) &&
                Float.floatToIntBits(this.quantity) == Float.floatToIntBits(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, quantity);
    }

    @Override
    public String toString() {
        return "Holding[" +
                "ticker=" + ticker + ", " +
                "quantity=" + quantity + ']';
    }
}