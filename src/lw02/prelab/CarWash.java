package lw02.prelab;

public class CarWash extends WashService {
    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public String label() {
        return "Car";
    }
    
    @Override
    public int calculateCharge() {
        int days = getdays();
        int total = 15000;

        if (days <= 3) {
            total += days * 35000;
        } else {
            total += 3 * 35000;
            total += (days - 3) * 25000;
        }
        return total;
    }
}
