package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app1.model.Ticket;
import es.upm.etsisi.poo.app2.data.model.user.Cashier;

import java.util.List;

public interface CashierRepository extends RepositoryUser<Cashier> {

    List<Ticket> listTickets();

    void add(Cashier cashier);

}
