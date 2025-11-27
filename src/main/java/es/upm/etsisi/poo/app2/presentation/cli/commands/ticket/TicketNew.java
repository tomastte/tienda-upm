package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.ticket.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
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
    public String[] assessParams(String[] params) {
        if (params.length < 2 || params.length > 3)
            throw new CommandException("Usage: " + this.help());
        // Id
        int index = 0;
        String id = null;
        if (params[0].matches("[0-9]{6}")) {
            id = params[0];
            index++;
        }
        // CashId + UserId
        String cashId = params[index];
        String userId = params[index + 1];
        return new String[]{id, cashId, userId};
    }

    @Override
    public void execute(String[] params) {
        params = this.assessParams(params);
        String id = params[0];
        String cashId = params[1];
        String clientId = params[2];
        Ticket ticket;
        if (id != null) {
            ticket = new Ticket(id, clientId, cashId);
        } else {
            ticket = new Ticket(clientId, cashId);
        }
        this.cashierService.newTicket(ticket, cashId);
        this.view.showEntity(ticket);
        this.view.show("ticket new: ok");
    }
}