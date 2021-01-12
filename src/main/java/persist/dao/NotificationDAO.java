package persist.dao;

import core.models.Notification;

import java.util.ArrayList;

public abstract class NotificationDAO {


    public abstract void addNotification(Notification notification);
    public abstract void deleteNotificationById(String id);
    public abstract ArrayList<Notification> getNotifications(String id);
}
