package core.facade;

import core.models.NormalUser;
import core.models.User;
import persist.DAOFactory;
import persist.dao.FriendDAO;
import persist.dao.mysql.MySqlDAOFactory;
import persist.dao.mysql.MySqlFriendDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendFacade {

    private static DAOFactory daoFactory = new MySqlDAOFactory();
    private static FriendFacade friendFacade;
    private FriendDAO friendDao;


    //------------------------------------- Constructor--------------------------------

    private FriendFacade() {
        MySqlFriendDAO friendDAO = daoFactory.createFriendDao();
        setFriendDao(friendDAO);

    }

    // Singleton
    public static FriendFacade getFriendFacade() {
        // TODO implement here
        if (friendFacade == null) {
            setFriendFacade(new FriendFacade());
        }

        return friendFacade;
    }


    //------------------------------------- Getters and Setters--------------------------------

    public static void setFriendFacade(FriendFacade friendFacade) {
        FriendFacade.friendFacade = friendFacade;
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(DAOFactory daoFactory) {
        FriendFacade.daoFactory = daoFactory;
    }

    public FriendDAO getFriendDao() {
        return friendDao;
    }

    public void setFriendDao(FriendDAO friendDao) {
        this.friendDao = friendDao;
    }

    //------------------------------------- Methods--------------------------------

    public void addFriendByPhone(String phone) {
        UserFacade userFacade = UserFacade.getUserFacade();
        User friend = userFacade.findUserByPhone(phone);
        getFriendDao().addFriend(userFacade.getLoggedUser(), friend);

    }

    public void addFriendByEmail(String email) throws SQLException {
        UserFacade userFacade = UserFacade.getUserFacade();
        User friend = userFacade.findUserByEmail(email);
        getFriendDao().addFriend(userFacade.getLoggedUser(), friend);

    }

    public void deleteFriend(User friend) {
        UserFacade userFacade = UserFacade.getUserFacade();
        getFriendDao().deleteFriend(userFacade.getLoggedUser(), friend);

    }

    public ArrayList<NormalUser> getFriends() {
        String id = UserFacade.getUserFacade().getLoggedUser().getId();
        return getFriendDao().getFriends(id);
    }
}
