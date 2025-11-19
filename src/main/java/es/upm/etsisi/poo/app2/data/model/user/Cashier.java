package es.upm.etsisi.poo.app2.data.model.user;

import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.data.model.shop.Ticket;

import java.util.HashMap;
import java.util.Map;

public class Cashier extends User{
    private final Map<String, Ticket> ticketList;

    public Cashier(String id, String name, String mail) {
        super(id, name, mail);
        this.ticketList = new HashMap<String, Ticket>();
    }

    public Cashier(String name, String mail) {
        super(null, name, mail);
        this.ticketList = new HashMap<String, Ticket>();
    }

    public void newTicket(Ticket ticket){
        this.ticketList.put(ticket.getId(), ticket);
    }

    public void newTicket(Ticket ticket, String ticketId){
        ticket.setId(ticketId);
        this.ticketList.put(ticketId, ticket);
    }

    public void addProduct(String ticketId, Product product, Integer quantity){
        Ticket ticket = this.ticketList.get(ticketId);
        ticket.add(product, quantity);
    }

    public void removeProduct(String ticketId, Product product){
        Ticket ticket = this.ticketList.get(ticketId);
        Integer productId = Integer.valueOf(product.getId().toString());
        ticket.remove(productId);
    }

    public void closeTicket(String ticketId){
        Ticket ticket = this.ticketList.get(ticketId);
        ticket.closeTicket();
    }

    public Ticket getTicket(String ticketId){
        return this.ticketList.get(ticketId);
    }
}
