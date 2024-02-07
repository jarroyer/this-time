package fun.debaucherydungeon;

import java.util.List;

public interface TradingStrategy {

    String getTicker();

    String getName();

    Action onData(Data data);


}
