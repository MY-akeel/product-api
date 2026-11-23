package uk.ac.westminster.products_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryProductRepositoryTest {
    private InMemoryProductRepository repository;
    @BeforeEach
    void setup() {
        repository = new InMemoryProductRepository(); // fresh for every test
    }

    @Test
    void shouldAssignIdWhenSavingNewProduct() {
        Product notes = new DigitalProduct(null, "Course notes", 4.99, "/dl/2", 1.1);

        Product saved = repository.save(notes);

        assertNotNull(saved.getId());
        assertSame(notes, repository.findById(saved.getId()).get());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        assertTrue(repository.findById(999L).isEmpty());
    }

    @Test
    void shouldDeleteProduct() {
        Product notes = new DigitalProduct(null, "Course notes", 4.99, "/dl/2", 1.1);
        Product saved = repository.save(notes);

        repository.delete(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}
