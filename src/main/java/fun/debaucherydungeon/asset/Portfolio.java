package fun.debaucherydungeon.asset;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    private List<Holding> holdings;

    public Portfolio() {
        this.holdings = new ArrayList<>();
    }

    public boolean contains(String ticker) {
        for (Holding h : holdings) {
            if (h.ticker().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    public Holding get(String ticker) {
        for (Holding h : holdings) {
            if (h.ticker().equals(ticker)) {
                return h;
            }
        }
        throw new RuntimeException("Could not find ticker " + ticker + " in holdings");
    }

    public void put(Holding toAdd) {
        if (!this.contains(toAdd.ticker())) {
            holdings.add(toAdd);
        } else {
            throw new RuntimeException("Trying to add holding " + toAdd + " to holdings but it was already present. " +
                    "Please use the add(Holding toAdd) method");
        }
    }

    public void add(Holding toAdd) {
        if (!this.contains(toAdd.ticker())) {
            holdings.add(toAdd);
        } else {
            for (Holding h : holdings) {
                if (h.ticker().equals(toAdd.ticker())) {
                    holdings.remove(h);
                    Holding newEntry = new Holding(h.ticker(), h.quantity() + toAdd.quantity());
                    holdings.add(newEntry);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Portfolio[ ");
        for (Holding h : holdings) {
            sb.append(h).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}