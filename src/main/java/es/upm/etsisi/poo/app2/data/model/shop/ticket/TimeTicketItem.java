package es.upm.etsisi.poo.app2.data.model.shop.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.products.TimeProduct;

public class TimeTicketItem extends TicketItem{
    public TimeTicketItem(TimeProduct timeProduct, Integer quantity) {
        super(timeProduct, quantity);
    }

    @Override
    public Double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
}
