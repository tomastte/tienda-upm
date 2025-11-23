package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.CashierService;

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
    public void execute(String[] params) {
        String id = "";
        int index = 0;
        if (!params[index].startsWith("\"")) {
            id = params[index];
            index++;
        }
        StringBuilder name = new StringBuilder(params[index] + " ");
        if (!name.toString().trim().endsWith("\"")) {
            index++;
            while (!params[index].endsWith("\"")) {
                name.append(params[index]).append(" ");
                index++;
            }
        }
        name = new StringBuilder(name.toString().trim());
        name = new StringBuilder(name.substring(1, name.length() - 2));
        String mail = params[index];
        Cashier cashier = new Cashier(name.toString(), mail);
        if (id.isEmpty()) {
            this.cashierService.add(cashier);
        } else {
            this.cashierService.add(cashier, id);
        }
        this.view.showEntity(cashier);
        this.view.show("cash add: ok");
    }
}