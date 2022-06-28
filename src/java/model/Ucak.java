package model;

public class Ucak {
    int ucak_id;
    String ucak_ad;
    int ucak_koltuk;
    int company_id;
    String company_name;

    public Ucak() {
    }

    public Ucak(int ucak_id, String ucak_ad) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
    }

    public Ucak(String ucak_ad, int ucak_koltuk, int company_id) {
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.company_id = company_id;
    }
    
    public Ucak(int ucak_id, String ucak_ad, int ucak_koltuk, int company_id) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.company_id = company_id;
    }

    public Ucak(int ucak_id, String ucak_ad, int ucak_koltuk, String company_name) {
        this.ucak_id = ucak_id;
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
        this.company_name = company_name;
    }

    public int getUcak_id() {
        return ucak_id;
    }

    public void setUcak_id(int ucak_id) {
        this.ucak_id = ucak_id;
    }

    public String getUcak_ad() {
        return ucak_ad;
    }

    public void setUcak_ad(String ucak_ad) {
        this.ucak_ad = ucak_ad;
    }

    public int getUcak_koltuk() {
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

    public String getFirma_ad() {
        return company_name;
    }

    public void setFirma_ad(String company_name) {
        this.company_name = company_name;
    }
    
    
    
}
