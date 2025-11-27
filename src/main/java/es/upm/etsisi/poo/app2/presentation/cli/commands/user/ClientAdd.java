package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.ClientService;

import java.util.List;

public class ClientAdd implements Command {

    private final ClientService clientService;
    private final View view;

    public ClientAdd(View view, ClientService clientService) {
        this.clientService = clientService;
        this.view = view;
    }

    @Override
    public String name() {
        return "client add";
    }

    @Override
    public List<String> params() {
        return List.of("\"<nombre>\"", "<DNI>", "<email>", "<cashId>");
    }

    @Override
    public String helpMessage() {
        return "Implements a new client with name, DNI, email and cashId.";
    }

    @Override
    public String[] assessParams(String[] params) {
        if (params.length < 4)
            throw new CommandException("Usage: " + this.help());
        int index = 0;
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
        // DNI
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        String dni = params[index];
        index++;
        // Email
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        String email = params[index];
        index++;
        // CashId
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        String cashId = params[index];
        return new String[]{name.toString().trim(), dni, email, cashId};

    }

    @Override
    public void execute(String[] params) {
        params = this.assessParams(params);
        String name = params[0];
        String dni = params[1];
        String mail = params[2];
        String cashId = params[3];
        Client client = new Client(name, mail, cashId);
        this.clientService.add(client, dni);
        this.view.showEntity(client);
        this.view.show("client add: ok");
    }
}