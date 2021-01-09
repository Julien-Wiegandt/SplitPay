package core.models;

import core.facade.UserFacade;

import java.util.Date;

/**
 * Model of the UserToUserTransaction which represents the transaction from a NormalUser's balance to another NormalUser's.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public class UserToUserTransaction extends Transaction {

    private int sender_fk;
    private int receiver_fk;

    /**
     * UserToUserTransaction's constructor.
     *
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public UserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        this.name = "UserToUserTransaction";
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public int getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(int sender_fk) {
        this.sender_fk = sender_fk;
    }

    public int getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(int receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    /**
     * Represents the transaction.
     *
     * @return the String representation of the transaction.
     */
    public String toString() {
        if (this.getSender_fk() == Integer.valueOf(UserFacade.getUserFacade().getLoggedUser().getId())) {
            return "To User " + this.getReceiver_fk() + ": -" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        } else {
            return "From User " + this.getSender_fk() + ": +" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        }
    }

}
