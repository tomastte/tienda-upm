package es.upm.etsisi.poo.app2.data.model.shop.ticket;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;
import es.upm.etsisi.poo.app2.data.model.shop.TimeProductType;
import es.upm.etsisi.poo.app2.data.model.shop.products.TimeProduct;

public class TimeTicketItem extends TicketItem{
    public TimeTicketItem(TimeProduct timeProduct, Integer quantity) {
        super(timeProduct, quantity);
    }

    @Override
    public Double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        TimeProduct product =(TimeProduct) this.getProduct();
        String productType;
        if(product.getType() == TimeProductType.MEETING){
            productType = "Meeting";
        }else{
            productType = "Food";
        }

        stringBuilder.append("{class:").append(productType)
                .append(", id:").append(product.getId())
                .append(", name:'").append(product.getName()).append("'")
                .append(", price:").append(product.getPrice())
                .append(", date of Event:");

        if (product.getOpenDate() == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(product.getOpenDate());
        }

        stringBuilder.append(", max people allowed:");
        if (product.getMAX_PEOPLE() == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(product.getMAX_PEOPLE());
        }

        stringBuilder.append(", actual people in event:");
        if (this.quantity == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.quantity);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
