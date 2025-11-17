package es.upm.etsisi.poo.app2.data.model.shop;

public class BasicProduct extends Product {
    private Category category;

    public BasicProduct(Integer id, String name, Category category, Double price){
        super(id, name, price);
        this.category = category;
    }

    public BasicProduct(String name, Category category, Double price){
        super(name, price);
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{class:Product, id:" + getId() + ", name:'" + getName() +
                "', category:" + category + ", price:" + getPrice() + "}";
    }
}
