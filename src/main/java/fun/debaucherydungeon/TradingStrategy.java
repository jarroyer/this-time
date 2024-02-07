package fun.debaucherydungeon;

import java.util.List;

public interface TradingStrategy {

    List<String> getTickers();

    String getName();

    Action onData(Data data);


}
