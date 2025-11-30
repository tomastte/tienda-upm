package es.upm.etsisi.poo.app2.data.repositories.map;

import es.upm.etsisi.poo.app2.data.model.user.Client;
import es.upm.etsisi.poo.app2.data.repositories.ClientRepository;

public class ClientRepositoryMap extends RepositoryUserMap<Client> implements ClientRepository {

    public ClientRepositoryMap() {
        super();
    }

}

