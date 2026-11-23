package uk.ac.westminster.products_api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

        @Test
    void shouldUpdatePriceWhenValid() {
        //Arrange
        Product monitor = new PhysicalProduct(1L, "Monitor",
                249.99, 5.4, "60x40");

        //Act
        monitor.setPrice(349.99);

        //Assertion
        assertEquals(349.99, monitor.getPrice());
    }
    @Test
    void shouldUpdatePriceWithDelta() {
        //Arrange
        Product monitor = new PhysicalProduct(1L, "Monitor",
                249.99, 5.4, "60x40");

        //Act
        monitor.setPrice(349.99);

        //Assertion
        //assertNotNull(monitor.getPrice());
        assertEquals(349.98, monitor.getPrice(),0.01);
    }
    @Test
    void shouldRejectNegativePrice() {
        //Arrange
        Product monitor = new PhysicalProduct(1L, "Monitor",
                249.99, 5.4, "60x40");


        // act and assertions
        assertThrows(IllegalArgumentException.class,
                () -> monitor.setPrice(-249.99));
    }
}