package persist.models;

public class StoreOwner extends User {

    /**
     * Default constructor
     *
     * @param email
     * @param phone
     * @param password
     * @param nickname
     * @param balance
     * @param validationCode
     */
    private String siret;
    private String companyName;
    private String address;

    public StoreOwner(String id, String email, String phone, String siret, String password, String nickname, Float balance, String validationCode, String companyName, String address) {
        super(id, email, phone, password, nickname, balance, validationCode);
        this.siret = siret;
        this.companyName = companyName;
        this.address = address;
    }

    public String getSiret(){
        return this.siret;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String getAddress(){
        return this.address;
    }

    public String toString() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", phone='" + this.getPhone() + '\'' +
                ", nickname='" + this.getNickname() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", balance=" + this.getBalance() +
                ", validationCode='" + this.getValidationCode() + '\'' +
                ", companyName='" + this.getCompanyName() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                '}';
    }
}
