package core.facade;

import core.auth.Session;
import core.models.NormalUser;
import core.models.User;
import persist.DAOFactory;
import persist.dao.FriendDAO;
import persist.dao.UserDAO;
import persist.dao.mysql.MySqlDAOFactory;
import persist.dao.mysql.MySqlFriendDAO;

public class FriendFacade{

    private static DAOFactory daoFactory = new MySqlDAOFactory();
    private FriendDAO friendDao;
    private static FriendFacade friendFacade;


    //------------------------------------- Constructor--------------------------------

    private FriendFacade() {
        MySqlFriendDAO friendDAO = daoFactory.createFriendDao();
        setFriendDao(friendDAO);

    }

    // Singleton
    public static FriendFacade getFriendFacade() {
        // TODO implement here
        if(friendFacade==null){
            setFriendFacade(new FriendFacade());
        }

        return friendFacade;
    }


    //------------------------------------- Getters and Setters--------------------------------

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

    public static void setFriendFacade(FriendFacade friendFacade) {
        FriendFacade.friendFacade = friendFacade;
    }

    //------------------------------------- Methods--------------------------------

    public void addFriendByPhone(String phone){
        UserFacade userFacade = UserFacade.getUserFacade();
        User friend = userFacade.findUserByPhone(phone);
        getFriendDao().addFriend(userFacade.getLoggedUser(),friend);

    }

    public void addFriendByEmail(String email){

    }

    public void deleteFriend(User friend) {
        UserFacade userFacade = UserFacade.getUserFacade();
        getFriendDao().deleteFriend(userFacade.getLoggedUser(),friend);

    }

}
