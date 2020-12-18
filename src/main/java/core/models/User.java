package core.models;

/**
 * 
 */
public class User {

    /**
     * Default constructor
     */
    public User(String id, String email, String phone, String password, String nickname, Float balance, String validationCode) {
        this.id=id;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.nickname=nickname;
        this.balance=balance;
        this.validationCode=validationCode;
    }


    private String id;
    private String email;
    private String phone;
    private String nickname;
    private String password;
    private Float balance;
    private String validationCode;

    public String getId(){ return this.id;}

    public String getEmail(){ return this.email;}
    public String getPhone(){ return this.phone;}
    public String getNickname(){return this.nickname;}
    public String getPassword(){return this.password;}
    public Float getBalance(){return this.balance;}
    public String getValidationCode(){ return this.validationCode;}
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", validationCode='" + validationCode + '\'' +
                '}';
    }
}