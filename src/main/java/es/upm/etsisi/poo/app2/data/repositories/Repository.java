package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app2.data.model.Entity;

import java.util.List;

public interface Repository<T extends Entity> {

    void add(T entity, String id);

    void remove(String id);

    List<Entity>list();

    Entity findById(String id);

}
