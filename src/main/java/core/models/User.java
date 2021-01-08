package core.models;

/**
 *
 */
public class User {

    /**
     * Default constructor
     */
    public User() {
    }
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }


    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    private String validationCode;

    public String getId(){ return this.id;}

    public String getEmail(){ return this.email;}
    public String getPhone(){ return this.phone;}
    public String getNickname(){return this.nickname;}
    public String getPassword(){return this.password;}
    public Float getBalance(){return this.balance;}
    public String getValidationCode(){ return this.validationCode;}

    public void setId(String id) {
        this.id = id;
    }


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