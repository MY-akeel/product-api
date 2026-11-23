package uk.ac.westminster.products_api;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(long id);
    Product save(Product product);
    void delete(Long id);
}
