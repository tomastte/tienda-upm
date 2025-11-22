package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class CashList implements Command {

    private final CashierService cashierService;
    private final View view;

    public CashList(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
    }

    @Override
    public String name() {
        return "cash list";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lists all registered cashiers with their id, name and email.";
    }

    @Override
    public void execute(String[] params) {
        List<Cashier> cashiers = this.cashierService.list();
        this.view.showList("Cash:", cashiers);
        this.view.show("cash list: ok");
    }
}