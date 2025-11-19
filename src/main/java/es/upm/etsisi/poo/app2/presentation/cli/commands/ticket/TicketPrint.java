package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketPrint implements Command {

    private final CashierService cashierService;
    private final View view;

    public TicketPrint(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
    }

    @Override
    public String name() {
        return "ticket print";
    }

    @Override
    public List<String> params() {
        return List.of("<ticketId>", "<cashId>");
    }

    @Override
    public String helpMessage() {
        return "Prints the details of a ticket by ticketId.";
    }

    @Override
    public void execute(String[] params) {
        String ticketId = params[0];
        String cashId = params[1];
        Ticket ticket = this.cashierService.print(cashId, ticketId);
        this.view.showEntity(ticket);
        this.view.show("ticket print: ok");
    }
}