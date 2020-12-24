package core.models;

import java.util.Date;

public class BankAccountToUserTransaction extends Transaction{

    public BankAccountToUserTransaction(Float amount, Date dateCreated, BankAccount sender_fk, User receiver_fk) {
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public BankAccount getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(BankAccount sender_fk) {
        this.sender_fk = sender_fk;
    }

    public User getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(User receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    private BankAccount sender_fk;

    private User receiver_fk;
}
