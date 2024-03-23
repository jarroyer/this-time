package fun.debaucherydungeon.data.polygon;

import fun.debaucherydungeon.data.DataDriver;
import fun.debaucherydungeon.data.DataSource;
import fun.debaucherydungeon.data.algorithm.PollingAlgorithm;
import fun.debaucherydungeon.data.polygon.url.PolygonAggregatesUrl;

public class PolygonAggregatesDataDriver implements DataDriver {

    private final DataSource dataSource;
    private final PolygonAggregatesUrl url;
    private final PollingAlgorithm pollingAlgorithm;
    private long from = 0;
    private long to = 0;

    public PolygonAggregatesDataDriver(
            DataSource dataSource,
            PollingAlgorithm pollingAlgorithm,
            String ticker) {
        this.dataSource = dataSource;
        this.url = new PolygonAggregatesUrl(
                ticker,
                1,
                "day",
                true,
                from,
                to,
                120
        );
        this.pollingAlgorithm = pollingAlgorithm;
    }

    @Override
    public void run() {
        if (pollingAlgorithm.shouldPoll()) {
            from = to;
            to = System.currentTimeMillis();
            url.setFrom(from);
            url.setTo(to);
            dataSource.poll(url);
        }
    }
}
