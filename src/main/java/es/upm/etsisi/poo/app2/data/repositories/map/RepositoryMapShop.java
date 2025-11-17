package es.upm.etsisi.poo.app2.data.repositories.map;

import es.upm.etsisi.poo.app2.data.model.Entity;
import es.upm.etsisi.poo.app2.data.repositories.RepositoryShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RepositoryMapShop<T extends Entity> implements RepositoryShop<T> {

    protected final Map<Integer, T> map;
    protected Integer id;

    public RepositoryMapShop(){
        this.map = new HashMap<>();
        this.id = 1;
    }

    @Override
    public void add(T entity, Integer id) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public T findById(Integer id) {
        return null;
    }

}

