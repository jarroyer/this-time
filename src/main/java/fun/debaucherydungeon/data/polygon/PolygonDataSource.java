package fun.debaucherydungeon.data.polygon;

import fun.debaucherydungeon.data.DataSource;
import fun.debaucherydungeon.data.converter.PolygonResponseConverter;
import fun.debaucherydungeon.data.Url;
import fun.debaucherydungeon.http.HttpClient;
import fun.debaucherydungeon.trading.strategy.TradingStrategy;

public class PolygonDataSource implements DataSource {

    private final HttpClient client = new HttpClient();
    private final TradingStrategy strategy;

    public PolygonDataSource(TradingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void poll(Url url) {
        String urlAsString = url.getUrlAsString();
        String response = client.get(urlAsString);
        PolygonResponseConverter converter = new PolygonResponseConverter();
        strategy.onData(converter.convert(response));
    }
}
