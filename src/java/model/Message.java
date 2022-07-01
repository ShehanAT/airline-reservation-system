package model;

public class Message {
    int message_id;
    String message_surname;
    String message_email;
    String message_subject;
    String message_content;
    String message_date;
    int message_notRead;
    int message_reply;
    public Message() {
    }

    public Message(String message_surname, String message_email, String message_subject, String message_content) {
        this.message_surname = message_surname;
        this.message_email = message_email;
        this.message_subject = message_subject;
        this.message_content = message_content;
    }
    public Message(int message_id, String message_surname, String message_email, String message_subject, String message_content, String message_date, int message_notRead,int message_reply) {
        this.message_id = message_id;
        this.message_surname = message_surname;
        this.message_email = message_email;
        this.message_subject = message_subject;
        this.message_content = message_content;
        this.message_date = message_date;
        this.message_notRead = message_notRead;
        this.message_reply = message_reply;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_surname() {
        return message_surname;
    }

    public void setMessage_surname(String message_surname) {
        this.message_surname = message_surname;
    }

    public String getMessage_email() {
        return message_email;
    }

    public void setMessage_email(String message_email) {
        this.message_email = message_email;
    }

    public String getMessage_subject() {
        return message_subject;
    }

    public void setMessage_subject(String message_subject) {
        this.message_subject = message_subject;
    }

    public String getMessage_contents() {
        return message_content;
    }

    public void setMessage_contents(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_date() {
        return message_date;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public int getMessage_notRead() {
        return message_notRead;
    }

    public void setMessage_notRead(int message_notRead) {
        this.message_notRead = message_notRead;
    }
    public int getMessage_reply() {
        return message_reply;
    }

    public void setMessage_reply(int message_reply) {
        this.message_reply = message_reply;
    }
    
    
}
