package es.upm.etsisi.poo.app2.data.repositories.map;

import es.upm.etsisi.poo.app1.model.Ticket;
import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.data.repositories.CashierRepository;

import java.util.List;

public class CashierRepositoryMap extends RepositoryMapUser<Cashier> implements CashierRepository {

    public CashierRepositoryMap() {
        super();
    }

    @Override
    public List<Ticket> listTickets() {
        return null;
    }

    @Override
    public void add(Cashier cashier) {

    }

}

