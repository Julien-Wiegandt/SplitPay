package core.models;

import java.util.Date;

public class Notification {


    private String id ;
    private String userID;
    private String label;
    private String message;



    //------------------------------------- Constructor--------------------------------

    public Notification(String id, String userID, String label, String message) {
        this.id = id;
        this.userID = userID;
        this.label = label;
        this.message = message;
    }
    public Notification(String userID, String label, String message) {
        this.userID = userID;
        this.label = label;
        this.message = message;
    }

    //------------------------------------- Getters and Setters--------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  label + " : "+  message;
    }
}
