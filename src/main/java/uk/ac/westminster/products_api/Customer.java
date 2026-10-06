package uk.ac.westminster.products_api;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private Address address;

    // 1. Static field to track total instances created
    private static int customerCount = 0;

    // 2. Increment in no-arg constructor
    public Customer() {
        customerCount++;
    }

    // 3. Increment in parameterized constructor
    public Customer(Long id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        customerCount++;
    }

    // Getters for standard fields...
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Address getAddress() { return address; }

    // 4. Static getter method
    public static int getCustomerCount() {
        return customerCount;
    }
}
