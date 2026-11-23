package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
//    Depending on the ProductRepository interface means that when the storage changes from in-memory to a database,
//    you can swap in a new repository class without changing a single line of ProductService.

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<String> getAllSummaries() {
        List<String> summaries = new ArrayList<>();
        for (Product p : repository.findAll()) {
            summaries.add(p.getSummary());
        }
        return summaries;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteProduct(Long id) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        repository.delete(existing.getId());
    }

    public List<Product> findByName(String name) {
        return repository.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

//    public Optional<Product> findById(long id) {
//        return repository.findById(id);
//    }
}