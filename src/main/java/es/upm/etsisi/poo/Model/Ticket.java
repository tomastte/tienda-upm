package es.upm.etsisi.poo.Model;

import java.util.*;

public class Ticket {
    private final LinkedList<TicketItem> itemList;

    public Ticket(){
        this.itemList = new LinkedList<>();
    }

    public boolean addProduct(Product product, int quantity) {
        boolean result = true;
        if(product == null || quantity <= 0){
            result = false;
        }
        boolean itemFound = false;
        Iterator<TicketItem> iterator = this.itemList.iterator();
        while(iterator.hasNext() && !itemFound){
            TicketItem item = iterator.next();
            if(item.getProduct().equals(product)){
                itemFound = true;
                item.setQuantity(item.getQuantity() + quantity);
            }
        }
        if(!itemFound){
            TicketItem newItem = new TicketItem(product, quantity, product.getCategory().getDiscount());
            itemList.add(newItem);
        }

        return result;
    }

}
