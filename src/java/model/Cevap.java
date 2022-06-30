package model;

public class Cevap {
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

    public Cevap() {
    }

    public Cevap(int reply_id, int message_id, String reply_icerik, String reply_title, String reply_tarih, String message_surname, String message_email, String message_konu, String message_icerik, String message_tarih) {
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
    
    public Cevap(int message_id, String reply_icerik, String reply_title) {
        this.message_id = message_id;
        this.reply_icerik = reply_icerik;
        this.reply_title = reply_title;
    }

    public String getMesaj_surname() {
        return message_surname;
    }

    public void setMesaj_surname(String message_surname) {
        this.message_surname = message_surname;
    }

    public String getMesaj_email() {
        return message_email;
    }

    public void setMesaj_email(String mesaj_email) {
        this.mesaj_email = mesaj_email;
    }

    public String getMesaj_konu() {
        return message_subject;
    }

    public void setMesaj_konu(String message_subject) {
        this.message_subject = message_subject;
    }

    public String getMesaj_icerik() {
        return message_content;
    }

    public void setMesaj_icerik(String message_content) {
        this.message_content = message_content;
    }

    public String getMesaj_tarih() {
        return message_date;
    }

    public void setMesaj_tarih(String message_date) {
        this.message_date = message_date;
    }

    public int getMesaj_notRead() {
        return mesaj_notRead;
    }

    public void setMesaj_notRead(int mesaj_notRead) {
        this.mesaj_notRead = mesaj_notRead;
    }

    public int getMesaj_reply() {
        return mesaj_reply;
    }

    public void setMesaj_reply(int mesaj_reply) {
        this.mesaj_reply = mesaj_reply;
    }
    
    public int getCevap_id() {
        return reply_id;
    }

    public void setCevap_id(int reply_id) {
        this.review_id = review_id;
    }

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public String getCevap_icerik() {
        return review_icerik;
    }

    public void setCevap_icerik(String review_icerik) {
        this.review_icerik = review_icerik;
    }

    public String getCevap_title() {
        return review_title;
    }

    public void setCevap_title(String review_title) {
        this.review_title = review_title;
    }

    public String getCevap_tarih() {
        return review_tarih;
    }

    public void setCevap_tarih(String review_tarih) {
        this.review_tarih = review_tarih;
    }
    
    
}
