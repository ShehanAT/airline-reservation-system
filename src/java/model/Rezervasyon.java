package model;

public class Rezervasyon  {
    int rezervasyon_id;
    String rezervasyon_tarih;
    String pnr_no;
    String yolcu_ad;
    String yolcu_soyad;  
    String yolcu_email;
    String yolcu_tel;
    String yolcu_tc;
    int yolcu_tip;
    String koltuk_no;
    int kullanici_id;
    int airport_departure_id;
    int airport_heir_id;
    String flight_date;
    int yetiskin_sayi;
    int cocuk_sayi;
    int flight_id;
    String kalkis_sehir;
    String kalkis_ad;
    String kalkis_kod;
    String varis_sehir;
    String varis_ad;
    String varis_kod;
    String flight_hour;
    String flight_time;
    String company_name;
    String company_logo;
    Double flight_fare;
    String ucus_s;
    String ucus_d;
    String varis_saat;
    String ucak_ad;
    int ucak_koltuk;
    int sonuc;
    int koltuk_dolu;
    String yolcu_tarih;
    Double yolcu_ucret;
    int durum;

    public Rezervasyon(int rezervasyon_id, String yolcu_ad, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, String yolcu_tarih) {
        this.rezervasyon_id = rezervasyon_id;
        this.yolcu_ad = yolcu_ad;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tarih = yolcu_tarih;
    }
    
    
    public Rezervasyon(int durum, int rezervasyon_id, String rezervasyon_tarih, String pnr_no, String yolcu_ad, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, int yolcu_tip, String koltuk_no, String flight_date, String kalkis_sehir, String kalkis_ad, String kalkis_kod, String varis_sehir, String varis_ad, String varis_kod, String flight_hour, String flight_time, String company_name, String company_logo, String ucus_s, String ucus_d, String varis_saat, String ucak_ad, String yolcu_tarih, Double yolcu_ucret) {
        this.durum = durum;
        this.rezervasyon_id = rezervasyon_id;
        this.rezervasyon_tarih = rezervasyon_tarih;
        this.pnr_no = pnr_no;
        this.yolcu_ad = yolcu_ad;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tip = yolcu_tip;
        this.koltuk_no = koltuk_no;
        this.flight_date = flight_date;
        this.kalkis_sehir = kalkis_sehir;
        this.kalkis_ad = kalkis_ad;
        this.kalkis_kod = kalkis_kod;
        this.varis_sehir = varis_sehir;
        this.varis_ad = varis_ad;
        this.varis_kod = varis_kod;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
        this.ucak_ad = ucak_ad;
        this.yolcu_tarih = yolcu_tarih;
        this.yolcu_ucret = yolcu_ucret;
    }
    
    public Rezervasyon(String pnr_no, String yolcu_ad, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, int yolcu_tip, String koltuk_no, int kullanici_id, int flight_id, String yolcu_tarih, Double yolcu_ucret) {
        this.pnr_no = pnr_no;
        this.yolcu_ad = yolcu_ad;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tip = yolcu_tip;
        this.koltuk_no = koltuk_no;
        this.kullanici_id = kullanici_id;
        this.flight_id = flight_id;
        this.yolcu_tarih = yolcu_tarih;
        this.yolcu_ucret = yolcu_ucret;
    }
    
    public Rezervasyon() {
    }
    
    public Rezervasyon(int sonuc) {
        this.sonuc = sonuc;
    }

    public Rezervasyon(String pnr_no, String yolcu_soyad) {
        this.pnr_no = pnr_no;
        this.yolcu_soyad = yolcu_soyad;
    }

    
    public Rezervasyon(String flight_date, int flight_id, String kalkis_sehir, String kalkis_ad, String kalkis_kod, String varis_sehir, String varis_ad, String varis_kod, String flight_hour, String flight_time, String company_name, String company_logo, Double flight_fare, String ucus_s, String ucus_d, String varis_saat, String ucak_ad, int ucak_koltuk) {
        this.flight_date = flight_date;
        this.flight_id = flight_id;
        this.kalkis_sehir = kalkis_sehir;
        this.kalkis_ad = kalkis_ad;
        this.kalkis_kod = kalkis_kod;
        this.varis_sehir = varis_sehir;
        this.varis_ad = varis_ad;
        this.varis_kod = varis_kod;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.flight_fare = flight_fare;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
        this.ucak_ad = ucak_ad;
        this.ucak_koltuk = ucak_koltuk;
    }
    public Rezervasyon(String flight_date, int flight_id, String kalkis_sehir, String kalkis_ad, String kalkis_kod, String varis_sehir, String varis_ad, String varis_kod, String flight_hour, String flight_time, String company_name, String company_logo, Double flight_fare, String ucus_s, String ucus_d, String varis_saat) {
        this.flight_date = flight_date;
        this.flight_id = flight_id;
        this.kalkis_sehir = kalkis_sehir;
        this.kalkis_ad = kalkis_ad;
        this.kalkis_kod = kalkis_kod;
        this.varis_sehir = varis_sehir;
        this.varis_ad = varis_ad;
        this.varis_kod = varis_kod;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.flight_fare = flight_fare;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
    }

