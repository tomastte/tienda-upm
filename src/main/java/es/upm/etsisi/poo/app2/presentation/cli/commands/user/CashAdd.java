package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class CashAdd implements Command {

    private final CashierService cashierService;
    final private View view;

    public CashAdd(View view, CashierService cashierService) {
        this.cashierService = cashierService;
        this.view = view;
    }

    @Override
    public String name() {
        return "cash add";
    }

    @Override
    public List<String> params() {
        return List.of("[<id>]", "\"<nombre>\"", "<email>");
    }

    @Override
    public String helpMessage() {
        return "Implements a new cashier register with optional id, name and email.";
    }

    @Override
    public void execute(List<String> params) {
        String id = "";
        int index = 0;
        if (!params.getFirst().startsWith("\"")) {
            id = params.getFirst();
            index++;
        }
        String name = params().get(index) + " ";
        if (!name.trim().endsWith("\"")) {
            index++;
            while (!params().get(index).endsWith("\"")) {
                name += params().get(index) + " ";
                index++;
            }
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        String mail = params.get(index);
        Cashier cashier = new Cashier(name, mail);
        if (id == null) {
            this.cashierService.add(cashier);
        } else {
            this.cashierService.add(cashier, id);
        }
        this.view.showEntity(cashier);
        this.view.show("cash add: ok");
    }
}