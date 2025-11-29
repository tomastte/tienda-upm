package es.upm.etsisi.poo.app2.data.model.shop.products;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;
import es.upm.etsisi.poo.app2.data.model.shop.Category;

public class CustomProduct extends BasicProduct {
    private Double originalPrice;
    private Integer numberTexts;

    public CustomProduct(String name, Category category, Double OriginalPrice, Integer numberTexts) {
        super(name, category, OriginalPrice);
        this.originalPrice = OriginalPrice;
        this.numberTexts = numberTexts;
        double price = calculatePrice();
        super.setPrice(price);
    }

    public Double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        if (originalPrice < 0) {
            throw new InvalidAttributeException("OriginalPrice cannot be negative");
        }
        this.originalPrice = originalPrice;
    }

    public Integer getNumberTexts() {
        return numberTexts;
    }

    public void setNumberTexts(Integer numberTexts) {
        if(numberTexts < 0) {
            throw new InvalidAttributeException("NumberTexts cannot be negative");
        }
        this.numberTexts = numberTexts;
    }

    private double calculatePrice(){
        return this.originalPrice * (1.0 + 0.1 * this.numberTexts);
    }

    @Override
    public String toString() {
        return "{class:ProductPersonalized, id:" + this.id + ", name:'" + this.getName() + "', category:" +
                this.getCategory() + ", price:" + this.originalPrice + ", maxPersonal:" + this.numberTexts + "}";
    }
}
