package es.upm.etsisi.poo.app2.data.model.shop;

import es.upm.etsisi.poo.app2.data.model.Entity;
import java.util.Objects;

public abstract class Product extends Entity {
    private String name;
    private Double price;

    public Product(Integer id, String name, Double price){
        super(id);
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price){
        super();
        this.name = name;
        this.price = price;
    }

    public boolean equalsWithoutId(Product product){
        if(product == null || !Objects.equals(this.getClass(), product.getClass())){
            return false;
        }
        return Objects.equals(this.name, product.name)
                && Objects.equals(this.getPrice(), product.getPrice());
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    @Override
    public String toString(){
        return "{class:Product, id:" + getId() + ", name:'" + getName() +
                "', price:" + getPrice() + "}";
    }
}
