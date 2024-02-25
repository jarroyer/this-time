package fun.debaucherydungeon.data.polygon;

public class PolygonAggregatesUrl implements PolygonUrl {

    private final String baseUrl = "https://api.polygon.io/v2/aggs";
    private final String apiToken = "Vq6DZx1B3e_fv1gCQMAxmEWdkLP5s84i";

    private final String ticker;
    private final int timespanMultiplier;
    private final String timespan;
    private final boolean adjusted;
    private final String from;
    private final String to;
    private final int limit;

    public PolygonAggregatesUrl(
            String ticker,
            int timespanMultiplier,
            String timespan,
            boolean adjusted,
            String from,
            String to,
            int limit) {
        this.ticker = ticker;
        this.timespanMultiplier = timespanMultiplier;
        this.timespan = timespan;
        this.adjusted = adjusted;
        this.from = from;
        this.to = to;
        this.limit = limit;
    }

    @Override
    public String getUrlAsString() {
        return baseUrl + "/ticker/" + ticker + "/range/" + timespanMultiplier
                + "/" + timespan + "/" + from + "/" + to
                + "?adjusted=" + adjusted + "&sort=asc&limit=" + limit + "&apiKey="
                + apiToken;
    }
}
