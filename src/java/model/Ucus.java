package model;

public class Ucus {
    protected int flight_id;
    protected int flight_departure_id;
    protected int end_heir_id;
    protected String flight_date;
    protected String flight_hour;
    protected String flight_time;
    protected int firma_id;
    protected int ucak_id;
    protected double flight_fare;
    protected String firma_ad;
    protected String ucak_ad;
    protected String ucus_kalkis;
    protected String ucus_varis;

    public Ucus() {
    }

    public Ucus(int flight_id, int flight_departure_id, int end_heir_id, String flight_date, String flight_hour, String flight_time, int firma_id, int ucak_id, double flight_fare) {
        this.flight_id = flight_id;
        this.flight_departure_id = flight_departure_id;
        this.end_heir_id = end_heir_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.firma_id = firma_id;
        this.ucak_id = ucak_id;
        this.flight_fare = flight_fare;
    }

    public Ucus(int flight_id, String flight_date, String flight_hour, String flight_time, double flight_fare, String firma_ad, String ucak_ad, String ucus_kalkis, String ucus_varis) {
        this.flight_id = flight_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.flight_fare = flight_fare;
        this.firma_ad = firma_ad;
        this.ucak_ad = ucak_ad;
        this.ucus_kalkis = ucus_kalkis;
        this.ucus_varis = ucus_varis;
    }

    public Ucus(int flight_departure_id, int end_heir_id, String flight_date, String flight_hour, String flight_time, int firma_id, int ucak_id, double flight_fare) {
        this.flight_departure_id = flight_departure_id;
        this.end_heir_id = end_heir_id;
        this.flight_date = flight_date;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.firma_id = firma_id;
        this.ucak_id = ucak_id;
        this.flight_fare = flight_fare;
    }
    
    public int getUcus_id() {
        return flight_id;
    }

    public void setUcus_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getUcus_kalkis_id() {
        return flight_departure_id;
    }

    public void setUcus_kalkis_id(int flight_departure_id) {
        this.flight_departure_id = flight_departure_id;
    }

    public int getUcus_varis_id() {
        return end_heir_id;
    }

    public void setUcus_varis_id(int end_heir_id) {
        this.end_heir_id = end_heir_id;
    }

    public String getUcus_tarih() {
        return flight_date;
    }

    public void setUcus_tarih(String flight_date) {
        this.flight_date = flight_date;
    }

    public String getUcus_saat() {
        return flight_hour;
    }

    public void setUcus_saat(String flight_hour) {
        this.flight_hour = flight_hour;
    }

    public String getUcus_sure() {
        return flight_time;
    }

    public void setUcus_sure(String flight_time) {
        this.flight_time = flight_time;
    }

    public int getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(int firma_id) {
        this.firma_id = firma_id;
    }

    public int getUcak_id() {
        return ucak_id;
    }

    public void setUcak_id(int ucak_id) {
        this.ucak_id = ucak_id;
    }

    public double getUcus_ucret() {
        return flight_fare;
    }

    public void setUcus_ucret(double flight_fare) {
        this.flight_fare = flight_fare;
    }

    public String getFirma_ad() {
        return firma_ad;
    }

    public void setFirma_ad(String firma_ad) {
        this.firma_ad = firma_ad;
    }

    public String getUcak_ad() {
        return ucak_ad;
    }

    public void setUcak_ad(String ucak_ad) {
        this.ucak_ad = ucak_ad;
    }

    public String getUcus_kalkis() {
        return ucus_kalkis;
    }

    public void setUcus_kalkis(String ucus_kalkis) {
        this.ucus_kalkis = ucus_kalkis;
    }

    public String getUcus_varis() {
        return ucus_varis;
    }

    public void setUcus_varis(String ucus_varis) {
        this.ucus_varis = ucus_varis;
    }
    
    
    
}
