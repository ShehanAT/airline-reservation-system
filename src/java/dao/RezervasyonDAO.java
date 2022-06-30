package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Reservation;

public class ReservationDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String TEKYON_SORGULAMA_SELECT1="select distinct flight_id,(ucak.ucak_koltuk-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_koltuk, a.airport_city_name as kalkis_sehir, b.airport_city_name as varis_sehir ,s.airport_name as kalkis_ad,s.airport_code as kalkis_kod, p.airport_name as varis_ad, p.airport_code as varis_kod, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_ad, flight_fare from flight JOIN havaalani JOIN airport_city\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  airport_city a ON (a.airport_city_id = s.airport_city_id)\n" +
                                    "INNER JOIN  airport_city b ON (b.airport_city_id = p.airport_city_id)\n" +
                                    "WHERE s.airport_id = ? AND p.airport_id =? AND flight_date=? AND (ucak.ucak_koltuk-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) >= ?;";
    private static final String TEKYON_SORGULAMA_SELECT2="select distinct flight_id,(ucak.ucak_koltuk-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_koltuk, a.airport_city_name as kalkis_sehir, b.airport_city_name as varis_sehir ,s.airport_name as kalkis_ad,s.airport_code as kalkis_kod, p.airport_name as varis_ad, p.airport_code as varis_kod, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_ad, flight_fare from flight JOIN havaalani JOIN airport_city\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  airport_city a ON (a.airport_city_id = s.airport_city_id)\n" +
                                    "INNER JOIN  havaalani_sehir b ON (b.havaalani_sehir_id = p.havaalani_sehir_id)\n" +
                                    "WHERE s.airport_id = ? AND p.airport_id =? AND flight_date=? AND flight_hour > ? AND (ucak.ucak_koltuk-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) >= ?;";
    private static final String REZERVASYON_SELECT_COUNT="SELECT COUNT(*) as conclusion FROM reservation WHERE rezervasyon_tarih BETWEEN ? AND ?;";
    private static final String UCUS_SELECT_COUNT="SELECT count(*) as conclusion FROM flight WHERE flight_date >= ? ;";
    private static final String MESAJ_SELECT_COUNT="SELECT count(*) as conclusion FROM mesaj WHERE mesaj_okunma = 0;";
    private static final String REZERVASYON_DELETE = "delete from reservation where rezervasyon_id = ?;";
    private static final String REZERVASYON_SELECT_PNRNO="SELECT * FROM reservation where pnr_no=? and yolcu_soyad=?;";
    private static final String REZERVASYON_SELECT_UCUS_ID="select DISTINCT k.ucak_ad, u.flight_hour, u.flight_date, u.flight_time, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tc, yolcu_tip, a.airport_city_name AS kalkis_sehir, s.airport_name as kalkis_ad, s.airport_code as kalkis_kod, b.airport_city_name as varis_sehir, p.airport_name as varis_ad, p.airport_code as varis_kod, f.company_name, f.company_logo from reservation JOIN havaalani JOIN havaalani_sehir JOIN flight JOIN company JOIN ucak\n" +
                                    "INNER JOIN  flight u ON (reservation.flight_id = flight.flight_id)\n" +
                                    "INNER JOIN  company f ON (f.company_id = u.company_id)\n" +
                                    "INNER JOIN  ucak k ON (k.plane_id = u.plane_id)\n" +
                                    "INNER JOIN  havaalani s ON (u.flight_departure_id = s.airport_id)\n" +
                                    "INNER JOIN  havaalani p ON (u.end_heir_id = p.airport_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (s.havaalani_sehir_id = a.havaalani_sehir_id )\n" +
                                    "INNER JOIN  havaalani_sehir b ON (p.havaalani_sehir_id = b.havaalani_sehir_id)\n" +
                                    "WHERE u.flight_id=? and rezervasyon.rezervasyon_id=?;";
    private static final String SELECT_UCUS_BILGILERI = "select distinct flight_id,(ucak.ucak_koltuk-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_koltuk, a.airport_city_name as kalkis_sehir, b.airport_city_name as varis_sehir ,s.airport_name as kalkis_ad,s.airport_code as kalkis_kod, p.airport_name as varis_ad, p.airport_code as varis_kod, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_ad, ucak.ucak_koltuk, flight_fare from flight JOIN havaalani JOIN havaalani_sehir\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (a.havaalani_sehir_id = s.havaalani_sehir_id)\n" +
                                    "INNER JOIN  havaalani_sehir b ON (b.havaalani_sehir_id = p.havaalani_sehir_id)\n" +
                                    "WHERE flight_id=?;";
    private static final String KOLTUK_BILGI_SELECT="SELECT koltuk_no FROM reservation \n" +
                                                "WHERE flight_id=?\n" +
                                                "ORDER BY koltuk_no ASC;";  
    private static final String KOLTUK_DOLU_SELECT="SELECT COUNT(koltuk_no) as koltuk_dolu FROM reservation \n" +
                                                "WHERE flight_id=?;";
    private static final String REZERVASYON_INSERT ="INSERT INTO reservation (flight_id, kullanici_id, pnr_no, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, yolcu_tarih, yolcu_ucret, koltuk_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String KOLTUK_NO_SELECT="SELECT * FROM reservation WHERE flight_id=? and koltuk_no=?;";
    private static final String REZERVASYON_ISLEMLERIM_SELECT="SELECT r.rezervasyon_id,r.situation ,r.rezervasyon_tarih, r.pnr_no, r.yolcu_ad, r.yolcu_soyad, r.yolcu_email, r.yolcu_tel, r.yolcu_tc, r.yolcu_tip, r.yolcu_tarih, r.yolcu_ucret, r.koltuk_no, u.flight_date, u.flight_hour, u.flight_time, k.airport_name as kalkis_ad, k.airport_code as kalkis_kod, v.airport_name as varis_ad, v.airport_code as varis_kod, s1.airport_city_name as kalkis_sehir, s2.airport_city_name as varis_sehir, f.company_name, f.company_logo, p.ucak_ad from reservation AS r\n" +
                                                "JOIN flight AS u ON u.flight_id = r.flight_id\n" +
                                                "JOIN havaalani AS k ON k.airport_id=u.flight_departure_id \n" +
                                                "JOIN havaalani AS v ON v.airport_id=u.end_heir_id\n" +
                                                "JOIN havaalani_sehir AS s1 ON s1.havaalani_sehir_id=k.havaalani_sehir_id\n" +
                                                "JOIN havaalani_sehir AS s2 ON s2.havaalani_sehir_id=v.havaalani_sehir_id\n" +
                                                "JOIN company AS f ON f.company_id=u.company_id\n" +
                                                "JOIN ucak AS p ON p.plane_id=u.plane_id\n" +
                                                "WHERE r.kullanici_id=?\n"+
                                                "ORDER BY r.rezervasyon_tarih DESC;";
    private static final String IPTAL_DURUM1="update reservation r\n" +
                                                "join flight u on r.flight_id = u.flight_id\n" +
                                                "set r.situation = '1'\n" +
                                                "WHERE (r.kullanici_id=? and u.flight_date > ?) OR (u.flight_date = ? and u.flight_hour > ?);";
    private static final String IPTAL_DURUM0="update reservation r\n" +
                                                "join flight u on r.flight_id = u.flight_id\n" +
                                                "set r.situation = '0'\n" +
                                                "WHERE (r.kullanici_id=? and u.flight_date < ?) OR (u.flight_date = ? and u.flight_hour < ?);";
    private static final String REZERVASYON_UPDATE = "update reservation set yolcu_ad = ?, yolcu_soyad=?, yolcu_tc=?, yolcu_tarih=?, yolcu_email=?, yolcu_tel=? where rezervasyon_id = ?;";
    
    private static final String RZERVASYON_INCELE="SELECT r.rezervasyon_id,r.situation ,r.rezervasyon_tarih, r.pnr_no, r.yolcu_ad, r.yolcu_soyad, r.yolcu_email, r.yolcu_tel, r.yolcu_tc, r.yolcu_tip, r.yolcu_tarih, r.yolcu_ucret, r.koltuk_no, u.flight_date, u.flight_hour, u.flight_time, k.airport_name as kalkis_ad, k.airport_code as kalkis_kod, v.airport_name as varis_ad, v.airport_code as varis_kod, s1.airport_city_name as kalkis_sehir, s2.airport_city_name as varis_sehir, f.company_name, f.company_logo, p.ucak_ad from reservation AS r\n" +
                                                "JOIN flight AS u ON u.flight_id = r.flight_id\n" +
                                                "JOIN havaalani AS k ON k.airport_id=u.flight_departure_id\n" +
                                                "JOIN havaalani AS v ON v.airport_id=u.end_heir_id\n" +
                                                "JOIN havaalani_sehir AS s1 ON s1.havaalani_sehir_id=k.havaalani_sehir_id\n" +
                                                "JOIN havaalani_sehir AS s2 ON s2.havaalani_sehir_id=v.havaalani_sehir_id\n" +
                                                "JOIN company AS f ON f.company_id=u.company_id\n" +
                                                "JOIN ucak AS p ON p.plane_id=u.plane_id\n" +
                                                "ORDER BY r.rezervasyon_tarih DESC;";
    
    public ReservationDAO() {}
    
    protected Connection getConnection() {
        Connection connection = null;
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcKullaniciname,jdbcPassword);
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public boolean cancellationStatus1(int id) throws SQLException {
        boolean guncellenenSatir;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now(); 
        String tarih = now.format(formatter);
        String saat = now.format(timeformatter);
        
        String[] ARRAYsaat = saat.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Sdakika;
        if (mm < 10) {
            Sdakika = "0" + String.valueOf(mm);
        } else {
            Sdakika = String.valueOf(mm);
        }
        String Ssaat;
        if (hh < 10) {
            Ssaat = "0" + String.valueOf(hh + 2);
        } else {
            Ssaat = String.valueOf(hh + 2);
        }
        String flight_hour = Ssaat + ":" + Sdakika;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(IPTAL_DURUM1);) {
            statement.setInt(1, id);
            statement.setString(2, tarih);
            statement.setString(3, tarih);
            statement.setString(4, flight_hour);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean cancellationStatus0(int id) throws SQLException {
        boolean guncellenenSatir;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now(); 
        String tarih = now.format(formatter);
        String saat = now.format(timeformatter);
        
        String[] ARRAYsaat = saat.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Sdakika;
        if (mm < 10) {
            Sdakika = "0" + String.valueOf(mm);
        } else {
            Sdakika = String.valueOf(mm);
        }
        String Ssaat;
        if (hh < 10) {
            Ssaat = "0" + String.valueOf(hh + 2);
        } else {
            Ssaat = String.valueOf(hh + 2);
        }
        String flight_hour = Ssaat + ":" + Sdakika;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(IPTAL_DURUM0);) {
            statement.setInt(1, id);
            statement.setString(2, tarih);
            statement.setString(3, tarih);
            statement.setString(4, flight_hour);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean seatControl(int id, String koltuk_no) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_NO_SELECT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, koltuk_no);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public void rezervasyonekle(Reservation rez) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_INSERT)) {
            preparedStatement.setInt(1, rez.getUcus_id());
            preparedStatement.setInt(2, rez.getKullanici_id());
            preparedStatement.setString(3, rez.getPnr_no());
            preparedStatement.setString(4, rez.getYolcu_ad());
            preparedStatement.setString(5, rez.getYolcu_soyad());
            preparedStatement.setString(6, rez.getYolcu_email());
            preparedStatement.setString(7, rez.getYolcu_tel());
            preparedStatement.setString(8, rez.getYolcu_tc());
            preparedStatement.setInt(9, rez.getYolcu_tip());
            preparedStatement.setString(10, rez.getYolcu_tarih());
            preparedStatement.setDouble(11, rez.getYolcu_ucret());
            preparedStatement.setString(12, rez.getKoltuk_no());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public List<Reservation> rezervasyonislem(int id) {
        List<Reservation> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(REZERVASYON_ISLEMLERIM_SELECT);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int situation = rs.getInt("situation");
                String pnr_no = rs.getString("pnr_no");
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_soyad = rs.getString("yolcu_soyad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip");
                String yolcu_tarih = rs.getString("yolcu_tarih");
                Double yolcu_ucret=rs.getDouble("yolcu_ucret"); 
                String koltuk_no = rs.getString("koltuk_no");
                String flight_hour=rs.getString("flight_hour");
                flight_hour=flight_hour.substring(0, 5);
                String flight_date=rs.getString("flight_date");
                String flight_time=rs.getString("flight_time");
                String[] ARRAYflight_time = flight_time.split(":");
                String flight_s = ARRAYflight_time[0];
                String flight_d = ARRAYflight_time[1];
                String[] ARRAYflight_hour = flight_hour.split(":");
                String s = ARRAYflight_hour[0];
                String d = ARRAYflight_hour[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(flight_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(flight_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;              
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                String ucak_ad=rs.getString("ucak_ad");
                String rezervasyon_tarih=rs.getString("rezervasyon_tarih");          
                rez.add(new Reservation(situation, rezervasyon_id, rezervasyon_tarih, pnr_no,yolcu_ad, yolcu_soyad,yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, koltuk_no, flight_date, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, flight_hour, flight_time, company_name, company_logo, flight_s, flight_d, varis_saat,ucak_ad, yolcu_tarih, yolcu_ucret));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    }
    
    public boolean rezervasyonguncelle(Reservation rez) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_UPDATE);) {
            preparedStatement.setString(1, rez.getYolcu_ad());
            preparedStatement.setString(2, rez.getYolcu_soyad());
            preparedStatement.setString(3, rez.getYolcu_tc());
            preparedStatement.setString(4, rez.getYolcu_tarih());
            preparedStatement.setString(5, rez.getYolcu_email());
            preparedStatement.setString(6, rez.getYolcu_tel());
            preparedStatement.setInt(7, rez.getReservation_id());
            guncellenenSatir = preparedStatement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public List<Reservation> tekyonsorgulama(Reservation reservation) {
        List<Reservation> rez = new ArrayList<>();
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String sss = now.format(timeformatter);

        String[] ARRAYsaat = sss.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Ssdakika;
        if (mm < 10) {
            Ssdakika = "0" + String.valueOf(mm);
        } else {
            Ssdakika = String.valueOf(mm);
        }
        String Sssaat;
        if (hh < 10) {
            Sssaat = "0" + String.valueOf(hh + 1);
        } else {
            Sssaat = String.valueOf(hh + 1);
        }
        String u_saat = Sssaat + ":" + Ssdakika;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        String date1 = date.format(formatter);
        if (reservation.getUcus_tarih().equals(date1)) {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT2);) {
                statement.setInt(1, reservation.getHavaalani_kalkis_id());
                statement.setInt(2, reservation.getHavaalani_varis_id());
                statement.setString(3, reservation.getUcus_tarih());
                statement.setString(4, u_saat);
                statement.setInt(5, (reservation.getCocuk_sayi() + reservation.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String kalkis_sehir = rs.getString("kalkis_sehir");
                    String kalkis_ad = rs.getString("kalkis_ad");
                    String kalkis_kod = rs.getString("kalkis_kod");
                    String varis_sehir = rs.getString("varis_sehir");
                    String varis_ad = rs.getString("varis_ad");
                    String varis_kod = rs.getString("varis_kod");
                    String flight_hour = rs.getString("flight_hour");
                    flight_hour = flight_saat.substring(0, 5);
                    String flight_date = rs.getString("flight_date");
                    String flight_time = rs.getString("flight_time");

                    String[] ARRAYflight_time = flight_time.split(":");
                    String flight_s = ARRAYflight_time[0];
                    String flight_d = ARRAYflight_time[1];
                    String[] ARRAYucus_saat = ucus_saat.split(":");
                    String s = ARRAYucus_saat[0];
                    String d = ARRAYucus_saat[1];
                    int saat = (Integer.parseInt(s) + Integer.parseInt(ucus_s)) % 24;
                    int dakika = (Integer.parseInt(d) + Integer.parseInt(ucus_d)) % 60;
                    String Sdakika;
                    if (dakika < 10) {
                        Sdakika = "0" + String.valueOf(dakika);
                    } else {
                        Sdakika = String.valueOf(dakika);
                    }
                    String Ssaat;
                    if (saat < 10) {
                        Ssaat = "0" + String.valueOf(saat);
                    } else {
                        Ssaat = String.valueOf(saat);
                    }
                    String varis_saat = Ssaat + ":" + Sdakika;
                    String company_name = rs.getString("company_name");
                    String company_logo = rs.getString("company_logo");
                    Double flight_fare = rs.getDouble("flight_fare");
                    rez.add(new Reservation(flight_date, flight_id, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, flight_time, company_name, company_logo, flight_fare, ucus_s, ucus_d, varis_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        } else {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT1);) {
                statement.setInt(1, reservation.getHavaalani_kalkis_id());
                statement.setInt(2, reservation.getHavaalani_varis_id());
                statement.setString(3, reservation.getUcus_tarih());
                statement.setInt(4, (reservation.getCocuk_sayi() + rezervasyon.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String kalkis_sehir = rs.getString("kalkis_sehir");
                    String kalkis_ad = rs.getString("kalkis_ad");
                    String kalkis_kod = rs.getString("kalkis_kod");
                    String varis_sehir = rs.getString("varis_sehir");
                    String varis_ad = rs.getString("varis_ad");
                    String varis_kod = rs.getString("varis_kod");
                    String ucus_saat = rs.getString("ucus_saat");
                    ucus_saat = ucus_saat.substring(0, 5);
                    String flight_date = rs.getString("flight_date");
                    String flight_time = rs.getString("flight_time");

                    String[] ARRAYflight_time = flight_time.split(":");
                    String ucus_s = ARRAYflight_time[0];
                    String ucus_d = ARRAYflight_time[1];
                    String[] ARRAYucus_saat = ucus_saat.split(":");
                    String s = ARRAYucus_saat[0];
                    String d = ARRAYucus_saat[1];
                    int saat = (Integer.parseInt(s) + Integer.parseInt(ucus_s)) % 24;
                    int dakika = (Integer.parseInt(d) + Integer.parseInt(ucus_d)) % 60;
                    String Sdakika;
                    if (dakika < 10) {
                        Sdakika = "0" + String.valueOf(dakika);
                    } else {
                        Sdakika = String.valueOf(dakika);
                    }
                    String Ssaat;
                    if (saat < 10) {
                        Ssaat = "0" + String.valueOf(saat);
                    } else {
                        Ssaat = String.valueOf(saat);
                    }
                    String varis_saat = Ssaat + ":" + Sdakika;
                    String company_name = rs.getString("company_name");
                    String company_logo = rs.getString("company_logo");
                    Double flight_fare = rs.getDouble("flight_fare");
                    rez.add(new Reservation(flight_date, flight_id, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, flight_time, company_name, company_logo, flight_fare, ucus_s, ucus_d, varis_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        }
    }
    
    public Reservation ucusbilgileri(int id) {
        Reservation rez=null;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_UCUS_BILGILERI);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int flight_id = rs.getInt("flight_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String flight_date=rs.getString("flight_date");
                String flight_time=rs.getString("flight_time");
                
                String[] ARRAYflight_time = flight_time.split(":");
                String ucus_s = ARRAYflight_time[0];
                String ucus_d = ARRAYflight_time[1];
                String[] ARRAYucus_saat = ucus_saat.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                Double flight_fare=rs.getDouble("flight_fare");
                String ucak_ad=rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                rez = new Reservation(flight_date,flight_id, kalkis_sehir,kalkis_ad,kalkis_kod,varis_sehir,varis_ad,varis_kod,ucus_saat,flight_time,company_name,company_logo,flight_fare, ucus_s, ucus_d, varis_saat,ucak_ad,ucak_koltuk);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    } 
    
    public Reservation rezervasyonsec(String pnr_no, String yolcu_soyad) {
        Reservation rezervasyon = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_PNRNO);) {
            preparedStatement.setString(1, pnr_no);
            preparedStatement.setString(2, yolcu_soyad);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String rezervasyon_tarih = rs.getString("rezervasyon_tarih");
                int flight_id = rs.getInt("flight_id");
                int kullanici_id = rs.getInt("kullanici_id"); 
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip"); 
                String koltuk_no = rs.getString("koltuk_no");
                rezervasyon = new Reservation(rezervasyon_id, rezervasyon_tarih,pnr_no,yolcu_ad, yolcu_soyad,yolcu_email,yolcu_tel,yolcu_tc,yolcu_tip,koltuk_no,kullanici_id, flight_id  );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public Reservation rezervasyonbilgi(int flight_id, int rezervasyon_id) {
        Reservation rezervasyon = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_UCUS_ID);) {
            preparedStatement.setInt(1, flight_id);
            preparedStatement.setInt(2, rezervasyon_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ucus_saat = rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String flight_time = rs.getString("flight_time");
                String[] ARRAYflight_time = ucus_sure.split(":");
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String flight_date = rs.getString("ucus_tarih");
                String ucak_ad = rs.getString("ucak_ad");
                String company_name = rs.getString("company_name");
                String company_logo = rs.getString("company_logo");
                String kalkis_sehir = rs.getString("kalkis_sehir");
                String kalkis_ad = rs.getString("kalkis_ad");
                String kalkis_kod = rs.getString("kalkis_kod");
                String varis_sehir = rs.getString("varis_sehir");
                String varis_ad = rs.getString("varis_ad");
                String varis_kod = rs.getString("varis_kod");
                
                rezervasyon = new Reservation(ucus_tarih, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, company_name, company_logo, ucus_s, ucus_d, ucak_ad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public boolean cancelReservation(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(REZERVASYON_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public List<Reservation> numberOfReservations() {
        List<Reservation> rezervasyon = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDate months = LocalDate.now().minusMonths(1);
        String date1 = months.format(formatter);
        LocalDateTime now = LocalDateTime.now().plusDays(1); 
        String date2 = now.format(formatter);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(REZERVASYON_SELECT_COUNT);) {
            statement.setString(1, date1);
            statement.setString(2, date2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int conclusion = rs.getInt("conclusion");
                rezervasyon.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Reservation> numberOfFlights() {
        List<Reservation> rezervasyon = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String date1 = now.format(formatter);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UCUS_SELECT_COUNT);) {
            statement.setString(1, date1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int conclusion = rs.getInt("conclusion");
                rezervasyon.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Reservation>numberOfMessages() {
        List<Reservation> rezervasyon = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(MESAJ_SELECT_COUNT);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int conclusion = rs.getInt("conclusion");
                rezervasyon.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Reservation> rezervasyonlistele() {
        List<Reservation> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RZERVASYON_INCELE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int situation = rs.getInt("situation");
                String pnr_no = rs.getString("pnr_no");
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_soyad = rs.getString("yolcu_soyad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip");
                String yolcu_tarih = rs.getString("yolcu_tarih");
                Double yolcu_ucret=rs.getDouble("yolcu_ucret"); 
                String koltuk_no = rs.getString("koltuk_no");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_tarih=rs.getString("ucus_tarih");
                String ucus_sure=rs.getString("ucus_sure");
                String[] ARRAYucus_sure = ucus_sure.split(":"); 
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String[] ARRAYucus_saat = ucus_saat.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;              
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                String ucak_ad=rs.getString("ucak_ad");
                String rezervasyon_tarih=rs.getString("rezervasyon_tarih");          
                rez.add(new Reservation(situation, rezervasyon_id, rezervasyon_tarih, pnr_no,yolcu_ad, yolcu_soyad,yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, koltuk_no, ucus_tarih, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, ucus_sure, company_name, company_logo, ucus_s, ucus_d, varis_saat,ucak_ad, yolcu_tarih, yolcu_ucret));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } return rez;
    }  
    
    public List<Reservation> koltukbilgi(int id) {
        List<Reservation> rezervasyonlar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_BILGI_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int koltuk_no = rs.getInt("koltuk_no");
                rezervasyonlar.add(new Reservation(koltuk_no));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyonlar;
    }
    
    public Reservation dolukoltuk(int id) {
        Reservation rezervasyonlar = new Reservation();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_DOLU_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int koltuk_dolu = rs.getInt("koltuk_dolu");
                rezervasyonlar = new Reservation(koltuk_dolu);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyonlar;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
}
