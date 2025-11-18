package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.ClientService;

import java.util.List;

public class ClientRemove implements Command {

    private final ClientService clientService;

    public ClientRemove(ClientService clientService) {
        this.clientService = clientService;
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
    public void execute(List<String> params) {
        if (params.size() != 1) {
            throw new CommandException("Usage: client remove <DNI>");
        }
        String dni = params.getFirst();
        Client client = this.clientService.remove(dni);
        View.showEntity(client);
        View.show("client remove: ok");
    }
}