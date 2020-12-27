package persist.dao;

import core.models.Notification;

public abstract class NotificationDAO {


    public abstract void addNotification(Notification notification);
    public abstract void deleteNotificationById(String id);

    public abstract void show();
}
