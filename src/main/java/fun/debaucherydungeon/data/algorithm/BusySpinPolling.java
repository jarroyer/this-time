package fun.debaucherydungeon.data.algorithm;

public class BusySpinPolling implements PollingAlgorithm {

    @Override
    public boolean shouldPoll() {
        return true;
    }

}
