package core.models;

import java.util.Date;

public class UserToUserTransaction extends Transaction{

    public UserToUserTransaction(Float amount, Date dateCreated, NormalUser sender_fk, NormalUser receiver_fk) {
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



    public NormalUser getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(NormalUser receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    private NormalUser sender_fk;

    private NormalUser receiver_fk;

}
