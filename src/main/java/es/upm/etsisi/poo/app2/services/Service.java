package es.upm.etsisi.poo.app2.services;

import es.upm.etsisi.poo.app2.data.model.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    void add(T entity, String id);

    T remove(String id);

    List<T> list();

}
