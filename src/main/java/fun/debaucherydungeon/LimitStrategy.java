package fun.debaucherydungeon;

import java.util.List;

public class LimitStrategy implements TradingStrategy {

    private final List<String> tickers;
    private final float sellAbove;
    private final float buyBelow;
    private final int quantity;

    public LimitStrategy(float buyBelow, float sellAbove, int quantity, List<String> tickers) {
        this.tickers = tickers;
        this.sellAbove = sellAbove;
        this.buyBelow = buyBelow;
        this.quantity = quantity;
    }

    @Override
    public List<String> getTickers() {
        return tickers;
    }

    @Override
    public String getName() {
        return "Limit Strategy";
    }

    @Override
    public Action onData(Data data) {
        return null;
    }
}
