package fun.debaucherydungeon.data.polygon;

import fun.debaucherydungeon.data.polygon.url.EmptyPolygonUrl;
import fun.debaucherydungeon.data.polygon.url.PolygonUrl;
import fun.debaucherydungeon.http.HttpClient;

public class PolygonApi {

    private final HttpClient client = new HttpClient();
    private PolygonUrl url;

    public PolygonApi(PolygonUrl url) {
        this.url = url;
    }

    public PolygonApi() {
        this.url = new EmptyPolygonUrl();
    }

    public void setUrl(PolygonUrl url) {
        this.url = url;
    }

    public String get() {
        return client.get(url.getUrlAsString());
    }
}
