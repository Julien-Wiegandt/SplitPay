package persist.models;

public class NormalUser extends User {

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
    private String firstName;
    private String LastName;

    public NormalUser(String firstName, String LastName, String id, String email, String phone, String password, String nickname, Float balance, String validationCode) {
        super(id, email, phone, password, nickname, balance, validationCode);
        this.firstName = firstName;
        this.LastName = LastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.LastName;
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
                ", lastName='" + this.getLastName() + '\'' +
                ", firstName='" + this.getFirstName() + '\'' +
                '}';
    }

}
