By applying the Dependency Inversion principle, `ProductService.java` now depends on the `ProductRepository.java` interface rather than a concrete storage class, cleanly separating business logic from data persistence.

Because `ProductController.java` and `ProductService.java` now use constructor injection instead of `@Autowired` field injection, they can be instantiated directly in a plain unit test using `new` with a mock or fake collaborator rather than relying on Spring reflection.

If storage were migrated to a real database, only a new repository implementation such as `JdbcProductRepository.java` would need to be added to replace `InMemoryProductRepository.java`, while zero lines of code would change in `ProductRepository.java`, `ProductService.java`, or `ProductController.java`.