package es.upm.etsisi.poo.app2.data.model.shop;

public class CustomProduct extends BasicProduct {
    private Double originalPrice;
    private Integer numberTexts;

    public CustomProduct(Integer id, String name, Category category, Double originalPrice, Integer numberTexts) {
        super(id, name, category, originalPrice);
        this.originalPrice = originalPrice;
        this.numberTexts = numberTexts;
        double price = calculatePrice();
        super.setPrice(price);
    }

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
        this.originalPrice = originalPrice;
    }

    public Integer getNumberTexts() {
        return numberTexts;
    }

    public void setNumberTexts(Integer numberTexts) {
        this.numberTexts = numberTexts;
    }

    private double calculatePrice(){
        return this.originalPrice * (1.0 + 0.1 * this.numberTexts);
    }

    @Override
    public String toString() {
        return "{class:ProductPersonalized, id:" + getId() + ", name:'" + getName() + "', category:" +
                getCategory() + ", price:" + getPrice() + ", maxPersonal:" + this.numberTexts +
                (this.numberTexts > 0 ? ", personalizationList:[...]" : "") + "}";
    }
}
