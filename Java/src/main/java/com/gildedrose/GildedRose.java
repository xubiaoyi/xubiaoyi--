package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        switch (item.name) {
            case "Aged Brie":
                updateAgedBrie(item);
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                updateBackstagePass(item);
                break;
            case "Sulfuras, Hand of Ragnaros":
                // Sulfuras doesn't change in quality or sellIn.
                break;
            case "Conjured":
                updateConjuredItem(item);
                break;
            default:
                updateNormalItem(item);
                break;
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            int qualityIncrease = 1;

            if (item.sellIn < 10) {
                qualityIncrease += 1;
            }

            if (item.sellIn < 5) {
                qualityIncrease += 1;
            }

            increaseQuality(item, qualityIncrease);
            item.sellIn -= 1;
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            decreaseQuality(item, 1);
        }
    }

    private void updateConjuredItem(Item item) {
        decreaseQuality(item, 2);
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        }
    }

    private void increaseQuality(Item item, int amount) {
        if (item.quality < 50) {
            item.quality += Math.min(50 - item.quality, amount);
        }
    }

    private void decreaseQuality(Item item, int amount) {
        if (item.quality > 0) {
            item.quality -= Math.min(item.quality, amount);
        }
    }
}
