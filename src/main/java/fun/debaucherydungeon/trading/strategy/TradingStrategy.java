package fun.debaucherydungeon.trading.strategy;

import fun.debaucherydungeon.asset.TradingData;
import fun.debaucherydungeon.trading.Action;

public interface TradingStrategy {

    String getTicker();

    String getName();

    Action onData(TradingData data);


}
