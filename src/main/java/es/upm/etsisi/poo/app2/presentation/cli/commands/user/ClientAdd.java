package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
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
    public void execute(String[] params) {
        int index = 0;
        StringBuilder name = new StringBuilder(params[0] + " ");
        if (!name.toString().trim().endsWith("\"")) {
            index = 1;
            while (!params[index].endsWith("\"")) {
                name.append(params[index]).append(" ");
                index++;
            }
        }
        name = new StringBuilder(name.toString().trim());
        name = new StringBuilder(name.substring(1, name.length() - 2));
        String dni = params[index];
        index++;
        String mail = params[index];
        index++;
        String cashId = params[index];
        Client client = new Client(name.toString(), mail, cashId);
        this.clientService.add(client, dni);
        this.view.showEntity(client);
        this.view.show("client add: ok");
    }
}