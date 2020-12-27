package core.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCard {
    private String number;
    private String dbId;
    private String nameOwner;
    private Date date;
    private String cvv;

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public Date getDate() {
        return date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CreditCard(String dbId, String number, String nameOwner, Date date, String cvv){
        this.dbId = dbId;
        this.number = number;
        this.nameOwner = nameOwner;
        this.date = date;
        this.cvv = cvv;
    }


    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return("id: "+ this.dbId + ",number: " + this.number + ", nameOwner: "+ this.nameOwner + ", date: " +dateFormat.format(this.date)+ ", cvv: "+ this.cvv );
    }
}
