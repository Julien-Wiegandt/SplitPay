package core.models;

import java.util.Date;

public abstract class Transaction {

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String toString(){
        return this.getClass() + " " + this.getAmount() + " " + this.getDateCreated();
    }

    protected Float amount;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    protected Date dateCreated;

}
