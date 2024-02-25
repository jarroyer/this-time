package fun.debaucherydungeon.data.polygon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolygonAggregatesUrlTest {

    @Test
    void testUrlFormatt() {
        PolygonAggregatesUrl url = new PolygonAggregatesUrl(
                "AAPL",
                1,
                "day",
                true,
                "2023-01-09",
                "2023-01-09",
                120
        );
        Assertions.assertEquals("https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=Vq6DZx1B3e_fv1gCQMAxmEWdkLP5s84i",
                url.getUrlAsString());
    }
}
