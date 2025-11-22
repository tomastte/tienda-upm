package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.CashierService;

import java.util.List;

public class CashRemove implements Command {

    private final CashierService cashierService;
    private final View view;

    public CashRemove(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
    }

    @Override
    public String name() {
        return "cash remove";
    }

    @Override
    public List<String> params() {
        return List.of("<id>");
    }

    @Override
    public String helpMessage() {
        return "Deletes a cashier register with the specified id.";
    }

    @Override
    public void execute(String[] params) {
        if (params.length != 1) {
            throw new CommandException("Usage: cash remove <id>");
        }
        String id = params[0];
        Cashier cashier = this.cashierService.remove(id);
        this.view.showEntity(cashier);
        this.view.show("cash remove: ok");
    }
}