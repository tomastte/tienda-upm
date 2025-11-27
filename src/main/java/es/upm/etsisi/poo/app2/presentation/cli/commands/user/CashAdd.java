package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Cashier;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
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
    public String[] assessParams(String[] params) {
        int index = 0;
        if (params.length < 2)
            throw new CommandException("Usage: " + this.help());
        // Id
        String id = null;
        if (params[0].matches("UW[0-9]{7}")) {
            id = params[0];
            index++;
        }
        // Name
        if (!params[index].startsWith("\""))
            throw new CommandException("Usage: " + this.help());
        StringBuilder name = new StringBuilder();
        name.append(params[index].substring(1));
        index++;
        while (index < params.length && !params[index].endsWith("\"")) {
            name.append(" ").append(params[index]);
            index++;
        }
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        name.append(" ").append(params[index], 0, params[index].length() - 1);
        index++;
        // Email
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        String email = params[index];
        return new String[]{id, name.toString().trim(), email};
    }

    @Override
    public void execute(String[] params) {
        params = this.assessParams(params);
        String id = params[0];
        String name = params[1];
        String mail = params[2];
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