package fun.debaucherydungeon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LimitStrategyTest {

    @Test
    void test() {
        LimitStrategy strategy = new LimitStrategy(200.0f, 250.0f, 10, List.of("NVDA"));
        Action action = strategy.onData(new Data("OCHL", "NVDA", 260.0f, System.currentTimeMillis()));
        Assertions.assertEquals(action.get);
    }

}
