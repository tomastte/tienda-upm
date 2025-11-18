package es.upm.etsisi.poo.app2.data.model.shop;

public enum TimeProductType {
    FOOD(72),
    MEETING(12);

    private final Integer PLANNING_HOURS;

    TimeProductType(Integer planningHours) {
        this.PLANNING_HOURS = planningHours;
    }

    public Integer getPLANNING_HOURS() {
        return this.PLANNING_HOURS;
    }
}
