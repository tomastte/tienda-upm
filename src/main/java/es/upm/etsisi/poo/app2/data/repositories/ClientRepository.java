package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app2.data.model.user.Client;

public interface ClientRepository extends Repository {

    Client findByMail(String mail);

}
