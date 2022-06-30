package model;

public class Mesaj {
    int mesaj_id;
    String mesaj_surname;
    String mesaj_email;
    String message_subject;
    String message_content;
    String message_date;
    int mesaj_notRead;
    int mesaj_review;
    public Mesaj() {
    }

    public Mesaj(String mesaj_surname, String mesaj_email, String message_subject, String message_content) {
        this.mesaj_surname = mesaj_surname;
        this.mesaj_email = mesaj_email;
        this.message_subject = message_subject;
        this.message_content = message_content;
    }
    public Mesaj(int mesaj_id, String mesaj_surname, String mesaj_email, String message_subject, String message_content, String message_date, int mesaj_notRead,int mesaj_review) {
        this.mesaj_id = mesaj_id;
        this.mesaj_surname = mesaj_surname;
        this.mesaj_email = mesaj_email;
        this.message_subject = message_subject;
        this.message_content = message_content;
        this.message_date = message_date;
        this.mesaj_notRead = mesaj_notRead;
        this.mesaj_review = mesaj_review;
    }

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public String getMesaj_surname() {
        return mesaj_surname;
    }

    public void setMesaj_surname(String mesaj_surname) {
        this.mesaj_surname = mesaj_surname;
    }

    public String getMesaj_email() {
        return mesaj_email;
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
    public int getMesaj_review() {
        return mesaj_review;
    }

    public void setMesaj_review(int mesaj_review) {
        this.message_reply = message_reply;
    }
    
    
}
