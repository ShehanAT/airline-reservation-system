package model;

public class Ucak {
    int plane_id;
    String ucak_name;
    int ucak_seat;
    int company_id;
    String company_name;

    public Ucak() {
    }

    public Ucak(int plane_id, String ucak_name) {
        this.plane_id = plane_id;
        this.ucak_name = ucak_name;
    }

    public Ucak(String ucak_name, int ucak_seat, int company_id) {
        this.ucak_name = ucak_name;
        this.ucak_seat = ucak_seat;
        this.company_id = company_id;
    }
    
    public Ucak(int plane_id, String ucak_name, int ucak_seat, int company_id) {
        this.plane_id = plane_id;
        this.ucak_name = ucak_name;
        this.ucak_seat = ucak_seat;
        this.company_id = company_id;
    }

    public Ucak(int plane_id, String ucak_name, int ucak_seat, String company_name) {
        this.plane_id = plane_id;
        this.ucak_name = ucak_name;
        this.ucak_seat = ucak_seat;
        this.company_name = company_name;
    }

    public int getUcak_id() {
        return plane_id;
    }

    public void setUcak_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getUcak_name() {
        return ucak_name;
    }

    public void setUcak_name(String ucak_name) {
        this.ucak_name = ucak_name;
    }

    public int getUcak_seat() {
        return ucak_koltuk;
    }

    public void setUcak_koltuk(int ucak_koltuk) {
        this.ucak_koltuk = ucak_koltuk;
    }

    public int getFirma_id() {
        return company_id;
    }

    public void setFirma_id(int company_id) {
        this.company_id = company_id;
    }

    public String getFirma_name() {
        return company_name;
    }

    public void setFirma_name(String company_name) {
        this.company_name = company_name;
    }
    
    
    
}
