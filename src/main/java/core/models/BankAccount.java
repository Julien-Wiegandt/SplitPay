package core.models;

public class BankAccount {

    public BankAccount(String id, String label, String bic, String iban, String ownerFirstName, String ownerLastName) {
        this.id = id;
        this.label = label;
        this.bic = bic;
        this.iban = iban;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String label;

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    private String bic;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    private String iban;

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    private String ownerFirstName;

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    private String ownerLastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

}
