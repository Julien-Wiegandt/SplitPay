package core.models;

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

    public NormalUser(String firstName, String LastName, String id, String email, String phone, String password, String nickname, Float balance) {
        super(id, email, phone, password, nickname, balance);
        this.firstName = firstName;
        this.LastName = LastName;
    }

    public NormalUser(){
        super();
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String toStringDebug() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", phone='" + this.getPhone() + '\'' +
                ", nickname='" + this.getNickname() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", balance=" + this.getBalance() +
                ", lastName='" + this.getLastName() + '\'' +
                ", firstName='" + this.getFirstName() + '\'' +
                '}';
    }

    public  String toString(){
        return this.firstName+" "+this.LastName+" ("+ this.getNickname() +")";
    }

}
