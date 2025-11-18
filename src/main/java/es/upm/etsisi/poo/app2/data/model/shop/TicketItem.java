package es.upm.etsisi.poo.app2.data.model.shop;

import java.util.Objects;

public class TicketItem {
    private final Product product;
    private Integer quantity;
    private Double discountApplied;

    public TicketItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.discountApplied = 0.0;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscountApplied() {
        return this.discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = Objects.requireNonNullElse(discountApplied, 0.0);
    }

    public Double getSubtotal(){
        return this.product.getPrice() * this.quantity;
    }

    public Double getDiscount() {
        return this.discountApplied * this.getSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.product.toString());
        if (discountApplied != null && discountApplied > 0.0) {
            stringBuilder.append(" **discount -").append(this.product.getPrice() * this.discountApplied);
        }
        return stringBuilder.toString();
    }
}
