package es.upm.etsisi.poo.app2.data.model.shop.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.products.BasicProduct;

public class BasicTicketItem extends TicketItem {
    protected Double discountApplied;

    public BasicTicketItem(BasicProduct basicProduct, Integer quantity, Double discountApplied) {
        super(basicProduct, quantity);
        this.discountApplied = discountApplied;
    }

    public Double getDiscountApplied() {
        return this.discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
    }

    @Override
    public Double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public Double getDiscount(){
        return this.discountApplied * this.getTotalPrice();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.product.toString());
        if (this.discountApplied != null && this.discountApplied > 0.0) {
            double perUnitDiscount = this.getDiscount() / this.quantity;
            stringBuilder.append(" **discount -").append(perUnitDiscount);
        }
        return stringBuilder.toString();
    }
}
