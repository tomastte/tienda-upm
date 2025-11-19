package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketList implements Command {

    private final CashierService cashierService;

    public TicketList(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public String name() {
        return "ticket list";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lists all tickets registered in the system.";
    }

    @Override
    public void execute(List<String> params) {
        List<Ticket> tickets = cashierService.ticketList();
        View.showList("Ticket List:", tickets);
        View.show("ticket list: ok");
    }
}