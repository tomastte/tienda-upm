package es.upm.etsisi.poo.app2.services;

import es.upm.etsisi.poo.app2.data.model.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    public void add(T entity, String id);

    public T remove(String id);

    public List<T> list();

}
