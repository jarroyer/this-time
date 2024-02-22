package fun.debaucherydungeon.asset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

    @Test
    void testBuy() {
        Portfolio p = new Portfolio();
        p.buy(new Holding("NVDA", 10));
        Assertions.assertTrue(p.contains("NVDA"));
    }

    @Test
    void testBuysAreAdditiveForQuantity() {
        Portfolio p = new Portfolio();
        p.buy(new Holding("NVDA", 10));
        p.buy(new Holding("NVDA", 11.1f));
        Assertions.assertEquals(21.1f, p.getQuantity("NVDA"));
    }

    @Test
    void testSellsSubtractQuantity() {
        Portfolio p = new Portfolio();
        p.buy(new Holding("NVDA", 10));
        p.sell(new Holding("NVDA", 9.1f));
        Assertions.assertEquals(0.9f, p.getQuantity("NVDA"), 0.001f);
    }

    @Test
    void testSellOnUnheldAssetThrowsError() {
        Portfolio p = new Portfolio();
        Assertions.assertThrows(RuntimeException.class,
                () -> p.sell(new Holding("NVDA", 10)));
    }

    @Test
    void testSellingMoreThanHeldOfAssetThrowsError() {
        Portfolio p = new Portfolio();
        p.buy(new Holding("NVDA", 5));
        Assertions.assertThrows(RuntimeException.class,
                () -> p.sell(new Holding("NVDA", 10)));
    }


}

