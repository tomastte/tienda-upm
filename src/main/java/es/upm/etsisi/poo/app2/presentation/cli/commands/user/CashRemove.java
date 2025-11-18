package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class CashRemove implements Command {

    private final CashierService cashierService;

    public CashRemove(CashierService cashierService) {
        this.cashierService = cashierService;
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
    public void execute(List<String> params) {
        if (params.size() != 1) {
            throw new CommandException("Usage: cash remove <id>");
        }
        String id = params.getFirst();
        Cashier cashier = this.cashierService.remove(id);
        View.showEntity(cashier);
        View.show("cash remove: ok");
    }
}