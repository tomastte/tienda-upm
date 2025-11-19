package es.upm.etsisi.poo.app2.data.model.user;

import es.upm.etsisi.poo.app2.data.model.Entity;

public class User extends Entity<String> {
    private String name;
    private String mail;

    public User(String id, String name, String mail){
        super(id);
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "{class:User, id:" + getId() +
                ", name:'" + name + "', mail:'" + mail + "'}";
    }
}
