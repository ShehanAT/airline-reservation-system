package model;

public class Reply {
    int reply_id;
    int message_id;
    String reply_icerik;
    String reply_title;
    String reply_tarih;
    String message_surname;
    String message_email;
    String message_konu;
    String message_icerik;
    String message_tarih;
    int message_notRead;
    int message_reply;

    public Reply() {
    }

    public Reply(int reply_id, int message_id, String reply_icerik, String reply_title, String reply_tarih, String message_surname, String message_email, String message_konu, String message_icerik, String message_tarih) {
        this.reply_id = reply_id;
        this.message_id = message_id;
        this.reply_icerik = reply_icerik;
        this.reply_title = reply_title;
        this.reply_tarih = reply_tarih;
        this.message_surname = message_surname;
        this.message_email = message_email;
        this.message_konu = message_konu;
        this.message_icerik = message_icerik;
        this.message_tarih = message_tarih;
    }
    
    public Reply(int message_id, String reply_icerik, String reply_title) {
        this.message_id = message_id;
        this.reply_icerik = reply_icerik;
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

    public String getMessage_icerik() {
        return message_content;
    }

    public void setMessage_icerik(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_tarih() {
        return message_date;
    }

    public void setMessage_tarih(String message_date) {
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

    public String getReply_icerik() {
        return review_icerik;
    }

    public void setReply_icerik(String review_icerik) {
        this.review_icerik = review_icerik;
    }

    public String getReply_title() {
        return review_title;
    }

    public void setReply_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReply_tarih() {
        return review_tarih;
    }

    public void setReply_tarih(String review_tarih) {
        this.review_tarih = review_tarih;
    }
    
    
}
