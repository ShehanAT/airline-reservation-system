package model;

public class Reply {
    int reply_id;
    int message_id;
    String reply_contents;
    String reply_title;
    String reply_date;
    String message_surname;
    String message_email;
    String message_konu;
    String message_contents;
    String message_date;
    int message_notRead;
    int message_reply;

    public Reply() {
    }

    public Reply(int reply_id, int message_id, String reply_contents, String reply_title, String reply_date, String message_surname, String message_email, String message_konu, String message_contents, String message_date) {
        this.reply_id = reply_id;
        this.message_id = message_id;
        this.reply_contents = reply_contents;
        this.reply_title = reply_title;
        this.reply_date = reply_date;
        this.message_surname = message_surname;
        this.message_email = message_email;
        this.message_konu = message_konu;
        this.message_contents = message_contents;
        this.message_date = message_date;
    }
    
    public Reply(int message_id, String reply_contents, String reply_title) {
        this.message_id = message_id;
        this.reply_contents = reply_contents;
        this.reply_title = reply_title;
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

    public String getMessage_konu() {
        return message_subject;
    }

    public void setMessage_konu(String message_subject) {
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
    
    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.review_id = review_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getReply_contents() {
        return review_contents;
    }

    public void setReply_contents(String review_contents) {
        this.review_contents = review_contents;
    }

    public String getReply_title() {
        return review_title;
    }

    public void setReply_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReply_date() {
        return review_date;
    }

    public void setReply_date(String review_date) {
        this.review_date = review_date;
    }
    
    
}
