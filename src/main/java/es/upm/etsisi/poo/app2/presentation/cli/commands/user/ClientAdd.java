package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.ClientService;

import java.util.List;

public class ClientAdd implements Command {

    private final ClientService clientService;

    public ClientAdd(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String name() {
        return "client add";
    }
    @Override
    public List<String> params() {
        return List.of("\"<nombre>\"", "<DNI>","<email>","<cashId>");
    }

    @Override
    public String helpMessage() {
        return "Implements a new client with name, DNI, email and cashId.";
    }

    @Override
    public void execute(List<String> params) {
        if (params.size() != 4 || !params.getFirst().startsWith("\"")) {
            throw new CommandException("Usage: client add \"<nombre>\" <DNI> <email> <cashId>");
        }
        int index = 0;
        String name = "";
        while (!params().get(index).endsWith("\"")) {
            name += params().get(index) + " ";
            index++;
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        if (params.size() - index != 3) {
            throw new CommandException("Usage: client add \"<nombre>\" <DNI> <email> <cashId>");
        }
        String dni = params.get(index);
        index++;
        String mail = params.get(index);
        index++;
        String cashId = params.get(index);
        Client client = new Client(name, mail, cashId);
        this.clientService.add(client, dni);
        View.showEntity(client);
        View.show("client add: ok");
    }
}