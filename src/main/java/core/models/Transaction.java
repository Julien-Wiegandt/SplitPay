package core.models;

import java.util.Date;

/**
 * Model of the Transaction which represents a transaction between two actors.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public abstract class Transaction implements Comparable<Transaction> {

    protected String name;
    protected Float amount;
    protected Date dateCreated;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String toString() {
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

    /**
     * Compares two transactions by their creation date.
     *
     * @param t1 Transaction with which we will make the comparison.
     * @return <0 if t1's DateCreated is bigger than this's DateCreated, 0 if equals, >0 if t1's DateCreated is smaller than this's DateCreated.
     */
    public int compareTo(Transaction t1) {
        return this.getDateCreated().compareTo(t1.getDateCreated());
    }
}
