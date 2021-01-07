package server.models.split;

import java.io.Serializable;

public class Item implements Serializable {

    private String label;
    private double price;
    private boolean isPicked = false;

    public Item(String label, double price){
        this.label=label;
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public String getLabel(){
        return label;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }

    @Override
    public String toString() {
        return "Item{" +
                "label='" + label + '\'' +
                ", price=" + price +
                ", isPicked=" + isPicked +
                '}';
    }
}
