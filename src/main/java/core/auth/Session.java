package core.auth;

import core.models.NormalUser;
import core.models.StoreOwner;
import core.models.User;

public class Session {
    private NormalUser loggedNormalUser = null;
    private StoreOwner loggedStoreOwner = null;

    /**
     * Returns the logged NormalUser if not logged returns null
     * @return
     */
    public NormalUser getLoggedNormalUser() {
        return loggedNormalUser;
    }

    /**
     * Sets the logged NormalUser
     * @param loggedNormalUser
     */
    public void setLoggedNormalUser(NormalUser loggedNormalUser) {
        this.loggedNormalUser = loggedNormalUser;
    }

    /**
     * Returns the logged StoreOwner if not logged returns null
     * @return
     */
    public StoreOwner getLoggedStoreOwner() {
        return loggedStoreOwner;
    }

    /**
     * Sets the logged StoreOwner
     * @param loggedStoreOwner
     */
    public void setLoggedStoreOwner(StoreOwner loggedStoreOwner) {
        this.loggedStoreOwner = loggedStoreOwner;
    }

    /**
     * This method receives a User and checks what type of user it is, it then register the user in the right place of the Session class
     * @param user
     */
    public void setLoggedUser(User user){
        if (user instanceof NormalUser) {
            setLoggedNormalUser((NormalUser) user);
        } else {
            setLoggedStoreOwner((StoreOwner) user);
        }
    }

    /**
     * Returns true if the logged in user is a NormalUser
     */
    public boolean isNormalUser(){
        return getLoggedNormalUser()!=null;
    }

    /**
     * Returns true if the logged in user is a StoreOwner
     */
    public boolean isStoreOwner(){
        return getLoggedStoreOwner()!=null;
    }

    /** pre : a user must be logged in
     * Returns the logged in user
     */
    public User getLoggedUser(){
        return isNormalUser() ? getLoggedNormalUser() : getLoggedStoreOwner();
    }

    /**
     * Clear any logged in user
     */
    public void logOut(){
        setLoggedStoreOwner(null);
        setLoggedNormalUser(null);
    }
}
