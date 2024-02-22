package fun.debaucherydungeon.exchange;

import fun.debaucherydungeon.trading.Action;

import java.util.Objects;

public final class ExchangeRequest {

    private final int action;
    private final String ticker;
    private final float price;
    private final float quantity;

    public ExchangeRequest(int action, String ticker, float price, float quantity) {
        this.action = action;
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
    }

    public ExchangeRequest(Action action) {
        this.action = action.actionToTake();
        this.ticker = action.ticker();
        this.price = action.price();
        this.quantity = action.quantity();
    }

    public int action() {
        return action;
    }

    public String ticker() {
        return ticker;
    }

    public float price() {
        return price;
    }

    public float quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ExchangeRequest) obj;
        return this.action == that.action &&
                Objects.equals(this.ticker, that.ticker) &&
                Float.floatToIntBits(this.price) == Float.floatToIntBits(that.price) &&
                Float.floatToIntBits(this.quantity) == Float.floatToIntBits(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, ticker, price, quantity);
    }

    @Override
    public String toString() {
        return "ExchangeRequest[" +
                "action=" + action + ", " +
                "ticker=" + ticker + ", " +
                "price=" + price + ", " +
                "quantity=" + quantity + ']';
    }
}
