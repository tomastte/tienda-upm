package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class CashTickets implements Command {

    private final CashierService cashierService;
    private final View view;

    public CashTickets(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
    }

    @Override
    public String name() {
        return "cash tickets";
    }

    @Override
    public List<String> params() {
        return List.of("<id>");
    }

    @Override
    public String helpMessage() {
        return "Shows all tickets created by the cashier with the given id.";
    }

    @Override
    public void execute(String[] params) {
        List<Ticket> tickets = this.cashierService.ticketListFromCashier(params[0]);
        this.view.showList("Ticket :", tickets);
        this.view.show("cash tickets: ok");
    }
}