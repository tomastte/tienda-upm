package es.upm.etsisi.poo.Model;

import es.upm.etsisi.poo.Model.Product;

public class TicketItem {
    private Product product;
    private int quantity;
    private double discountApplied;

    public TicketItem(Product product, int quantity, double discountApplied) {
        this.product = product;
        this.quantity = quantity;
        this.discountApplied = discountApplied;
    }

    public double getSubtotal(){
        return product.getPrice() * quantity;
    }

    public double getDiscount(){
        return discountApplied * this.getSubtotal();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
