package es.upm.etsisi.poo.app2.data.model.user;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;
import es.upm.etsisi.poo.app2.data.model.shop.products.CustomProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.Product;
import es.upm.etsisi.poo.app2.data.model.shop.ticket.Ticket;
import es.upm.etsisi.poo.app2.data.repositories.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cashier extends User{
    private final Map<String, Ticket> ticketList;

    public Cashier(String name, String mail) {
        super(name, mail);
        this.ticketList = new HashMap<String, Ticket>();
    }

    @Override
    public void setId(String id) {
        if(!id.matches("UW[0-9]{7}")){
            throw new InvalidAttributeException("Invalid cashierId");
        }
        this.id = id;
    }

    public void newTicket(Ticket ticket){
        this.ticketList.put(ticket.getId(), ticket);
    }

    public void addProduct(String ticketId, Product product, Integer quantity){
        Ticket ticket = this.ticketList.get(ticketId);
        if(ticket == null){
            throw new EntityNotFoundException("Ticket not found");
        }
        ticket.add(product, quantity);
    }

    public void addCustomProduct(String ticketId, CustomProduct product, Integer quantity, String[] texts){
        Ticket ticket = this.ticketList.get(ticketId);
        if(ticket == null){
            throw new EntityNotFoundException("Ticket not found");
        }
        ticket.addCustom(product, quantity, texts);
    }

    public void removeProduct(String ticketId, Integer productId){
        Ticket ticket = this.ticketList.get(ticketId);
        if(ticket == null){
            throw new EntityNotFoundException("Ticket not found");
        }
        ticket.remove(productId);
    }

    public void closeTicket(String ticketId){
        Ticket ticket = this.ticketList.get(ticketId);
        if(ticket == null){
            throw new EntityNotFoundException("Ticket not found");
        }
        ticket.closeTicket();
    }

    public Ticket getTicket(String ticketId){
        return this.ticketList.get(ticketId);
    }

    public List<Ticket> getTicketList() {
        return (List<Ticket>) this.ticketList;
    }

}
