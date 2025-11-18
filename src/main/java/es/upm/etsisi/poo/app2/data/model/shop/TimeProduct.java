package es.upm.etsisi.poo.app2.data.model.shop;

import java.time.LocalDate;

public class TimeProduct extends Product {
    private final LocalDate openDate;
    private final Integer MAX_PEOPLE;
    private final TimeProductType type;
    private final Integer PLANNING_HOURS;
    private Integer actualPeople;

    public TimeProduct(String name, TimeProductType type, Double price, LocalDate openDate, Integer MAX_PEOPLE) {
        super(name, price);
        this.type = type;
        this.openDate = openDate;
        this.MAX_PEOPLE = MAX_PEOPLE;
        if(type != null){
            this.PLANNING_HOURS = type.getPLANNING_HOURS();
        }else{
            this.PLANNING_HOURS = null;
        }
    }

    public LocalDate getOpenDate() {
        return this.openDate;
    }

    public Integer getMAX_PEOPLE() {
        return this.MAX_PEOPLE;
    }

    public TimeProductType getType() {
        return this.type;
    }

    public Integer getPLANNING_HOURS() {
        return this.PLANNING_HOURS;
    }

    public Integer getActualPeople() {
        return this.actualPeople;
    }

    public void setActualPeople(Integer actualPeople) {
        this.actualPeople = actualPeople;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        String productType;
        if(type == TimeProductType.MEETING){
            productType = "Meeting";
        }else{
            productType = "Food";
        }

       stringBuilder.append("{class:").append(productType)
               .append(", id:").append(getId())
               .append(", name:'").append(getName()).append("'")
               .append(", price:").append(getPrice())
               .append(", date of Event:");

        if (openDate == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(openDate.toString());
        }

        stringBuilder.append(", max people allowed:");
        if (MAX_PEOPLE == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(MAX_PEOPLE);
        }

        if (actualPeople != null) {
            stringBuilder.append(", actual people in event:").append(actualPeople);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
