package model;

public class Reservation  {
    int reservation_id;
    String reservation_date;
    String pnr_no;
    String traveller_name;
    String traveller_surname;
    String traveller_email;
    String traveller_tel;
    String traveller_tc;
    int traveller_tip;
    String seat_no;
    int user_id;
    int airport_departure_id;
    int airport_heir_id;
    String flight_date;
    int adultNumber;
    int childrenNumber;
    int flight_id;
    String departure_city;
    String departure_name;
    String departure_code;
    String varis_city;
    String varis_name;
    String varis_code;
    String flight_hour;
    String flight_time;
    String company_name;
    String company_logo;
    Double flight_fare;
    String ucus_s;
    String ucus_d;
    String varis_saat;
    String ucak_name;
    int ucak_seat;
    int conclusion;
    int seat_full;
    String traveller_date;
    Double traveller_ucret;
    int situation;

    public Reservation(int reservation_id, String traveller_name, String traveller_surname, String traveller_email, String traveller_tel, String traveller_tc, String traveller_date) {
        this.reservation_id = reservation_id;
        this.traveller_name = traveller_name;
        this.traveller_surname = traveller_soyad;
        this.traveller_email = traveller_email;
        this.traveller_tel = traveller_tel;
        this.traveller_tc = traveller_tc;
        this.traveller_date = traveller_date;
    }
    
    
    public Reservation(int situation, int reservation_id, String reservation_date, String pnr_no, String traveller_name, String traveller_soyad, String traveller_email, String traveller_tel, String traveller_tc, int traveller_tip, String seat_no, String flight_date, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String flight_time, String company_name, String company_logo, String ucus_s, String ucus_d, String varis_saat, String ucak_name, String traveller_date, Double traveller_ucret) {
        this.situation = situation;
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.traveller_name = traveller_name;
        this.traveller_soyad = traveller_soyad;
        this.traveller_email = traveller_email;
        this.traveller_tel = traveller_tel;
        this.traveller_tc = traveller_tc;
        this.traveller_tip = traveller_tip;
        this.seat_no = seat_no;
        this.flight_date = flight_date;
        this.departure_city = departure_city;
        this.departure_name = departure_name;
        this.departure_code = departure_code;
        this.varis_city = varis_city;
        this.varis_name = varis_name;
        this.varis_code = varis_code;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
        this.ucak_name = ucak_name;
        this.traveller_date = traveller_date;
        this.traveller_ucret = yolcu_ucret;
    }
    
    public Reservation(String pnr_no, String yolcu_name, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, int yolcu_tip, String seat_no, int user_id, int flight_id, String yolcu_date, Double yolcu_ucret) {
        this.pnr_no = pnr_no;
        this.yolcu_name = yolcu_name;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tip = yolcu_tip;
        this.seat_no = seat_no;
        this.user_id = user_id;
        this.flight_id = flight_id;
        this.yolcu_date = yolcu_date;
        this.yolcu_ucret = yolcu_ucret;
    }
    
    public Reservation() {
    }
    
    public Reservation(int conclusion) {
        this.conclusion = conclusion;
    }

    public Reservation(String pnr_no, String yolcu_soyad) {
        this.pnr_no = pnr_no;
        this.yolcu_soyad = yolcu_soyad;
    }

    
    public Reservation(String flight_date, int flight_id, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String flight_time, String company_name, String company_logo, Double flight_fare, String ucus_s, String ucus_d, String varis_saat, String ucak_name, int ucak_koltuk) {
        this.flight_date = flight_date;
        this.flight_id = flight_id;
        this.departure_city = departure_city;
        this.departure_name = departure_name;
        this.departure_code = departure_code;
        this.varis_city = varis_city;
        this.varis_name = varis_name;
        this.varis_code = varis_code;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.flight_fare = flight_fare;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
        this.ucak_name = ucak_name;
        this.ucak_koltuk = ucak_koltuk;
    }
    public Reservation(String flight_date, int flight_id, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String flight_time, String company_name, String company_logo, Double flight_fare, String ucus_s, String ucus_d, String varis_saat) {
        this.flight_date = flight_date;
        this.flight_id = flight_id;
        this.departure_city = departure_city;
        this.departure_name = departure_name;
        this.departure_code = departure_code;
        this.varis_city = varis_city;
        this.varis_name = varis_name;
        this.varis_code = varis_code;
        this.flight_hour = flight_hour;
        this.flight_time = flight_time;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.flight_fare = flight_fare;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.varis_saat = varis_saat;
    }

    public Reservation(int adultNumber, int childrenNumber) {
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
    }
    
