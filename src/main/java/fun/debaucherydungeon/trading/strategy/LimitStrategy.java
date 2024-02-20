package fun.debaucherydungeon.trading.strategy;

import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.trading.Action;

import static fun.debaucherydungeon.trading.ActionKey.*;

public class LimitStrategy implements TradingStrategy {

    private final String ticker;
    private final float sellAbove;
    private final float buyBelow;
    private final int quantity;

    public LimitStrategy(float buyBelow, float sellAbove, int quantity, String tickers) {
        this.ticker = tickers;
        this.sellAbove = sellAbove;
        this.buyBelow = buyBelow;
        this.quantity = quantity;
    }

    @Override
    public String getTicker() {
        return ticker;
    }

    @Override
    public String getName() {
        return "Limit Strategy";
    }

    @Override
    public Action onData(TradingData data) {
        if (data.price() > sellAbove) {
            return new Action(SELL, data.ticker(), data.price());
        } else if (data.price() < buyBelow) {
            return new Action(BUY, data.ticker(), data.price());
        }
        return new Action(HOLD, data.ticker(), data.price());
    }
}
