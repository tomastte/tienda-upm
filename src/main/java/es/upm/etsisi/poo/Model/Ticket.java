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

    public boolean removeProduct(int id){
        boolean result = true;
        if(id < 0 || id >= this.itemList.size()){
            result = false;
        }
        itemList.remove(id);
        return result;
    }

    public void clear(){
        this.itemList.clear();
    }

    public double calculateTotalDiscount(){
        double result = 0;
        Iterator<TicketItem> iterator = this.itemList.iterator();
        while(iterator.hasNext()){
            TicketItem item = iterator.next();
            result += item.getDiscount();
        }
        return  result;
    }

    public double calculateTotalPrice(){
        double result = 0;
        Iterator<TicketItem> iterator = this.itemList.iterator();
        while(iterator.hasNext()){
            TicketItem item = iterator.next();
            result += item.getSubtotal();
        }
        return result;
    }

    public double calculateFinalPrice(){
        return this.calculateTotalPrice() -  calculateTotalDiscount();
    }

    public Product getProduct(int id){
        return this.itemList.get(id).getProduct();
    }

    @Override
    public String toString() {
        LinkedList<TicketItem> sortedList = new LinkedList<>(this.itemList);
        sortedList.sort(new Comparator<TicketItem>() {
            @Override
            public int compare(TicketItem a, TicketItem b) {
                return a.getProduct().getName().compareTo(b.getProduct().getName());
            }
        });

        StringBuilder result = new StringBuilder();
        Iterator<TicketItem> iterator = sortedList.iterator();
        while(iterator.hasNext()){
            TicketItem item = iterator.next();
            result.append(item.getProduct().toString());
            if(item.getDiscount() != 0) {
                result.append(" **discount -").append(item.getDiscount());
            }
            result.append("\n");
        }

        result.append("Total price: ").append(calculateTotalPrice()).append("\n");
        result.append("Total discount: ").append(calculateTotalDiscount()).append("\n");
        result.append("Final Price: ").append(calculateFinalPrice()).append("\n");

        return result.toString();
    }

}
