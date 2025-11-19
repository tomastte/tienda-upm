package es.upm.etsisi.poo.app2.data.model.user;

public class Client extends User{
    private String cashierId;

    public Client(String name, String mail, String cashierId){
        super(cashierId, name, mail);
    }

    public String getCashierId() {
        return this.cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    @Override
    public String toString() {
        return "Client{identifier='" + getId() + "', name='" + getName() +
                "', email='" + getMail() + "', cash=" + cashierId + "}";
    }
}
