package es.upm.etsisi.poo.app2.data.model.user;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;

public class Client extends User{
    private String cashierId;

    public Client(String name, String mail, String cashierId){
        super(name, mail);
        if(!cashierId.matches("UW[0-9]{7}")){
            throw new InvalidAttributeException("Invalid cashierId");
        }
        this.cashierId = cashierId;
    }

    public String getCashierId() {
        return this.cashierId;
    }

    public void setCashierId(String cashierId) {
        if(!cashierId.matches("UW[0-9]{7}")){
            throw new InvalidAttributeException("Invalid cashierId");
        }
        this.cashierId = cashierId;
    }

    @Override
    public void setId(String id) {
        if(id.matches("^[0-9]{8}[A-Z]$")) throw new InvalidAttributeException("Invalid DNI");
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{identifier='" + this.getId() + "', name='" + this.getName() +
                "', email='" + this.getMail() + "', cash=" + this.cashierId + "}";
    }
}
