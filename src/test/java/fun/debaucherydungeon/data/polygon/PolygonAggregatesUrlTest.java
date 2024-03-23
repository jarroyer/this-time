package fun.debaucherydungeon.data.polygon;

import fun.debaucherydungeon.data.polygon.url.PolygonAggregatesUrl;
import fun.debaucherydungeon.http.HttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolygonAggregatesUrlTest {

    @Test
    void testUrlFormat() {
        PolygonAggregatesUrl url = new PolygonAggregatesUrl(
                "AAPL",
                1,
                "day",
                true,
                1674284400000L,
                1674370800000L,
                120
        );
        Assertions.assertEquals("https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/1674284400000/1674370800000?adjusted=true&sort=asc&limit=120&apiKey=Vq6DZx1B3e_fv1gCQMAxmEWdkLP5s84i",
                url.getUrlAsString());
    }

    @Test
    void testSend() {
        PolygonAggregatesUrl url = new PolygonAggregatesUrl(
                "AAPL",
                1,
                "day",
                true,
                1674284400000L,
                1674370800000L,
                120
        );

        HttpClient client = new HttpClient();
        String response = client.get(url.getUrlAsString());
        Assertions.assertTrue(response.contains("AAPL"));
    }
}
