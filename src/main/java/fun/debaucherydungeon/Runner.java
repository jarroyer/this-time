package fun.debaucherydungeon;

import fun.debaucherydungeon.asset.Portfolio;
import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.data.algorithm.TimeoutPolling;
import fun.debaucherydungeon.data.polygon.PolygonAggregatesDataDriver;
import fun.debaucherydungeon.data.polygon.PolygonDataSource;
import fun.debaucherydungeon.exchange.Exchange;
import fun.debaucherydungeon.exchange.ExchangeRequest;
import fun.debaucherydungeon.exchange.ExchangeResponse;
import fun.debaucherydungeon.exchange.MockExchange;
import fun.debaucherydungeon.trading.Action;
import fun.debaucherydungeon.trading.strategy.LimitStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runner {

    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, "NVDA", portfolio);
        PolygonDataSource dataSource = new PolygonDataSource(strategy);
        PolygonAggregatesDataDriver driver =
                new PolygonAggregatesDataDriver(dataSource,
                        new TimeoutPolling(1, TimeUnit.SECONDS),
                        "NVDA");
        driver.run();
    }
}
