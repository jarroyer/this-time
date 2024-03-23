package fun.debaucherydungeon.data.polygon.url;

import fun.debaucherydungeon.data.polygon.url.PolygonUrl;

public class PolygonAggregatesUrl implements PolygonUrl {

    private final String baseUrl = "https://api.polygon.io/v2/aggs";
    private final String apiToken = "Vq6DZx1B3e_fv1gCQMAxmEWdkLP5s84i";

    private String ticker;
    private int timespanMultiplier;
    private String timespan;
    private boolean adjusted;
    private long from;
    private long to;
    private int limit;

    public PolygonAggregatesUrl(
            String ticker,
            int timespanMultiplier,
            String timespan,
            boolean adjusted,
            long from,
            long to,
            int limit) {
        this.ticker = ticker;
        this.timespanMultiplier = timespanMultiplier;
        this.timespan = timespan;
        this.adjusted = adjusted;
        this.from = from;
        this.to = to;
        this.limit = limit;
    }

    public void setTo(int millis) {
        this.to = millis;
    }

    public void setFrom(int millis) {
        this.from = millis;
    }

    @Override
    public String getUrlAsString() {
        return baseUrl + "/ticker/" + ticker + "/range/" + timespanMultiplier
                + "/" + timespan + "/" + from + "/" + to
                + "?adjusted=" + adjusted + "&sort=asc&limit=" + limit + "&apiKey="
                + apiToken;
    }
}
