package fun.debaucherydungeon.asset;

import java.util.Map;
import java.util.HashMap;

public class Portfolio {

    private final Map<String, Holding> holdings;

    public Portfolio() {
        this.holdings = new HashMap<>();
    }

    public boolean contains(String ticker) {
        // TODO this is bad
        for (Holding h : holdings.values()) {
            if (h.ticker().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    public void buy(Holding purchased) {
        if (!this.contains(purchased.ticker())) {
            holdings.put(purchased.ticker(), purchased);
        } else {
            Holding h = holdings.get(purchased.ticker());
            h.setQuantity(purchased.quantity() + h.quantity());
        }
    }

    public void sell(Holding sold) {
        if (!this.contains(sold.ticker())) {
            throw new RuntimeException("Trying to sell asset " + sold.ticker() + " that isn't held in the portfolio");
        } else {
            Holding held = holdings.get(sold.ticker());
            if (held.quantity() < sold.quantity()) {
                throw new RuntimeException("Portfolio is holding " + held.quantity() + " of asset " + held.ticker() + ", cannot sell "+ sold.quantity());
            } else {
                held.setQuantity(held.quantity() - sold.quantity());
            }
        }
    }

    public float getQuantity(String ticker) {
        if (this.contains(ticker)) {
            return this.holdings.get(ticker).quantity();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Portfolio[ ");
        for (Holding h : holdings.values()) {
            sb.append(h).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}