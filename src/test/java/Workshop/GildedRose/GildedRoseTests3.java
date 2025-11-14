package Workshop.GildedRose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTests3 {
    // 12 tests
    ///test #type: unit create a comprehensive suite of tests for the updateQuality functionalityI

    private GildedRose app;

    @BeforeEach
    void setUp() {
        // This method will be called before each test
    }

    @Test
    void testNormalItemDecreasesInQualityAndSellIn() {
        Item[] items = new Item[]{new Item("foo", 10, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    void testQualityDegradesTwiceAsFastAfterSellIn() {
        Item[] items = new Item[]{new Item("foo", 0, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(18, items[0].quality);
    }

    @Test
    void testQualityIsNeverNegative() {
        Item[] items = new Item[]{new Item("foo", 5, 0)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 10)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesInQualityTwiceAsFastAfterSellIn() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void testQualityNeverExceeds50() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 50)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void testSulfurasNeverChanges() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseInQuality() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseBy2When10DaysOrLess() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseBy3When5DaysOrLess() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    void testBackstagePassesQualityDropsToZeroAfterConcert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void testMultipleItemsUpdateCorrectly() {
        Item[] items = new Item[]{
                new Item("foo", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);

        assertEquals(1, items[1].sellIn);
        assertEquals(1, items[1].quality);

        assertEquals(0, items[2].sellIn);
        assertEquals(80, items[2].quality);

        assertEquals(14, items[3].sellIn);
        assertEquals(21, items[3].quality);
    }
}
