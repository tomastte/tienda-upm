package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketNew implements Command {

    private final CashierService cashierService;

    public TicketNew(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public String name() {
        return "ticket new";
    }

    @Override
    public List<String> params() {
        return List.of("[<id>]", "<cashId>", "<userId>");
    }

    @Override
    public String helpMessage() {
        return "Creates a new ticket with optional id, cashId and userId.";
    }

    @Override
    public void execute(List<String> params) {
        if (params.size() < 2 || params.size() > 3) {
            throw new CommandException("Usage: ticket new [<id>] <cashId> <userId>");
        }
        String cashId;
        String clientId;
        Ticket ticket;
        if (params.size() == 3) {
            String id = params.get(0);
            cashId = params.get(1);
            clientId = params.get(2);
            ticket = new Ticket(id, clientId, cashId);
        } else {
            cashId = params.get(0);
            clientId = params.get(1);
            ticket = new Ticket(clientId, cashId);
        }
        this.cashierService.newTicket(ticket, cashId);
        View.showEntity(ticket);
        View.show("ticket new: ok");
    }
}