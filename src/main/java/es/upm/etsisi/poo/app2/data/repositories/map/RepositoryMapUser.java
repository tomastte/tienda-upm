package es.upm.etsisi.poo.app2.data.repositories.map;

import es.upm.etsisi.poo.app2.data.model.user.User;
import es.upm.etsisi.poo.app2.data.repositories.RepositoryUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RepositoryMapUser<T extends User> implements RepositoryUser<T> {

    protected final Map<String, T> map;
    protected String id;

    public RepositoryMapUser(){
        this.map = new HashMap<>();
        this.id = "";
    }

    @Override
    public void add(T entity, String id) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public T findById(String id) {
       return null;
    }

    @Override
    public T findByMail(String mail) {
      return null;
    }

}

