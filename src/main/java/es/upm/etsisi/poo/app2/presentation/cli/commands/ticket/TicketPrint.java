package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketPrint implements Command {

    private final CashierService cashierService;

    public TicketPrint(CashierService cashierService) {
        this.cashierService = cashierService;
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
    public void execute(List<String> params) {
        if (params.size() != 2) {
            throw new CommandException("Usage: ticket print <ticketId> <cashId>");
        }
        String ticketId = params.get(0);
        String cashId = params.get(1);
        Ticket ticket = this.cashierService.print(cashId, ticketId);
        View.showEntity(ticket);
        View.show("ticket print: ok");
    }
}