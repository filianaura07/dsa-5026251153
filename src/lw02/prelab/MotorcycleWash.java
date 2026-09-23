package lw02.prelab;

public class MotorcycleWash extends WashService {
    public MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override
    public String label() {
        return "Motorcycle Wash";
    }

    @Override
    public int calculateCharge() {
        return getDays() * 10000; 
    }
}











