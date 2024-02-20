package fun.debaucherydungeon.exchange;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MockExchange implements Exchange {

    private static final Logger logger = LogManager.getLogger(MockExchange.class);

    @Override
    public ExchangeResponse send(ExchangeRequest request) {
        logger.debug("Exchange received request " + request);
        return new ExchangeResponse("SUCCESS", request);
    }
}
