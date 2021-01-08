package core.facade;

import core.models.Notification;
import core.models.User;
import persist.DAOFactory;
import persist.dao.NotificationDAO;
import persist.dao.NotificationDAO;
import persist.dao.mysql.MySqlDAOFactory;
import persist.dao.mysql.MySqlNotificationDAO;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class NotificationFacade {
    private static DAOFactory daoFactory = new MySqlDAOFactory();
    private NotificationDAO notificationDao;
    private static NotificationFacade notificationFacade;


    //------------------------------------- Constructor--------------------------------

    private NotificationFacade() {
        MySqlNotificationDAO notificationDAO = daoFactory.createNotificationDao();
        setNotificationDao(notificationDAO);

    }

    // Singleton
    public static NotificationFacade getNotificationFacade() {
        if(notificationFacade==null){
            setNotificationFacade(new NotificationFacade());
        }

        return notificationFacade;
    }

    //------------------------------------- Getters and Setters--------------------------------

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(DAOFactory daoFactory) {
        NotificationFacade.daoFactory = daoFactory;
    }

    public NotificationDAO getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDAO notificationDao) {
        this.notificationDao = notificationDao;
    }

    public static void setNotificationFacade(NotificationFacade notificationFacade) {
        NotificationFacade.notificationFacade = notificationFacade;
    }

    //------------------------------------- Methods--------------------------------

    /**
     * Creates a notification and add it to the database
     * @param userID
     * @param label
     * @param  message
     */

    public void addNotification(String userID, String label, String message) {
        Notification notification = new Notification(userID,label,message);
        getNotificationDao().addNotification(notification);



    }

    /**
     * delete from the database
     * @param id
     */
    public void deleteNotification(String id) {
        getNotificationDao().deleteNotificationById(id);

    };

    /**
     * Return all the notifications of a user
     */
    public ArrayList<Notification> getNotifications()
    {
        User user = UserFacade.getUserFacade().getLoggedUser();
        return getNotificationDao().getNotifications(user.getId());

    };





}
