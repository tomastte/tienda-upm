package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class CashAdd implements Command {

    private final CashierService cashierService;

    public CashAdd(CashierService cashierService) {
        this.cashierService = cashierService;
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
        if (params.size() < 2 || params.size() > 3) {
            throw new CommandException("Usage: cash add [<id>] \"<nombre>\" <email>");
        }
        String id = "";
        int index = 0;
        if (!params.getFirst().startsWith("\"")) {
            id = params.getFirst();
            index++;
        }
        if (!params.get(index).startsWith("\"")) {
            throw new CommandException("Usage: cash add [<id>] \"<nombre>\" <email>");
        }
        String name = params().get(index) + " ";
        if (!name.trim().endsWith("\"")) {
            index++;
            while (!params().get(index).endsWith("\"")) {
                name += params().get(index) + " ";
                index++;
            }
        }
        if (index >= params.size()) {
            throw new CommandException("Usage: cash add [<id>] \"<nombre>\" <email>");
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        if (params.size() - index != 1) {
            throw new CommandException("Usage: cash add [<id>] \"<nombre>\" <email>");
        }
        String mail = params.get(index);
        Cashier cashier = new Cashier(name, mail);
        if (id == null) {
            this.cashierService.add(cashier);
        } else {
            this.cashierService.add(cashier, id);
        }
        View.showEntity(cashier);
        View.show("cash add: ok");
    }
}