package fun.debaucherydungeon;

import fun.debaucherydungeon.asset.Portfolio;
import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.exchange.Exchange;
import fun.debaucherydungeon.exchange.ExchangeRequest;
import fun.debaucherydungeon.exchange.ExchangeResponse;
import fun.debaucherydungeon.exchange.MockExchange;
import fun.debaucherydungeon.trading.Action;
import fun.debaucherydungeon.trading.strategy.LimitStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", portfolio);
        Exchange exchange = new MockExchange();
        Action action = strategy.onData(new TradingData("OCHL", "NVDA", 199.0f, 10, System.currentTimeMillis()));
        ExchangeResponse response = exchange.send(new ExchangeRequest(action.actionToTake(), action.ticker(), action.price(), action.quantity()));
        strategy.onExchangeEvent(response);
        logger.info(portfolio);
    }
}
