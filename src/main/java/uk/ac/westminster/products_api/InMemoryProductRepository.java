package uk.ac.westminster.products_api;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository{
    private final Map<Long,Product> store = new HashMap<>();
    private Long nextId = 1L;

    public InMemoryProductRepository(){
        DigitalProduct ebook = new DigitalProduct(
                null, "Clean Code (E-book)", 29.99,
                "/downloads/clean-code.pdf", 4.2);
        ebook.applyDiscount(0.10);
        save(ebook);

        PhysicalProduct monitor = new PhysicalProduct(
                null, "27-inch Monitor", 249.99,
                5.4, "61cm x 41cm x 18cm");
        monitor.applyDiscount(0.15);
        save(monitor);

    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
