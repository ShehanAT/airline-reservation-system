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
    
    private static final String TEKYON_SORGULAMA_SELECT1="select distinct flight_id,(airplane.ucak_seat-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_seat, a.airport_city_name as departure_city, b.airport_city_name as arrival_city ,s.airport_name as departure_name,s.airport_code as departure_code, p.airport_name as arrival_name, p.airport_code as arrival_code, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_name, flight_fare from flight JOIN havaalani JOIN airport_city\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  airport_city a ON (a.airport_city_id = s.airport_city_id)\n" +
                                    "INNER JOIN  airport_city b ON (b.airport_city_id = p.airport_city_id)\n" +
                                    "WHERE s.airport_id = ? AND p.airport_id =? AND flight_date=? AND (ucak.ucak_seat-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) >= ?;";
    private static final String TEKYON_SORGULAMA_SELECT2="select distinct flight_id,(ucak.ucak_seat-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_seat, a.airport_city_name as departure_city, b.airport_city_name as arrival_city ,s.airport_name as departure_name,s.airport_code as departure_code, p.airport_name as arrival_name, p.airport_code as arrival_code, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_name, flight_fare from flight JOIN havaalani JOIN airport_city\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  airport_city a ON (a.airport_city_id = s.airport_city_id)\n" +
                                    "INNER JOIN  havaalani_city b ON (b.havaalani_city_id = p.havaalani_city_id)\n" +
                                    "WHERE s.airport_id = ? AND p.airport_id =? AND flight_date=? AND flight_hour > ? AND (ucak.ucak_seat-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) >= ?;";
    private static final String REZERVASYON_SELECT_COUNT="SELECT COUNT(*) as conclusion FROM reservation WHERE reservation_date BETWEEN ? AND ?;";
    private static final String UCUS_SELECT_COUNT="SELECT count(*) as conclusion FROM flight WHERE flight_date >= ? ;";
    private static final String MESAJ_SELECT_COUNT="SELECT count(*) as conclusion FROM message WHERE message_notRead = 0;";
    private static final String REZERVASYON_DELETE = "delete from reservation where reservation_id = ?;";
    private static final String REZERVASYON_SELECT_PNRNO="SELECT * FROM reservation where pnr_no=? and traveller_surname=?;";
    private static final String REZERVASYON_SELECT_UCUS_ID="select DISTINCT k.ucak_name, u.flight_hour, u.flight_date, u.flight_time, traveller_name, traveller_surname, traveller_email, traveller_tc, traveller_tip, a.airport_city_name AS departure_city, s.airport_name as departure_name, s.airport_code as departure_code, b.airport_city_name as arrival_city, p.airport_name as arrival_name, p.airport_code as arrival_code, f.company_name, f.company_logo from reservation JOIN havaalani JOIN havaalani_city JOIN flight JOIN company JOIN ucak\n" +
                                    "INNER JOIN  flight u ON (reservation.flight_id = flight.flight_id)\n" +
                                    "INNER JOIN  company f ON (f.company_id = u.company_id)\n" +
                                    "INNER JOIN  ucak k ON (k.plane_id = u.plane_id)\n" +
                                    "INNER JOIN  havaalani s ON (u.flight_departure_id = s.airport_id)\n" +
                                    "INNER JOIN  havaalani p ON (u.end_heir_id = p.airport_id)\n" +
                                    "INNER JOIN  havaalani_city a ON (s.havaalani_city_id = a.havaalani_city_id )\n" +
                                    "INNER JOIN  havaalani_city b ON (p.havaalani_city_id = b.havaalani_city_id)\n" +
                                    "WHERE u.flight_id=? and reservation.reservation_id=?;";
    private static final String SELECT_UCUS_BILGILERI = "select distinct flight_id,(ucak.ucak_seat-(SELECT COUNT(flight_id) FROM reservation WHERE flight_id=flight.flight_id )) as bos_seat, a.airport_city_name as departure_city, b.airport_city_name as arrival_city ,s.airport_name as departure_name,s.airport_code as departure_code, p.airport_name as arrival_name, p.airport_code as arrival_code, flight_date, flight_hour, flight_time, company.company_name,company.company_logo , ucak.ucak_name, ucak.ucak_seat, flight_fare from flight JOIN havaalani JOIN havaalani_city\n" +
                                    "INNER JOIN  ucak ON (ucak.plane_id = flight.plane_id)\n" +
                                    "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.airport_id = flight.flight_departure_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.airport_id = flight.end_heir_id)\n" +
                                    "INNER JOIN  havaalani_city a ON (a.havaalani_city_id = s.havaalani_city_id)\n" +
                                    "INNER JOIN  havaalani_city b ON (b.havaalani_city_id = p.havaalani_city_id)\n" +
                                    "WHERE flight_id=?;";
    private static final String KOLTUK_BILGI_SELECT="SELECT seat_no FROM reservation \n" +
                                                "WHERE flight_id=?\n" +
                                                "ORDER BY seat_no ASC;";
    private static final String KOLTUK_DOLU_SELECT="SELECT COUNT(seat_no) as seat_full FROM reservation \n" +
                                                "WHERE flight_id=?;";
    private static final String REZERVASYON_INSERT ="INSERT INTO reservation (flight_id, user_id, pnr_no, traveller_name, traveller_surname, traveller_email, traveller_tel, traveller_tc, traveller_tip, traveller_date, traveller_fee, seat_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String KOLTUK_NO_SELECT="SELECT * FROM reservation WHERE flight_id=? and seat_no=?;";
    private static final String REZERVASYON_ISLEMLERIM_SELECT="SELECT r.reservation_id,r.situation ,r.reservation_date, r.pnr_no, r.traveller_name, r.traveller_surname, r.traveller_email, r.traveller_tel, r.traveller_tc, r.traveller_tip, r.traveller_date, r.traveller_fee, r.seat_no, u.flight_date, u.flight_hour, u.flight_time, k.airport_name as departure_name, k.airport_code as departure_code, v.airport_name as arrival_name, v.airport_code as arrival_code, s1.airport_city_name as departure_city, s2.airport_city_name as arrival_city, f.company_name, f.company_logo, p.ucak_name from reservation AS r\n" +
                                                "JOIN flight AS u ON u.flight_id = r.flight_id\n" +
                                                "JOIN havaalani AS k ON k.airport_id=u.flight_departure_id \n" +
                                                "JOIN havaalani AS v ON v.airport_id=u.end_heir_id\n" +
                                                "JOIN havaalani_city AS s1 ON s1.havaalani_city_id=k.havaalani_city_id\n" +
                                                "JOIN havaalani_city AS s2 ON s2.havaalani_city_id=v.havaalani_city_id\n" +
                                                "JOIN company AS f ON f.company_id=u.company_id\n" +
                                                "JOIN ucak AS p ON p.plane_id=u.plane_id\n" +
                                                "WHERE r.user_id=?\n"+
                                                "ORDER BY r.reservation_date DESC;";
    private static final String IPTAL_DURUM1="update reservation r\n" +
                                                "join flight u on r.flight_id = u.flight_id\n" +
                                                "set r.situation = '1'\n" +
                                                "WHERE (r.user_id=? and u.flight_date > ?) OR (u.flight_date = ? and u.flight_hour > ?);";
    private static final String IPTAL_DURUM0="update reservation r\n" +
                                                "join flight u on r.flight_id = u.flight_id\n" +
                                                "set r.situation = '0'\n" +
                                                "WHERE (r.user_id=? and u.flight_date < ?) OR (u.flight_date = ? and u.flight_hour < ?);";
    private static final String REZERVASYON_UPDATE = "update reservation set traveller_name = ?, traveller_surname=?, traveller_tc=?, traveller_date=?, traveller_email=?, traveller_tel=? where reservation_id = ?;";
    
    private static final String RZERVASYON_INCELE="SELECT r.reservation_id,r.situation ,r.reservation_date, r.pnr_no, r.traveller_name, r.traveller_surname, r.traveller_email, r.traveller_tel, r.traveller_tc, r.traveller_tip, r.traveller_date, r.traveller_fee, r.seat_no, u.flight_date, u.flight_hour, u.flight_time, k.airport_name as departure_name, k.airport_code as departure_code, v.airport_name as arrival_name, v.airport_code as arrival_code, s1.airport_city_name as departure_city, s2.airport_city_name as arrival_city, f.company_name, f.company_logo, p.ucak_name from reservation AS r\n" +
                                                "JOIN flight AS u ON u.flight_id = r.flight_id\n" +
                                                "JOIN havaalani AS k ON k.airport_id=u.flight_departure_id\n" +
                                                "JOIN havaalani AS v ON v.airport_id=u.end_heir_id\n" +
                                                "JOIN havaalani_city AS s1 ON s1.havaalani_city_id=k.havaalani_city_id\n" +
                                                "JOIN havaalani_city AS s2 ON s2.havaalani_city_id=v.havaalani_city_id\n" +
                                                "JOIN company AS f ON f.company_id=u.company_id\n" +
                                                "JOIN ucak AS p ON p.plane_id=u.plane_id\n" +
                                                "ORDER BY r.reservation_date DESC;";
    
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
        String date = now.format(formatter);
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
            statement.setString(2, date);
            statement.setString(3, date);
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
        String date = now.format(formatter);
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
            statement.setString(2, date);
            statement.setString(3, date);
            statement.setString(4, flight_hour);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean seatControl(int id, String seat_no) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_NO_SELECT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, seat_no);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public void addReservation(Reservation rez) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_INSERT)) {
            preparedStatement.setInt(1, rez.getFlight_id());
            preparedStatement.setInt(2, rez.getKullanici_id());
            preparedStatement.setString(3, rez.getPnr_no());
            preparedStatement.setString(4, rez.getTraveller_name());
            preparedStatement.setString(5, rez.getTraveller_surname());
            preparedStatement.setString(6, rez.getTraveller_email());
            preparedStatement.setString(7, rez.getTraveller_tel());
            preparedStatement.setString(8, rez.getTraveller_tc());
            preparedStatement.setInt(9, rez.getTraveller_tip());
            preparedStatement.setString(10, rez.getTraveller_date());
            preparedStatement.setDouble(11, rez.getTraveller_fee());
            preparedStatement.setString(12, rez.getKoltuk_no());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public List<Reservation> reservationProcess(int id) {
        List<Reservation> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(REZERVASYON_ISLEMLERIM_SELECT);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int situation = rs.getInt("situation");
                String pnr_no = rs.getString("pnr_no");
                String traveller_name = rs.getString("traveller_name");
                String traveller_surname = rs.getString("traveller_surname");
                String traveller_email = rs.getString("traveller_email");
                String traveller_tel = rs.getString("traveller_tel");
                String traveller_tc = rs.getString("traveller_tc");
                int traveller_tip = rs.getInt("traveller_tip");
                String traveller_date = rs.getString("traveller_date");
                Double traveller_fee=rs.getDouble("traveller_fee");
                String seat_no = rs.getString("seat_no");
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
                String arrival_saat = Ssaat+":"+Sdakika;
                int reservation_id = rs.getInt("reservation_id");
                String departure_city=rs.getString("departure_city");
                String departure_name=rs.getString("departure_name");
                String departure_code=rs.getString("departure_code");
                String arrival_city=rs.getString("arrival_city");
                String arrival_name=rs.getString("arrival_name");
                String arrival_code=rs.getString("arrival_code");
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                String ucak_name=rs.getString("ucak_name");
                String reservation_date=rs.getString("reservation_date");
                rez.add(new Reservation(situation, reservation_id, reservation_date, pnr_no,traveller_name, traveller_surname,traveller_email, traveller_tel, traveller_tc, traveller_tip, seat_no, flight_date, departure_city, departure_name, departure_code, arrival_city, arrival_name, arrival_code, flight_hour, flight_time, company_name, company_logo, flight_s, flight_d, arrival_saat,ucak_name, traveller_date, traveller_fee));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    }
    
    public boolean updateReservation(Reservation rez) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_UPDATE);) {
            preparedStatement.setString(1, rez.getTraveller_name());
            preparedStatement.setString(2, rez.getTraveller_surname());
            preparedStatement.setString(3, rez.getTraveller_tc());
            preparedStatement.setString(4, rez.getTraveller_date());
            preparedStatement.setString(5, rez.getTraveller_email());
            preparedStatement.setString(6, rez.getTraveller_tel());
            preparedStatement.setInt(7, rez.getReservation_id());
            guncellenenSatir = preparedStatement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public List<Reservation> oneWayQuery(Reservation reservation) {
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
        if (reservation.getFlight_date().equals(date1)) {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT2);) {
                statement.setInt(1, reservation.getAirport_departure_id());
                statement.setInt(2, reservation.getAirport_arrival_id());
                statement.setString(3, reservation.getFlight_date());
                statement.setString(4, u_saat);
                statement.setInt(5, (reservation.getCocuk_sayi() + reservation.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String departure_city = rs.getString("departure_city");
                    String departure_name = rs.getString("departure_name");
                    String departure_code = rs.getString("departure_code");
                    String arrival_city = rs.getString("arrival_city");
                    String arrival_name = rs.getString("arrival_name");
                    String arrival_code = rs.getString("arrival_code");
                    String flight_hour = rs.getString("flight_hour");
                    flight_hour = flight_hour.substring(0, 5);
                    String flight_date = rs.getString("flight_date");
                    String flight_time = rs.getString("flight_time");

                    String[] ARRAYflight_time = flight_time.split(":");
                    String flight_s = ARRAYflight_time[0];
                    String flight_d = ARRAYflight_time[1];
                    String[] ARRAYucus_saat = flight_hour.split(":");
                    String s = ARRAYucus_saat[0];
                    String d = ARRAYucus_saat[1];
                    int saat = (Integer.parseInt(s) + Integer.parseInt(flight_s)) % 24;
                    int dakika = (Integer.parseInt(d) + Integer.parseInt(flight_d)) % 60;
                    String perMinute;
                    if (dakika < 10) {
                        perMinute = "0" + String.valueOf(dakika);
                    } else {
                        perMinute = String.valueOf(dakika);
                    }
                    String Hour;
                    if (saat < 10) {
                        Hour = "0" + String.valueOf(saat);
                    } else {
                        Hour = String.valueOf(saat);
                    }
                    String arrival_saat = Hour + ":" + perMinute;
                    String company_name = rs.getString("company_name");
                    String company_logo = rs.getString("company_logo");
                    Double flight_fare = rs.getDouble("flight_fare");
                    rez.add(new Reservation(flight_date, flight_id, departure_city, departure_name, departure_code, arrival_city, arrival_name, arrival_code, flight_hour, flight_time, company_name, company_logo, flight_fare, flight_s, flight_d, arrival_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        } else {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT1);) {
                statement.setInt(1, reservation.getAirport_arrival_id());
                statement.setInt(2, reservation.getAirport_departure_id());
                statement.setString(3, reservation.getFlight_date());
                statement.setInt(4, (reservation.getCocuk_sayi() + reservation.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int flight_id = rs.getInt("flight_id");
                    String departure_city = rs.getString("departure_city");
                    String kalkis_name = rs.getString("kalkis_name");
                    String kalkis_code = rs.getString("kalkis_code");
                    String arrival_city = rs.getString("arrival_city");
                    String arrival_name = rs.getString("arrival_name");
                    String arrival_code = rs.getString("arrival_code");
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
                    String arrival_saat = Ssaat + ":" + Sdakika;
                    String company_name = rs.getString("company_name");
                    String company_logo = rs.getString("company_logo");
                    Double flight_fare = rs.getDouble("flight_fare");
                    rez.add(new Reservation(flight_date, flight_id, departure_city, kalkis_name, kalkis_code, arrival_city, arrival_name, arrival_code, ucus_saat, flight_time, company_name, company_logo, flight_fare, ucus_s, ucus_d, arrival_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        }
    }
    
    public Reservation flightInformation(int id) {
        Reservation rez=null;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_UCUS_BILGILERI);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int flight_id = rs.getInt("flight_id");
                String departure_city=rs.getString("departure_city");
                String departure_name=rs.getString("departure_name");
                String departure_code=rs.getString("departure_code");
                String arrival_city=rs.getString("arrival_city");
                String arrival_ad=rs.getString("arrival_ad");
                String arrival_code=rs.getString("arrival_code");
                String flight_hour=rs.getString("flight_hour");
                flight_hour=flight_hour.substring(0, 5);
                String flight_date=rs.getString("flight_date");
                String flight_time=rs.getString("flight_time");
                
                String[] ARRAYflight_time = flight_time.split(":");
                String ucus_s = ARRAYflight_time[0];
                String ucus_d = ARRAYflight_time[1];
                String[] ARRAYucus_saat = flight_hour.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int hour=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(hour < 10){
                    Ssaat="0"+String.valueOf(hour);
                }else{                                    
                    Ssaat=String.valueOf(hour);
                }
                String arrival_hour = Ssaat+":"+Sdakika;
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                Double flight_fare=rs.getDouble("flight_fare");
                String airplane_name=rs.getString("airplane_name");
                int airplane_seat = rs.getInt("airplane_seat");
                rez = new Reservation(flight_date,flight_id, departure_city,departure_name,departure_code,arrival_city,arrival_ad,arrival_code,flight_hour,flight_time,company_name,company_logo,flight_fare, ucus_s, ucus_d, arrival_hour,airplane_name,airplane_seat);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    } 
    
    public Reservation selectReservation(String pnr_no, String passenger_surname) {
        Reservation reservation = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_PNRNO);) {
            preparedStatement.setString(1, pnr_no);
            preparedStatement.setString(2, passenger_surname);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservation_id = rs.getInt("reservation_id");
                String reservation_date = rs.getString("reservation_date");
                int flight_id = rs.getInt("flight_id");
                int user_id = rs.getInt("user_id");
                String passenger_ad = rs.getString("passenger_ad");
                String passenger_email = rs.getString("passenger_email");
                String passenger_tel = rs.getString("passenger_tel");
                String passenger_tc = rs.getString("passenger_tc");
                int passenger_tip = rs.getInt("passenger_tip");
                String seat_no = rs.getString("seat_no");
                reservation = new Reservation(reservation_id, reservation_date,pnr_no,passenger_ad, passenger_surname,passenger_email,passenger_tel,passenger_tc,passenger_tip,seat_no,user_id, flight_id  );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservation;
    }
    
    public Reservation reservationInformation(int flight_id, int reservation_id) {
        Reservation reservation = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_UCUS_ID);) {
            preparedStatement.setInt(1, flight_id);
            preparedStatement.setInt(2, reservation_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ucus_saat = rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String flight_time = rs.getString("flight_time");
                String[] ARRAYflight_time = flight_time.split(":");
                String ucus_s = ARRAYflight_time[0];
                String ucus_d = ARRAYflight_time[1];
                String flight_date = rs.getString("ucus_date");
                String ucak_ad = rs.getString("ucak_ad");
                String company_name = rs.getString("company_name");
                String company_logo = rs.getString("company_logo");
                String kalkis_city = rs.getString("kalkis_city");
                String kalkis_ad = rs.getString("kalkis_ad");
                String kalkis_code = rs.getString("kalkis_code");
                String varis_city = rs.getString("varis_city");
                String varis_ad = rs.getString("varis_ad");
                String varis_code = rs.getString("varis_code");
                
                reservation = new Reservation(flight_date, kalkis_city, kalkis_ad, kalkis_code, varis_city, varis_ad, varis_code, ucus_saat, company_name, company_logo, ucus_s, ucus_d, ucak_ad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservation;
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
        List<Reservation> reservation = new ArrayList<> ();
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
                reservation.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservation;
    }
    
    public List<Reservation> numberOfFlights() {
        List<Reservation> reservation = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String date1 = now.format(formatter);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UCUS_SELECT_COUNT);) {
            statement.setString(1, date1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int conclusion = rs.getInt("conclusion");
                reservation.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservation;
    }
    
    public List<Reservation>numberOfMessages() {
        List<Reservation> reservation = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(MESAJ_SELECT_COUNT);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int conclusion = rs.getInt("conclusion");
                reservation.add(new Reservation(conclusion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservation;
    }
    
    public List<Reservation> reservationList() {
        List<Reservation> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RZERVASYON_INCELE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int situation = rs.getInt("situation");
                String pnr_no = rs.getString("pnr_no");
                String passenger_ad = rs.getString("passenger_ad");
                String passenger_surname = rs.getString("passenger_surname");
                String passenger_email = rs.getString("passenger_email");
                String passenger_tel = rs.getString("passenger_tel");
                String passenger_tc = rs.getString("passenger_tc");
                int passenger_tip = rs.getInt("passenger_tip");
                String passenger_date = rs.getString("passenger_date");
                Double passenger_fee=rs.getDouble("passenger_fee");
                String seat_no = rs.getString("seat_no");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_date=rs.getString("ucus_date");
                String ucus_sure=rs.getString("ucus_sure");
                String[] ARRAYflight_time = ucus_sure.split(":");
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
                int reservation_id = rs.getInt("reservation_id");
                String kalkis_city=rs.getString("kalkis_city");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_code=rs.getString("kalkis_code");
                String varis_city=rs.getString("varis_city");
                String varis_ad=rs.getString("varis_ad");
                String varis_code=rs.getString("varis_code");
                String company_name=rs.getString("company_name");
                String company_logo=rs.getString("company_logo");
                String ucak_ad=rs.getString("ucak_ad");
                String reservation_date=rs.getString("reservation_date");
                rez.add(new Reservation(situation, reservation_id, reservation_date, pnr_no,passenger_ad, passenger_surname,passenger_email, passenger_tel, passenger_tc, passenger_tip, seat_no, ucus_date, kalkis_city, kalkis_ad, kalkis_code, varis_city, varis_ad, varis_code, ucus_saat, ucus_sure, company_name, company_logo, ucus_s, ucus_d, varis_saat,ucak_ad, passenger_date, passenger_fee));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } return rez;
    }  
    
    public List<Reservation> seatinformation(int id) {
        List<Reservation> reservations = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_BILGI_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int seat_no = rs.getInt("seat_no");
                reservations.add(new Reservation(seat_no));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservations;
    }
    
    public Reservation fullseat(int id) {
        Reservation reservations = new Reservation();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_DOLU_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int seat_full = rs.getInt("seat_full");
                reservations = new Reservation(seat_full);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reservations;
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
