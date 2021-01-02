package core.models;

public class BankAccount {
    private String id;
    private String label;
    private String bic;
    private String ownerFirstName;
    private String ownerLastName;
    private String iban;


    public BankAccount(String id,String label, String bic, String iban, String ownerFirstName, String ownerLastName){
        this.id = id;
        this.label = label;
        this.bic = bic;
        this.iban = iban;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return this.ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getId() {
        return this.id;
    }

    public String toString(){
        return("new bank account: id: "+this.id+ " label: "+this.label+ " bic: "+this.bic+" iban: "+this.iban+" FirstName: "+this.ownerFirstName+ " LastName: "+ this.ownerLastName);
    }
}
