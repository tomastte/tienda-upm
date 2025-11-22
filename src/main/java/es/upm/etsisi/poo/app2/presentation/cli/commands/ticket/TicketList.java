package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.ticket.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.CashierService;

import java.util.List;

public class TicketList implements Command {

    private final CashierService cashierService;
    private final View view;

    public TicketList(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
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
    public void execute(String[] params) {
        List<Ticket> tickets = this.cashierService.ticketList();
        this.view.showList("Ticket List:", tickets);
        this.view.show("ticket list: ok");
    }
}