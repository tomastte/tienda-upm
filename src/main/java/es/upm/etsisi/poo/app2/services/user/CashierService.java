package es.upm.etsisi.poo.app2.services.user;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.data.model.shop.TicketItem;
import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.data.repositories.CashierRepository;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.Service;

public class CashierService implements Service<Cashier> {

    private final CashierRepository cashierRepository;
    private final View view;

    public CashierService(CashierRepository cashierRepository, View view) {
        this.cashierRepository = cashierRepository;
        this.view = view;
    }

    @Override
    public void add(Cashier entity, String id) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void list() {

    }

    public void add(Cashier cashier) {

    }

    public void newTicket(Ticket ticket) {

    }

    public void newTicket(Ticket ticket, String id) {

    }

    public void print(String cashierId, String ticketId) {

    }

    public void addProduct(String cashierId, String ticketId, TicketItem ticketItem) {

    }

    public void removeProduct(String cashierId, String ticketId, String prodId) {

    }

}
