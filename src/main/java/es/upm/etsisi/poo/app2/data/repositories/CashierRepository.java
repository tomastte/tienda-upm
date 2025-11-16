package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app1.model.Ticket;
import java.util.List;

public interface CashierRepository<Cashier> extends Repository {

    List<Ticket> listTickets();

    void add(Cashier cashier);

    Cashier findByMail(String mail);

}
