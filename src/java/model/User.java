package model;

public class User {

    protected int user_id;
    protected String user_ad;
    protected String user_surname;
    protected String user_email;
    protected String user_password;
    protected int user_authorization;

    public User() {
    }

    public User(int user_id, String user_password) {
        this.user_id = user_id;
        this.user_password = user_password;
    }

    public User(String user_email, String user_sifre) {
        this.user_email = user_email;
        this.user_password = user_sifre;
    }

    public User(int user_id, String user_ad, String user_surname, String user_email, String user_sifre) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_sifre;
    }

    public User(String user_ad, String user_surname, String user_email, int user_authorization) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_authorization = user_authorization;
    }

    public User(String user_ad, String user_surname, String user_email, String user_password, int user_authorization) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_authorization = user_authorization;
    }

    public User(int user_id, String user_ad, String user_surname, String user_email, int user_authorization) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_authorization = user_authorization;
    }

    public User(int user_id, String user_ad, String user_surname, String user_email, String user_password, int user_authorization) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_authorization = user_authorization;
    }

    public User(String user_ad, String user_surname, String user_email, String user_password) {
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
    }
    
    public User(int user_id, String user_ad, String user_surname, String user_email) {
        this.user_id = user_id;
        this.user_ad = user_ad;
        this.user_surname = user_surname;
        this.user_email = user_email;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_ad;
    }

    public void setUser_name(String user_ad) {
        this.user_ad = user_ad;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_authority() {
        return user_authorization;
    }

    public void setUser_authority(int user_authorization) {
        this.user_authorization = user_authorization;
    }
    
    
}