package core.models;

import java.util.Date;

public class UserToBankAccountTransaction extends Transaction{

    public UserToBankAccountTransaction(Float amount, Date dateCreated, NormalUser sender_fk, BankAccount receiver_fk) {
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public NormalUser getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(NormalUser sender_fk) {
        this.sender_fk = sender_fk;
    }

    public BankAccount getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(BankAccount receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    private NormalUser sender_fk;

    private BankAccount receiver_fk;
}
