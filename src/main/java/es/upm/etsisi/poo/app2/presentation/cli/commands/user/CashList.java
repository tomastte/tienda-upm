package es.upm.etsisi.poo.app2.presentation.cli.commands.user;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.user.ClientService;

import java.util.List;

public class ClientList implements Command {

    private final ClientService clientService;

    public ClientList(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String name() {
        return "client list";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lists all registered clients with their id, name, email and associated cashier id.";
    }

    @Override
    public void execute(List<String> params) {
        List<Client> clients = this.clientService.list();
        View.showList("Client:", clients);
        View.show("client list: ok");
    }
}