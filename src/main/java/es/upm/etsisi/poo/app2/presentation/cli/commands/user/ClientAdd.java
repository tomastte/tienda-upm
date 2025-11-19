package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.ClientService;

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
    public void execute(List<String> params) {
        String name = params().getFirst() + " ";
        if (!name.trim().endsWith("\"")) {
            int index = 1;
            while (!params().get(index).endsWith("\"")) {
                name += params().get(index) + " ";
                index++;
            }
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        String dni = params.get(index);
        index++;
        String mail = params.get(index);
        index++;
        String cashId = params.get(index);
        Client client = new Client(name, mail, cashId);
        this.clientService.add(client, dni);
        this.view.showEntity(client);
        this.view.show("client add: ok");
    }
}