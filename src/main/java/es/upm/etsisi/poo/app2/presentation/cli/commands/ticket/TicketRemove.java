package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketRemove implements Command {

    private final CashierService cashierService;

    public TicketRemove(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public String name() {
        return "ticket remove";
    }

    @Override
    public List<String> params() {
        return List.of("<ticketId>", "<cashId>", "<prodId>");
    }

    @Override
    public String helpMessage() {
        return "Deletes a product from the specified ticket using ticketId, cashId, and prodId.";
    }

    @Override
    public void execute(List<String> params) {
        if (params.size() != 3) {
            throw new CommandException("Usage: ticket remove <ticketId> <cashId> <prodId>");
        }
        String ticketId = params.get(0);
        String cashId = params.get(1);
        Integer prodId = Integer.parseInt(params.get(2));
        Ticket ticket = this.cashierService.remove(cashId, ticketId, prodId);
        View.showEntity(ticket);
        View.show("ticket remove: ok");
    }
}