    public Rezervasyon(int yetiskin_sayi, int cocuk_sayi) {
        this.yetiskin_sayi = yetiskin_sayi;
        this.cocuk_sayi = cocuk_sayi;
    }
    
    public Rezervasyon(int rezervasyon_id, String rezervasyon_tarih, String pnr_no, String yolcu_soyad) {
        this.rezervasyon_id = rezervasyon_id;
        this.rezervasyon_tarih = rezervasyon_tarih;
        this.pnr_no = pnr_no;
        this.yolcu_soyad = yolcu_soyad;
    }
    
    public Rezervasyon(int airport_departure_id, int airport_heir_id, String flight_date, int yetiskin_sayi, int cocuk_sayi) {
        this.airport_departure_id = airport_departure_id;
        this.airport_heir_id = airport_heir_id;
        this.flight_date = flight_date;
        this.yetiskin_sayi = yetiskin_sayi;
        this.cocuk_sayi = cocuk_sayi;
    }

    public Rezervasyon(int rezervasyon_id, String rezervasyon_tarih, String pnr_no, String yolcu_ad, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, int yolcu_tip, String koltuk_no, int kullanici_id, int flight_id) {
        this.rezervasyon_id = rezervasyon_id;
        this.rezervasyon_tarih = rezervasyon_tarih;
        this.pnr_no = pnr_no;
        this.yolcu_ad = yolcu_ad;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tip = yolcu_tip;
        this.koltuk_no = koltuk_no;
        this.kullanici_id = kullanici_id;
        this.flight_id = flight_id;
    }

    public Rezervasyon(String flight_date, String kalkis_sehir, String kalkis_ad, String kalkis_kod, String varis_sehir, String varis_ad, String varis_kod, String flight_hour, String company_name, String company_logo, String ucus_s, String ucus_d, String ucak_ad) {
        this.flight_date = flight_date;
        this.kalkis_sehir = kalkis_sehir;
        this.kalkis_ad = kalkis_ad;
        this.kalkis_kod = kalkis_kod;
        this.varis_sehir = varis_sehir;
        this.varis_ad = varis_ad;
        this.varis_kod = varis_kod;
        this.flight_hour = flight_hour;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.ucak_ad = ucak_ad;
    }
    public String getYolcu_tarih() {
        return yolcu_tarih;
    }
    
