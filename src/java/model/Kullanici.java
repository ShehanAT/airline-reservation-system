package model;

public class Kullanici {

    protected int user_id;
    protected String user_ad;
    protected String user_surname;
    protected String user_email;
    protected String user_password;
    protected int user_authorization;

    public Kullanici() {
    }

    public Kullanici(int user_id, String user_password) {
        this.user_id = user_id;
        this.user_sifre = user_sifre;
    }

    public Kullanici(String user_email, String user_sifre) {
        this.user_email = user_email;
        this.user_sifre = user_sifre;
    }

    public Kullanici(int user_id, String user_ad, String user_surname, String user_email, String user_sifre) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_sifre = user_sifre;
    }

    public Kullanici(String user_ad, String user_surname, String user_email, int user_authorization) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_authorization = user_authorization;
    }

    public Kullanici(String user_ad, String user_surname, String user_email, String user_sifre, int user_authorization) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_sifre = user_sifre;
        this.user_authorization = user_authorization;
    }

    public Kullanici(int user_id, String user_ad, String user_surname, String user_email, int user_authorization) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_authorization = user_authorization;
    }

    public Kullanici(int user_id, String user_ad, String user_surname, String user_email, String user_sifre, int user_authorization) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_sifre = user_sifre;
        this.user_authorization = user_authorization;
    }

    public Kullanici(String user_ad, String user_surname, String user_email, String user_sifre) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_sifre = user_sifre;
    }
    
    public Kullanici(int user_id, String user_ad, String user_surname, String user_email) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
    }
    
    public int getKullanici_id() {
        return user_id;
    }

    public void setKullanici_id(int user_id) {
        this.user_id = user_id;
    }

    public String getKullanici_ad() {
        return user_ad;
    }

    public void setKullanici_ad(String user_ad) {
        this.user_ad = user_ad;
    }

    public String getKullanici_surname() {
        return user_surname;
    }

    public void setKullanici_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getKullanici_email() {
        return user_email;
    }

    public void setKullanici_email(String user_email) {
        this.user_email = user_email;
    }

    public String getKullanici_sifre() {
        return user_sifre;
    }

    public void setKullanici_sifre(String user_sifre) {
        this.user_sifre = user_sifre;
    }

    public int getKullanici_yetki() {
        return user_authorization;
    }

    public void setKullanici_yetki(int user_authorization) {
        this.user_authorization = user_authorization;
    }
    
    
}