package fun.debaucherydungeon.data.polygon;

import fun.debaucherydungeon.data.DataSource;
import fun.debaucherydungeon.data.algorithm.PollingAlgorithm;
import fun.debaucherydungeon.data.polygon.url.PolygonUrl;
import fun.debaucherydungeon.http.HttpClient;
import fun.debaucherydungeon.trading.strategy.TradingStrategy;

public class PolygonDataSource implements DataSource {

    private final HttpClient client = new HttpClient();
    private final PollingAlgorithm pollingAlgorithm;
    private final PolygonUrl polygonUrl;
    private final TradingStrategy strategy;

    public PolygonDataSource(
            PollingAlgorithm pollingAlgorithm,
            PolygonUrl url,
            TradingStrategy strategy) {
        this.pollingAlgorithm = pollingAlgorithm;
        this.polygonUrl = url;
        this.strategy = strategy;
    }

    @Override
    public void poll() {
        if (pollingAlgorithm.shouldPoll()) {
            String url = polygonUrl.getUrlAsString();
            String response = client.get(url);
            // response -> trade data -> strategy

        }
    }
}
