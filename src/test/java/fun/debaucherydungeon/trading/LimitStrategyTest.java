package fun.debaucherydungeon.trading;

import fun.debaucherydungeon.asset.Holding;
import fun.debaucherydungeon.asset.Portfolio;
import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.exchange.ExchangeRequest;
import fun.debaucherydungeon.exchange.ExchangeResponse;
import fun.debaucherydungeon.trading.strategy.LimitStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fun.debaucherydungeon.trading.ActionKey.*;

public class LimitStrategyTest {

    @Test
    void testSell() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), SELL);
    }

    @Test
    void testBuy() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 190.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), BUY);
    }

    @Test
    void testHold() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 220.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), HOLD);
    }

    @Test
    void testQuantity() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 220.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.quantity(), 10);
    }

    @Test
    void testHoldingOnActiveBuy() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        strategy.onData(new TradingData("OCHL", "NVDA", 190.0f, System.currentTimeMillis()));
        Assertions.assertTrue(strategy.isHolding());
    }

    @Test
    void testHoldingOnActiveSell() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Assertions.assertTrue(strategy.isHolding());
    }

    @Test
    void testPortfolioGetsUpdatedWithSuccessfulExchangeBuy() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 190.0f, System.currentTimeMillis()));
        ExchangeRequest request = new ExchangeRequest(action);
        ExchangeResponse response = new ExchangeResponse("SUCCESS", request);
        strategy.onExchangeEvent(response);
        Assertions.assertTrue(p.contains("NVDA"));
    }


    @Test
    void testHoldGetsUpdatedOnExchangeBuy() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 190.0f, System.currentTimeMillis()));
        ExchangeRequest request = new ExchangeRequest(action);
        ExchangeResponse response = new ExchangeResponse("SUCCESS", request);
        strategy.onExchangeEvent(response);
        Assertions.assertFalse(strategy.isHolding());
    }

    @Test
    void testPortfolioLosesStockOnSell() {
        Portfolio p = new Portfolio();
        p.buy(new Holding("NVDA", 10));
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        ExchangeRequest request = new ExchangeRequest(action);
        ExchangeResponse response = new ExchangeResponse("SUCCESS", request);
        strategy.onExchangeEvent(response);
        Assertions.assertEquals(0, p.getQuantity("NVDA"), 0.1f);
    }

    @Test
    void testDataDoesNothingWhenStrategyIsOnHold() {
        Portfolio p = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", p);
        p.buy(new Holding("NVDA", 10));
        strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Assertions.assertEquals(HOLD, action.actionToTake());

    }
}
