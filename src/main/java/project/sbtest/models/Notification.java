package project.sbtest.models;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Long notificationId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "NotificationType")
    private String notificationType;

    @Column(name = "NotificationMessage")
    private String notificationMessage;

    @Column(name = "NotificationDate")
    private Timestamp notificationDate;

    // Getters and setters

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }
}
