package persist.dao;

import core.models.NormalUser;
import core.models.User;

import java.util.ArrayList;

public abstract class FriendDAO {


    public abstract ArrayList<NormalUser> getFriends(String id);

    /**
     * @param user
     * @param friend

     */
    public abstract void addFriend(User user, User friend);

    /**
     * @param user
     * @param friend

     */

    public abstract void deleteFriend(User user, User friend);


}
