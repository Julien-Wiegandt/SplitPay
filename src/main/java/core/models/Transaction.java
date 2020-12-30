package core.models;

import java.util.Comparator;
import java.util.Date;

public abstract class Transaction implements Comparable<Transaction> {

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String toString(){
        return this.getClass() + " " + this.getAmount() + " " + this.getDateCreated();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    protected Float amount;

    protected Date dateCreated;

    public int compareTo(Transaction t1){
        return this.getDateCreated().compareTo(t1.getDateCreated());
    }
}
