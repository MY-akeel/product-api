package uk.ac.westminster.products_api;

// A PhysicalProduct IS-A Product because it is a specialised kind of product that has a name, price and id like every other product, and it can be used anywhere a Product is expected.
// A ShoppingCart would NOT pass this test because a cart is not a kind of product; it contains products, so it is a HAS-A relationship (composition).

public class PhysicalProduct extends Product{
    private double weightKg;
    private String dimensions;

    public PhysicalProduct(){

    }
    public PhysicalProduct(Long id,String name,double price,double weightKg,String dimensions){
        super(id,name,price);
        this.weightKg = weightKg;
        this.dimensions = dimensions;
    }
    public double getWeightKg(){
        return weightKg;
    }
    public String getDimensions(){
        return dimensions;
    }
}
