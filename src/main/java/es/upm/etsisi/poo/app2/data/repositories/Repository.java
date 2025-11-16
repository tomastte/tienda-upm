package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app2.data.model.Entity;

import java.util.List;

public interface Repository<T extends Entity> {

    public void add(T entity, String id);

    public void remove(String id);

    public List<Entity>list();

    public Entity findById(String id);

}
