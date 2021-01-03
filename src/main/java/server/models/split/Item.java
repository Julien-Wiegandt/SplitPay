package server.models.split;

public class Item {

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
}
