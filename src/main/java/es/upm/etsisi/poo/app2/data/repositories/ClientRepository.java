package es.upm.etsisi.poo.app2.data.repositories;

public interface ClientRepository<Client> extends Repository {

    Client findByMail(String mail);

}
