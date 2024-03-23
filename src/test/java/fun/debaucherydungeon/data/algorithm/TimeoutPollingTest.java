package fun.debaucherydungeon.data.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class TimeoutPollingTest {

    @Test
    void testReturnsTrueFirstTry() {
        TimeoutPolling polling = new TimeoutPolling(5, TimeUnit.NANOSECONDS);
        Assertions.assertTrue(polling.shouldPoll());
    }

    @Test
    void testReturnsFalseAfterFirstTryBeforeTime() {
        TimeoutPolling polling = new TimeoutPolling(5, TimeUnit.MINUTES);
        polling.shouldPoll();
        Assertions.assertFalse(polling.shouldPoll());
    }
}
