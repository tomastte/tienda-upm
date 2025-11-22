package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.CashierService;

import java.util.List;

public class TicketNew implements Command {

    private final CashierService cashierService;
    private final View view;

    public TicketNew(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
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
    public void execute(String[] params) {
        String cashId;
        String clientId;
        Ticket ticket;
        if (params.length == 3) {
            String id = params[0];
            cashId = params[1];
            clientId = params[2];
            ticket = new Ticket(id, clientId, cashId);
        } else {
            cashId = params[0];
            clientId = params[1];
            ticket = new Ticket(clientId, cashId);
        }
        this.cashierService.newTicket(ticket, cashId);
        this.view.showEntity(ticket);
        this.view.show("ticket new: ok");
    }
}