package uk.ac.westminster.products_api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DigitalProductTest {

    @Test
    void shouldUpdatePriceWhenValid() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        ebook.setPrice(7.49);

        assertEquals(7.49, ebook.getPrice(), 0.001);
    }

    @Test
    void shouldRejectNegativePrice() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        assertThrows(IllegalArgumentException.class,
                () -> ebook.setPrice(-0.01));
    }
    @Test
    void shouldApplyDiscountWhenRateIsValid() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        ebook.applyDiscount(0.10);

        assertEquals(8.991, ebook.getDiscountedPrice(), 0.001);
    }
    @Test
    void shouldKeepFullPriceWhenDiscountIsZero() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        ebook.applyDiscount(0);

        assertEquals(9.99, ebook.getDiscountedPrice(), 0.001);
    }
    @Test
    void shouldApplyMaxDiscountWhenRateIsTwentyPercent() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        ebook.applyDiscount(0.20);

        assertEquals(7.992, ebook.getDiscountedPrice(), 0.001);
    }
    @Test
    void shouldRejectDiscountWhenRateIsNegative() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        assertThrows(IllegalArgumentException.class,
                () -> ebook.applyDiscount(-0.01));
    }
    @Test
    void shouldRejectDiscountWhenRateExceedsTwentyPercent() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        assertThrows(IllegalArgumentException.class,
                () -> ebook.applyDiscount(0.21));
    }
    @Test
    void shouldLeavePriceUnchangedWhenDiscountIsRejected() {
        DigitalProduct ebook = new DigitalProduct(1L, "E-book", 9.99, "/dl/1", 4.2);

        assertThrows(IllegalArgumentException.class,
                () -> ebook.applyDiscount(0.21));

        assertEquals(9.99, ebook.getDiscountedPrice(), 0.001);
    }
}