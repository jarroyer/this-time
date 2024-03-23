package fun.debaucherydungeon.data.algorithm;

import java.util.concurrent.TimeUnit;

public class TimeoutPolling implements PollingAlgorithm {

    private final int timeout;
    private final TimeUnit timeUnit;
    private long lastPolled = 0;

    public TimeoutPolling(int timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    @Override
    public boolean shouldPoll() {
        long currentTime = System.nanoTime();
        long since = currentTime - lastPolled;
        if (since > TimeUnit.NANOSECONDS.convert(timeout, timeUnit)) {
            lastPolled = currentTime;
            return true;
        } else {
            return false;
        }
    }
}
