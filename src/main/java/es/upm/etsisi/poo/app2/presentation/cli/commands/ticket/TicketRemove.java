package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketRemove implements Command {

    private final CashierService cashierService;
    private final View view;

    public TicketRemove(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
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
    public void execute(String[] params) {
        String ticketId = params[0];
        String cashId = params[1];
        Integer prodId = Integer.parseInt(params[2]);
        Ticket ticket = this.cashierService.remove(cashId, ticketId, prodId);
        this.view.showEntity(ticket);
        this.view.show("ticket remove: ok");
    }
}