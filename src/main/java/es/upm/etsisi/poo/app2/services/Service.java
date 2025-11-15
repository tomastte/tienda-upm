package es.upm.etsisi.poo.app2.services;

import es.upm.etsisi.poo.app2.data.model.Entity;

public interface Service<T extends Entity> {

    public void add(T entity, String id);

    public void remove(String id);

    public void list();

}
