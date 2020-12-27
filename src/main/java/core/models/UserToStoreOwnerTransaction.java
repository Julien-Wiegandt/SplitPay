package core.models;

import core.facade.UserFacade;

import java.util.Date;

public class UserToStoreOwnerTransaction extends Transaction{

    public UserToStoreOwnerTransaction(Float amount, Date dateCreated, User sender_fk, StoreOwner receiver_fk) {
        this.name = "UserToStoreOwnerTransaction";
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.sender_fk = sender_fk;
        this.receiver_fk = receiver_fk;
    }

    public User getSender_fk() {
        return sender_fk;
    }

    public void setSender_fk(User sender_fk) {
        this.sender_fk = sender_fk;
    }

    public StoreOwner getReceiver_fk() {
        return receiver_fk;
    }

    public void setReceiver_fk(StoreOwner receiver_fk) {
        this.receiver_fk = receiver_fk;
    }

    public String toString(){
        if(UserFacade.getUserFacade().isNormalUser()){
            return "To StoreOwner " + this.getReceiver_fk() + ": -" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        }else{
            return "From Customer " + this.getSender_fk() + ": +" + this.getAmount() + "€ on " + this.getDateCreated().toString();
        }
    }

    private User sender_fk;

    private StoreOwner receiver_fk;

}
