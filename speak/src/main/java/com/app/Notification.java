package com.app;

import java.util.Timer;
import java.util.TimerTask;

/*
 * Basic notification class that can be used to send notifications to users
 * @author Bryce Klein
 */
public class Notification {

    private String message;
    private User recipient;
    private String type;

    /* Default Constructor */
    public Notification() {
        this.message = "";
        this.recipient = null;
        this.type = "";
    }
    
    /** Parametized Constructor
     * @param message The message to send in the notification
     * @param recipient The user to send the notification to
     * @param type The type of notification to send
     */
    public Notification(String message, User recipient, String type) {
        this.message = message;
        this.recipient = recipient;
        this.type = type;
    }

    /*
     * Sends a notification to the specified user
     */
    public void sendNotification(User user) {
        System.out.println("Notification sent to " + user.getUserName() + ": " + message);
    }

    /*
     * Incorporates Java utility for time to schedule a reminder notification
     */
    public void scheduleReminder(long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendNotification(recipient);
            }
        }, delay);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
