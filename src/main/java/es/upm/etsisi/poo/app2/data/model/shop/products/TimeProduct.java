package es.upm.etsisi.poo.app2.data.model.shop.products;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;
import es.upm.etsisi.poo.app2.data.model.shop.TimeProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeProduct extends Product {
    private final LocalDate openDate;
    private final Integer MAX_PEOPLE;
    private final TimeProductType type;
    private final Integer planningHours;
    private Integer actualPeople;

    public TimeProduct(String name, TimeProductType type, Double price, LocalDate openDate, Integer MAX_PEOPLE) {
        super(name, price);
        this.type = type;
        this.openDate = openDate;
        this.MAX_PEOPLE = MAX_PEOPLE;
        this.planningHours = type.getPlanningHours();
        LocalDateTime date = this.openDate.atStartOfDay().plusHours(this.planningHours);
        if(LocalDateTime.now().isBefore(date)){
            throw new InvalidAttributeException("Error adding product");
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
        return this.planningHours;
    }

    public Integer getActualPeople() {
        return this.actualPeople;
    }

    public void setActualPeople(Integer actualPeople) {
        if(actualPeople < 0) {
            throw new InvalidAttributeException("ActualPeople cannot be negative");
        } else if(actualPeople > MAX_PEOPLE) {
            throw new InvalidAttributeException("ActualPeople cannot be greater than MAX_PEOPLE");
        }
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
               .append(", id:").append(this.getId())
               .append(", name:'").append(this.getName()).append("'")
               .append(", price:").append(this.getPrice())
               .append(", date of Event:");

        if (this.openDate == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(openDate);
        }

        stringBuilder.append(", max people allowed:");
        if (this.MAX_PEOPLE == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.MAX_PEOPLE);
        }

        if (this.actualPeople != null) {
            stringBuilder.append(", actual people in event:").append(this.actualPeople);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
