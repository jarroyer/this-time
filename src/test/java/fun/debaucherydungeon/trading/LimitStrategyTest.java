package fun.debaucherydungeon.trading;

import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.trading.strategy.LimitStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fun.debaucherydungeon.trading.ActionKey.*;

public class LimitStrategyTest {

    @Test
    void testSell() {
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA");
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), SELL);
    }

    @Test
    void testBuy() {
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA");
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 190.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), BUY);
    }

    @Test
    void testHold() {
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA");
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 220.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.actionToTake(), HOLD);
    }
}
