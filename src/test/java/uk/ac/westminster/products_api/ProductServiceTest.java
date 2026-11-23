package uk.ac.westminster.products_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;
//define them as a field of this class
    private final Product ebook = new DigitalProduct(1L,"E-book",9.99,"/dl/1",4.2);
    private final Product monitor = new PhysicalProduct(2L,"Monitor",249.99,5.4,"60*40");

    @Test
    void shouldReturnAllProducts() {
        when(repository.findAll()).thenReturn(List.of(ebook, monitor));

        List<Product> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals("E-book", result.get(0).getName());
    }

    @Test
    void shouldReturnProductById() {
        when(repository.findById(1L)).thenReturn(Optional.of(ebook));

        Product result = service.getProductById(1L);

        assertEquals("E-book", result.getName());
        assertEquals(9.99, result.getPrice(), 0.001);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> service.getProductById(99L));
    }
    @Test
    void shouldSaveNewProduct() {

        // Arrange
        Product notes = new DigitalProduct(
                null, "Course notes", 4.99, "/dl/2", 1.1);

        when(repository.save(notes)).thenReturn(notes);

        // Act
        Product result = service.addProduct(notes);

        // Assert
        verify(repository).save(notes);
        assertEquals("Course notes", result.getName());
    }
    @Test
    void shouldNotDeleteWhenProductMissing(){
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,()->service.deleteProduct(99L));
        verify(repository, never()).delete(any());

    }
    @Test
    void shouldFindProductsByName() {
        when(repository.findAll()).thenReturn(List.of(ebook, monitor));

        List<Product> result = service.findByName("E-book");

        assertEquals(1, result.size());
        assertEquals("E-book", result.get(0).getName());
    }

    @Test
    void shouldReturnAllSummaries() {
        when(repository.findAll()).thenReturn(List.of(ebook, monitor));

        List<String> summaries = service.getAllSummaries();

        assertEquals(2, summaries.size());
    }



}