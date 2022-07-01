package model;

public class Reservation  {
    int reservation_id;
    String reservation_date;
    String pnr_no;
    String passenger_name;
    String passenger_surname;
    String passenger_email;
    String passenger_tel;
    String passenger_tc;
    int passenger_tip;
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
    String airplane_name;
    int airplane_seat;
    int conclusion;
    int seat_full;
    String passenger_date;
    Double passenger_fee;
    int situation;

    public Reservation(int reservation_id, String traveller_name, String traveller_surname, String traveller_email, String traveller_tel, String traveller_tc, String traveller_date) {
        this.reservation_id = reservation_id;
        this.passenger_name = traveller_name;
        this.passenger_surname = traveller_surname;
        this.passenger_email = traveller_email;
        this.passenger_tel = traveller_tel;
        this.passenger_tc = traveller_tc;
        this.passenger_date = traveller_date;
    }
    
    
    public Reservation(int situation, int reservation_id, String reservation_date, String pnr_no, String traveller_name, String traveller_surname, String traveller_email, String traveller_tel, String traveller_tc, int traveller_tip, String seat_no, String flight_date, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String flight_time, String company_name, String company_logo, String ucus_s, String ucus_d, String varis_saat, String airplane_name, String traveller_date, Double traveller_fee) {
        this.situation = situation;
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.passenger_name = traveller_name;
        this.passenger_surname = traveller_surname;
        this.passenger_email = traveller_email;
        this.passenger_tel = traveller_tel;
        this.passenger_tc = traveller_tc;
        this.passenger_tip = traveller_tip;
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
        this.airplane_name = airplane_name;
        this.passenger_date = traveller_date;
        this.passenger_fee = traveller_fee;
    }
    
    public Reservation(String pnr_no, String passenger_name, String passenger_surname, String passenger_email, String passenger_tel, String passenger_tc, int passenger_tip, String seat_no, int user_id, int flight_id, String passenger_date, Double passenger_fee) {
        this.pnr_no = pnr_no;
        this.passenger_name = passenger_name;
        this.passenger_surname = passenger_surname;
        this.passenger_email = passenger_email;
        this.passenger_tel = passenger_tel;
        this.passenger_tc = passenger_tc;
        this.passenger_tip = passenger_tip;
        this.seat_no = seat_no;
        this.user_id = user_id;
        this.flight_id = flight_id;
        this.passenger_date = passenger_date;
        this.passenger_fee = passenger_fee;
    }
    
    public Reservation() {
    }
    
    public Reservation(int conclusion) {
        this.conclusion = conclusion;
    }

    public Reservation(String pnr_no, String passenger_surname) {
        this.pnr_no = pnr_no;
        this.passenger_surname = passenger_surname;
    }

    
    public Reservation(String flight_date, int flight_id, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String flight_time, String company_name, String company_logo, Double flight_fare, String ucus_s, String ucus_d, String varis_saat, String airplane_name, int airplane_seat) {
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
        this.airplane_name = airplane_name;
        this.airplane_seat = airplane_seat;
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
    
    public Reservation(int reservation_id, String reservation_date, String pnr_no, String passenger_surname) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.passenger_surname = passenger_surname;
    }
    
    public Reservation(int airport_departure_id, int airport_heir_id, String flight_date, int adultNumber, int childrenNumber) {
        this.airport_departure_id = airport_departure_id;
        this.airport_heir_id = airport_heir_id;
        this.flight_date = flight_date;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
    }

    public Reservation(int reservation_id, String reservation_date, String pnr_no, String passenger_name, String passenger_surname, String passenger_email, String passenger_tel, String passenger_tc, int passenger_tip, String seat_no, int user_id, int flight_id) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.pnr_no = pnr_no;
        this.passenger_name = passenger_name;
        this.passenger_surname = passenger_surname;
        this.passenger_email = passenger_email;
        this.passenger_tel = passenger_tel;
        this.passenger_tc = passenger_tc;
        this.passenger_tip = passenger_tip;
        this.seat_no = seat_no;
        this.user_id = user_id;
        this.flight_id = flight_id;
    }

    public Reservation(String flight_date, String departure_city, String departure_name, String departure_code, String varis_city, String varis_name, String varis_code, String flight_hour, String company_name, String company_logo, String ucus_s, String ucus_d, String airplane_name) {
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
        this.airplane_name = airplane_name;
    }
    public String getTraveller_date() {
        return passenger_date;
    }
    
    public void setTraveller_date(String passenger_date) {
        this.passenger_date = passenger_date;
    }
    
    public int getDurum() {
        return situation;
    }

    public void setDurum(int situation) {
        this.situation = situation;
    }
    
    public Double getTraveller_fee() {
        return passenger_fee;
    }

    public void setTraveller_fee(Double passenger_fee) {
        this.passenger_fee = passenger_fee;
    }

    public int getKoltuk_full() {
        return seat_full;
    }

    public void setKoltuk_full(int seat_full) {
        this.seat_full = seat_full;
    }

    public Reservation(String seat_no) {
        this.seat_no = seat_no;
    }
    
    public int getUcak_seat() {
        return airplane_seat;
    }

    public void setUcak_seat(int ucak_seat) {
        this.airplane_seat = ucak_seat;
    }
    
    public String getUcak_name() {
        return airplane_name;
    }

    public void setUcak_name(String ucak_name) {
        this.airplane_name = ucak_name;
    }
    
    public String getTraveller_name() {
        return passenger_name;
    }

    public void setTraveller_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getTraveller_email() {
        return passenger_email;
    }

    public void setTraveller_email(String passenger_email) {
        this.passenger_email = passenger_email;
    }

    public String getTraveller_tel() {
        return passenger_tel;
    }

    public void setTraveller_tel(String passenger_tel) {
        this.passenger_tel = passenger_tel;
    }

    public String getTraveller_tc() {
        return passenger_tc;
    }

    public void setTraveller_tc(String passenger_tc) {
        this.passenger_tc = passenger_tc;
    }

    public int getTraveller_tip() {
        return passenger_tip;
    }

    public void setTraveller_tip(int passenger_tip) {
        this.passenger_tip = passenger_tip;
    }

    public String getKoltuk_no() {
        return seat_no;
    }

    public void setKoltuk_no(String seat_no) {
        this.seat_no = seat_no;
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

    public String getTraveller_surname() {
        return passenger_surname;
    }

    public void setTraveller_surname(String passenger_surname) {
        this.passenger_surname = passenger_surname;
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
        return departure_code;
    }

    public void setKalkis_code(String kalkis_code) {
        this.departure_code = kalkis_code;
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

    public Double getFlight_fee() {
        return flight_fare;
    }

    public void setFlight_fee(Double flight_fare) {
        this.flight_fare = flight_fare;
    }

    public int getAirport_departure_id() {
        return airport_departure_id;
    }

    public void setAirport_departure_id(int airport_departure_id) {
        this.airport_departure_id = airport_departure_id;
    }

    public int getAirport_arrival_id() {
        return airport_heir_id;
    }

    public void setAirport_arrival_id(int airport_heir_id) {
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
