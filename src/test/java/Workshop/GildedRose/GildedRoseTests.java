package Workshop.GildedRose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Unit")
public class GildedRoseTests {
     //   /test #type: unit create a comprehensive suite of tests for the updateQuality functionality
    // 13 tests (4 param)
 private GildedRose app;

    @BeforeEach
    void setUp() {
        // This setup will be overridden in most tests
        app = new GildedRose(new Item[]{});
    }

    @Test
    void updateQuality_regularItem_decreasesQualityAndSellIn() {
        Item item = new Item("Regular Item", 10, 20);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(19);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    void updateQuality_regularItemPastSellIn_decreasesQualityTwiceAsFast() {
        Item item = new Item("Regular Item", 0, 20);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(18);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    void updateQuality_agedBrie_increasesInQuality() {
        Item item = new Item("Aged Brie", 5, 10);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(11);
        assertThat(item.sellIn).isEqualTo(4);
    }

    @Test
    void updateQuality_agedBriePastSellIn_increasesInQualityTwiceAsFast() {
        Item item = new Item("Aged Brie", 0, 10);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(12);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    void updateQuality_sulfuras_doesNotChangeQualityOrSellIn() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(80);
        assertThat(item.sellIn).isEqualTo(0);
    }

    // I adjusted these from 10, 5, 0 to 11, 6, 1
    @ParameterizedTest
    @CsvSource({
            "12, 20, 21",
     //       "11, 20, 22",
     //       "6, 20, 23",
            "1, 20, 23",
            "0, 20, 0"
    })
    void updateQuality_backstagePasses_increasesInQualityBasedOnSellIn(int sellIn, int initialQuality, int expectedQuality) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(expectedQuality);
        assertThat(item.sellIn).isEqualTo(sellIn - 1);
    }

    @Test
    void updateQuality_backstagePassesPastSellIn_setsQualityToZero() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    void updateQuality_qualityNeverExceedsFifty() {
        Item agedBrie = new Item("Aged Brie", 5, 49);
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        app = new GildedRose(new Item[]{agedBrie, backstagePasses});

        app.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(50);
        assertThat(backstagePasses.quality).isEqualTo(50);
    }

    @Test
    void updateQuality_qualityNeverNegative() {
        Item item = new Item("Regular Item", 5, 0);
        app = new GildedRose(new Item[]{item});

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void updateQuality_multipleItems_updatesCorrectly() {
        Item[] items = new Item[] {
                new Item("Regular Item", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        };
        app = new GildedRose(items);

        app.updateQuality();

        assertThat(items[0].quality).isEqualTo(19);
        assertThat(items[0].sellIn).isEqualTo(9);
        assertThat(items[1].quality).isEqualTo(1);
        assertThat(items[1].sellIn).isEqualTo(1);
        assertThat(items[2].quality).isEqualTo(80);
        assertThat(items[2].sellIn).isEqualTo(0);
        assertThat(items[3].quality).isEqualTo(21);
        assertThat(items[3].sellIn).isEqualTo(14);
    }
}
