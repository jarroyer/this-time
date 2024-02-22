package fun.debaucherydungeon.trading.strategy;

import fun.debaucherydungeon.asset.Holding;
import fun.debaucherydungeon.asset.Portfolio;
import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.exchange.ExchangeResponse;
import fun.debaucherydungeon.trading.Action;

import static fun.debaucherydungeon.trading.ActionKey.*;

public class LimitStrategy implements TradingStrategy {

    private final String ticker;
    private final float sellAbove;
    private final float buyBelow;
    private final float quantity;
    private final Portfolio portfolio;

    private boolean hold = false;

    public LimitStrategy(float buyBelow,
                         float sellAbove,
                         float quantity,
                         String ticker,
                         Portfolio portfolio) {
        this.ticker = ticker;
        this.sellAbove = sellAbove;
        this.buyBelow = buyBelow;
        this.quantity = quantity;
        this.portfolio = portfolio;
    }

    @Override
    public String getTicker() {
        return ticker;
    }

    @Override
    public String getName() {
        return "Limit Strategy: " + ticker;
    }

    @Override
    public Action onData(TradingData data) {
        if (hold) {
            return new Action(HOLD, data.ticker(), quantity, data.price());
        }
        if (data.price() > sellAbove) {
            hold = true;
            return new Action(SELL, data.ticker(), quantity, data.price());
        } else if (data.price() < buyBelow) {
            hold = true;
            return new Action(BUY, data.ticker(), quantity, data.price());
        }
        return new Action(HOLD, data.ticker(), quantity, data.price());
    }

    @Override
    public void onExchangeEvent(ExchangeResponse event) {
        if (event.status().equals("SUCCESS")) {
            if (event.request().action() == BUY) {
                portfolio.buy(new Holding(event.request().ticker(), event.request().quantity()));
            }
        }
        hold = false;
    }

    public boolean isHolding() {
        return hold;
    }
}