    public Reservation(int reservation_id, String reservation_date, String pnr_no, String yolcu_soyad) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.yolcu_soyad = yolcu_soyad;
    }
    
    public Reservation(int airport_departure_id, int airport_heir_id, String flight_date, int adultNumber, int childrenNumber) {
        this.airport_departure_id = airport_departure_id;
        this.airport_heir_id = airport_heir_id;
        this.flight_date = flight_date;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
    }

    public Reservation(int reservation_id, String reservation_date, String pnr_no, String yolcu_name, String yolcu_soyad, String yolcu_email, String yolcu_tel, String yolcu_tc, int yolcu_tip, String koltuk_no, int user_id, int flight_id) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.yolcu_name = yolcu_name;
        this.yolcu_soyad = yolcu_soyad;
        this.yolcu_email = yolcu_email;
        this.yolcu_tel = yolcu_tel;
        this.yolcu_tc = yolcu_tc;
        this.yolcu_tip = yolcu_tip;
        this.koltuk_no = koltuk_no;
        this.user_id = user_id;
        this.flight_id = flight_id;
    }

    public Reservation(String flight_date, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String company_name, String company_logo, String ucus_s, String ucus_d, String ucak_name) {
        this.flight_date = flight_date;
        this.departure_city = departure_city;
        this.departure_name = departure_name;
        this.departure_code = departure_code;
        this.varis_city = varis_city;
        this.varis_name = varis_name;
        this.varis_code = varis_code;
        this.flight_hour = flight_hour;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.ucus_s = ucus_s;
        this.ucus_d = ucus_d;
        this.ucak_name = ucak_name;
    }
    public String getYolcu_date() {
        return yolcu_date;
    }
    
    public void setYolcu_date(String yolcu_date) {
        this.yolcu_date = yolcu_date;
    }
    
    public int getDurum() {
        return situation;
    }

    public void setDurum(int situation) {
        this.situation = situation;
    }
    
    public Double getYolcu_ucret() {
        return yolcu_ucret;
    }

    public void setYolcu_ucret(Double yolcu_ucret) {
        this.yolcu_ucret = yolcu_ucret;
    }

    public int getKoltuk_full() {
        return koltuk_full;
    }

    public void setKoltuk_full(int koltuk_full) {
        this.koltuk_full = koltuk_full;
    }

    public Reservation(String koltuk_no) {
        this.koltuk_no = koltuk_no;
    }
    
    public int getUcak_koltuk() {
        return ucak_koltuk;
    }

    public void setUcak_koltuk(int ucak_koltuk) {
        this.ucak_koltuk = ucak_koltuk;
    }
    
    public String getUcak_name() {
        return ucak_name;
    }

    public void setUcak_name(String ucak_name) {
        this.ucak_name = ucak_name;
    }
    
    public String getYolcu_name() {
        return yolcu_name;
    }

    public void setYolcu_name(String yolcu_name) {
        this.yolcu_name = yolcu_name;
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
        return user_id;
    }

    public void setKullanici_id(int user_id) {
        this.user_id = user_id;
    }
    
    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getRezervasyon_date() {
        return reservation_date;
    }

    public void setRezervasyon_date(String reservation_date) {
        this.reservation_date = reservation_date;
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

    public String getFlight_s() {
        return ucus_s;
    }

    public void setFlight_s(String ucus_s) {
        this.ucus_s = ucus_s;
    }

    public String getFlight_d() {
        return ucus_d;
    }

    public void setFlight_d(String ucus_d) {
        this.ucus_d = ucus_d;
    }
    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getKalkis_city() {
        return departure_city;
    }

    public void setKalkis_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getKalkis_name() {
        return departure_name;
    }

    public void setKalkis_name(String departure_name) {
        this.departure_name = departure_name;
    }

    public String getKalkis_code() {
        return kalkis_code;
    }

    public void setKalkis_code(String kalkis_code) {
        this.kalkis_code = kalkis_code;
    }

    public String getVaris_city() {
        return varis_city;
    }

    public void setVaris_city(String varis_city) {
        this.varis_city = varis_city;
    }

    public String getVaris_name() {
        return varis_name;
    }

    public void setVaris_name(String varis_name) {
        this.varis_name = varis_name;
    }

    public String getVaris_code() {
        return varis_code;
    }

    public void setVaris_code(String varis_code) {
        this.varis_code = varis_code;
    }

    public String getFlight_saat() {
        return flight_hour;
    }

    public void setFlight_saat(String flight_hour) {
        this.flight_hour = flight_hour;
    }

    public String getFlight_sure() {
        return flight_time;
    }

    public void setFlight_sure(String flight_time) {
        this.flight_time = flight_time;
    }

    public String getFirma_name() {
        return company_name;
    }

    public void setFirma_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public Double getFlight_ucret() {
        return flight_fare;
    }

    public void setFlight_ucret(Double flight_fare) {
        this.flight_fare = flight_fare;
    }

    public int getHavaalani_departure_id() {
        return airport_departure_id;
    }

    public void setHavaalani_departure_id(int airport_departure_id) {
        this.airport_departure_id = airport_departure_id;
    }

    public int getHavaalani_varis_id() {
        return airport_heir_id;
    }

    public void setHavaalani_varis_id(int airport_heir_id) {
        this.airport_heir_id = airport_heir_id;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    public int getYetiskin_sayi() {
        return adultNumber;
    }

    public void setYetiskin_sayi(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getCocuk_sayi() {
        return childrenNumber;
    }

    public void setCocuk_sayi(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }
    public int getSonuc() {
        return conclusion;
    }

    public void setSonuc(int conclusion) {
        this.conclusion = conclusion;
    }
    
    
}
