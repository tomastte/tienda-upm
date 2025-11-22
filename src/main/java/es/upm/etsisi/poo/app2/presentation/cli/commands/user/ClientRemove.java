package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.ClientService;

import java.util.List;

public class ClientRemove implements Command {

    private final ClientService clientService;
    private final View view;

    public ClientRemove(View view, ClientService clientService) {
        this.clientService = clientService;
        this.view = view;
    }

    @Override
    public String name() {
        return "client remove";
    }

    @Override
    public List<String> params() {
        return List.of("<DNI>");
    }

    @Override
    public String helpMessage() {
        return "Deletes a client by their DNI.";
    }

    @Override
    public void execute(String[] params) {
        String dni = params[0];
        Client client = this.clientService.remove(dni);
        this.view.showEntity(client);
        this.view.show("client remove: ok");
    }
}