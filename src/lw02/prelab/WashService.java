package lw02.prelab;

public class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days harus lebih dari 0");
        }
        this.id = id;
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public String Label() {
        return "Wash";
    }


    @override
    public abstract int calculateCharge();
    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units harus lebih dari 0");
        }
        return units * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }

}