    public void setYolcu_tarih(String yolcu_tarih) {
        this.yolcu_tarih = yolcu_tarih;
    }
    
    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }
    
    public Double getYolcu_ucret() {
        return yolcu_ucret;
    }

    public void setYolcu_ucret(Double yolcu_ucret) {
        this.yolcu_ucret = yolcu_ucret;
    }

    public int getKoltuk_dolu() {
        return koltuk_dolu;
    }

    public void setKoltuk_dolu(int koltuk_dolu) {
        this.koltuk_dolu = koltuk_dolu;
    }

    public Rezervasyon(String koltuk_no) {
        this.koltuk_no = koltuk_no;
    }
    
    public int getUcak_koltuk() {
        return ucak_koltuk;
    }

    public void setUcak_koltuk(int ucak_koltuk) {
        this.ucak_koltuk = ucak_koltuk;
    }
    
    public String getUcak_ad() {
        return ucak_ad;
    }

    public void setUcak_ad(String ucak_ad) {
        this.ucak_ad = ucak_ad;
    }
    
    public String getYolcu_ad() {
        return yolcu_ad;
    }

    public void setYolcu_ad(String yolcu_ad) {
        this.yolcu_ad = yolcu_ad;
    }

    public String getYolcu_email() {
        return yolcu_email;
    }

    public void setYolcu_email(String yolcu_email) {
        this.yolcu_email = yolcu_email;
    }

    public String getYolcu_tel() {
        return yolcu_tel;
    }

    public void setYolcu_tel(String yolcu_tel) {
        this.yolcu_tel = yolcu_tel;
    }

    public String getYolcu_tc() {
        return yolcu_tc;
    }

    public void setYolcu_tc(String yolcu_tc) {
        this.yolcu_tc = yolcu_tc;
    }

    public int getYolcu_tip() {
        return yolcu_tip;
    }

    public void setYolcu_tip(int yolcu_tip) {
        this.yolcu_tip = yolcu_tip;
    }

    public String getKoltuk_no() {
        return koltuk_no;
    }

    public void setKoltuk_no(String koltuk_no) {
        this.koltuk_no = koltuk_no;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
    
    public int getRezervasyon_id() {
        return rezervasyon_id;
    }

    public void setRezervasyon_id(int rezervasyon_id) {
        this.rezervasyon_id = rezervasyon_id;
    }

    public String getRezervasyon_tarih() {
        return rezervasyon_tarih;
    }

    public void setRezervasyon_tarih(String rezervasyon_tarih) {
        this.rezervasyon_tarih = rezervasyon_tarih;
    }

    public String getPnr_no() {
        return pnr_no;
    }

    public void setPnr_no(String pnr_no) {
        this.pnr_no = pnr_no;
    }

    public String getYolcu_soyad() {
        return yolcu_soyad;
    }

    public void setYolcu_soyad(String yolcu_soyad) {
        this.yolcu_soyad = yolcu_soyad;
    }
    
    
    public String getVaris_saat() {
        return varis_saat;
    }

    public void setVaris_saat(String varis_saat) {
        this.varis_saat = varis_saat;
    }

    public String getUcus_s() {
        return ucus_s;
    }

    public void setUcus_s(String ucus_s) {
        this.ucus_s = ucus_s;
    }

    public String getUcus_d() {
        return ucus_d;
    }

    public void setUcus_d(String ucus_d) {
        this.ucus_d = ucus_d;
    }
    public int getUcus_id() {
        return flight_id;
    }

    public void setUcus_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getKalkis_sehir() {
        return kalkis_sehir;
    }

    public void setKalkis_sehir(String kalkis_sehir) {
        this.kalkis_sehir = kalkis_sehir;
    }

    public String getKalkis_ad() {
        return kalkis_ad;
    }

    public void setKalkis_ad(String kalkis_ad) {
        this.kalkis_ad = kalkis_ad;
    }

    public String getKalkis_kod() {
        return kalkis_kod;
    }

    public void setKalkis_kod(String kalkis_kod) {
        this.kalkis_kod = kalkis_kod;
    }

    public String getVaris_sehir() {
        return varis_sehir;
    }

    public void setVaris_sehir(String varis_sehir) {
        this.varis_sehir = varis_sehir;
    }

    public String getVaris_ad() {
        return varis_ad;
    }

    public void setVaris_ad(String varis_ad) {
        this.varis_ad = varis_ad;
    }

    public String getVaris_kod() {
        return varis_kod;
    }

    public void setVaris_kod(String varis_kod) {
        this.varis_kod = varis_kod;
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

    public String getFirma_ad() {
        return company_name;
    }

    public void setFirma_ad(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public Double getUcus_ucret() {
        return flight_fare;
    }

    public void setUcus_ucret(Double flight_fare) {
        this.flight_fare = flight_fare;
    }

    public int getHavaalani_kalkis_id() {
        return airport_departure_id;
    }

    public void setHavaalani_kalkis_id(int airport_departure_id) {
        this.airport_departure_id = airport_departure_id;
    }

    public int getHavaalani_varis_id() {
        return airport_heir_id;
    }

    public void setHavaalani_varis_id(int airport_heir_id) {
        this.airport_heir_id = airport_heir_id;
    }

    public String getUcus_tarih() {
        return flight_date;
    }

    public void setUcus_tarih(String flight_date) {
        this.flight_date = flight_date;
    }

    public int getYetiskin_sayi() {
        return yetiskin_sayi;
    }

    public void setYetiskin_sayi(int yetiskin_sayi) {
        this.yetiskin_sayi = yetiskin_sayi;
    }

    public int getCocuk_sayi() {
        return cocuk_sayi;
    }

    public void setCocuk_sayi(int cocuk_sayi) {
        this.cocuk_sayi = cocuk_sayi;
    }
    public int getSonuc() {
        return sonuc;
    }

    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }
    
    
}
