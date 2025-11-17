package es.upm.etsisi.poo.app2.data.model;

import java.util.Objects;

public abstract class Entity <T> {
    private T id;

    protected Entity(){
        this.id = null;
    }

    protected Entity(T id){
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object entity){
        if(this == entity){
            return true;
        }
        if(entity == null || this.getClass() != entity.getClass()){
            return false;
        }
        Entity<?> other = (Entity<?>) entity;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode(){
       return Objects.hash(this.id);
    }

    @Override
    public String toString(){
        return "Entity{id=" + id + "}";
    }
}
