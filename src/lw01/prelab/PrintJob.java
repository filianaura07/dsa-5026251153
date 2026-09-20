package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private String id;
    private int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("pages harus lebih dari 0");
        }
        this.id = id;
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public String label() {
        return "Print";
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies harus lebih dari 0");
        }
        return copies * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}