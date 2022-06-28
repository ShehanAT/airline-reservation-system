package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Company;
import model.Airport;
import model.Ucak;
import model.Ucus;

public class UcusDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   

    private static final String UCUS_INSERT ="INSERT INTO ucus (flight_departure_id, end_heir_id, flight_date, flight_hour, flight_time, company_id, ucak_id, flight_fare) VALUES (?,?,?,?,?,?,?,?);";
    private static final String FIRMA_SELECT_ALL = "select * from company;";
    private static final String HAVAALANI_SELECT_ALL = "select * from airport;";
    private static final String UCAK_SELECT_ALL = "select * from ucak;";
    private static final String GUNCELUCUS_SELECT_ALL="select flight_id, s.airport_name as kalkis_ad, p.airport_name as varis_ad, flight_date, flight_hour, flight_time, company.company_name, ucak.ucak_ad, flight_fare from ucus\n" +
                                "INNER JOIN  ucak ON (ucak.ucak_id = flight.ucak_id)\n" +
                                "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                "INNER JOIN  airport s ON (s.airport_id = flight.flight_departure_id)\n" +
                                "INNER JOIN  airport p ON (p.airport_id = flight.end_heir_id)\n" +
                                "WHERE flight_date >= ? ;";
    
    private static final String GECMISUCUS_SELECT_ALL="select flight_id, s.airport_name as kalkis_ad, p.airport_name as varis_ad, flight_date, flight_hour, flight_time, company.company_name, ucak.ucak_ad, flight_fare from ucus\n" +
                                "INNER JOIN  ucak ON (ucak.ucak_id = flight.ucak_id)\n" +
                                "INNER JOIN  company ON (company.company_id = flight.company_id)\n" +
                                "INNER JOIN  airport s ON (s.airport_id = flight.flight_departure_id)\n" +
                                "INNER JOIN  airport p ON (p.airport_id = flight.end_heir_id)\n" +
                                "WHERE flight_date < ? ;";
    private static final String UCUS_DELETE = "delete from ucus where flight_id = ?;";
    private static final String UCUS_SELECT_ID = "SELECT * FROM ucus  where flight_id=?;";
    private static final String UCUS_UPDATE = "update ucus set flight_departure_id = ?, end_heir_id=?, flight_date=?, flight_hour=?, flight_time=?, company_id=?, ucak_id=?, flight_fare=? where flight_id = ?;";
    private static final String UCUS_KONTROL = "select * from ucus as u \n" +
                                "join ucak as k on k.ucak_id=u.ucak_id\n" +
                                "where u.ucak_id=? and u.flight_date=? and ((u.flight_hour BETWEEN ? AND ?) or (ADDTIME(u.flight_hour, u.flight_time) BETWEEN ? AND ?));";
    
    public UcusDAO() {}
    
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
    
    public boolean ucussil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCUS_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean ucusguncelle(Ucus ucus) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCUS_UPDATE);) {
            statement.setInt(1, flight.getUcus_kalkis_id());
            statement.setInt(2, flight.getUcus_varis_id());
            statement.setString(3, flight.getUcus_tarih());
            statement.setString(4, flight.getUcus_saat());
            statement.setString(5, flight.getUcus_sure());
            statement.setInt(6, flight.getFirma_id());
            statement.setInt(7, flight.getUcak_id());
            statement.setDouble(8, flight.getUcus_ucret());
            statement.setInt(9, flight.getUcus_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public Ucus ucussec(int id) {
        Ucus ucus = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                int flight_departure_id = rs.getInt("flight_departure_id");
                int end_heir_id = rs.getInt("end_heir_id");
                String flight_date = rs.getString("flight_date");
                String flight_hour = rs.getString("flight_hour");
                String flight_time = rs.getString("flight_time");
                int company_id = rs.getInt("company_id");
                int ucak_id = rs.getInt("ucak_id");
                Double flight_fare = rs.getDouble("flight_fare");
                ucus = new Ucus(id,flight_departure_id,end_heir_id,flight_date,flight_hour,flight_time,company_id,ucak_id,flight_fare);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucus;
    }
    public boolean ucuskontrol(Ucus ucus)throws SQLException { 
        String flight_hour = flight.getUcus_saat();
        flight_hour = flight_hour.substring(0, 5);
        String flight_time = flight.getUcus_sure();
        String[] ARRAYflight_time = flight_time.split(":");
        String ucus_s = ARRAYflight_time[0];
        String ucus_d = ARRAYflight_time[1];
        String[] ARRAYflight_hour = flight_hour.split(":");
        String s = ARRAYflight_hour[0];
        String d = ARRAYflight_hour[1];
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
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_KONTROL);) {
            preparedStatement.setInt(1, flight.getUcak_id());
            preparedStatement.setString(2, flight.getUcus_tarih());
            preparedStatement.setString(3, flight_hour);
            preparedStatement.setString(4, varis_saat);
            preparedStatement.setString(5, flight_hour);
            preparedStatement.setString(6, varis_saat);
            ResultSet rs = preparedStatement.executeQuery();            
            if (rs.next()) {
                return false;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return true;     
    }
    
    public void ucusolustur(Ucus ucus) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_INSERT)) {
            preparedStatement.setInt(1, flight.getUcus_kalkis_id());
            preparedStatement.setInt(2, flight.getUcus_varis_id());
            preparedStatement.setString(3, flight.getUcus_tarih());
            preparedStatement.setString(4, flight.getUcus_saat());
            preparedStatement.setString(5, flight.getUcus_sure());
            preparedStatement.setInt(6, flight.getFirma_id());
            preparedStatement.setInt(7, flight.getUcak_id());
            preparedStatement.setDouble(8, flight.getUcus_ucret());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public List<Ucus> guncelucusliste() {
        List<Ucus> ucuslar = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String str = now.format(formatter);

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GUNCELUCUS_SELECT_ALL);) {
            preparedStatement.setString(1, str);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int flight_id = rs.getInt("flight_id");
                String ucus_kalkis = rs.getString("kalkis_ad");
                String ucus_varis = rs.getString("varis_ad");
                String flight_date = rs.getString("flight_date");
                String flight_hour = rs.getString("flight_hour");
                String flight_time = rs.getString("flight_time");
                String company_name = rs.getString("company_name");
                String ucak_ad = rs.getString("ucak_ad");
                Double flight_fare = rs.getDouble("flight_fare");
                ucuslar.add(new Ucus(flight_id, flight_date,flight_hour, flight_time, flight_fare,company_name,ucak_ad,ucus_kalkis,ucus_varis));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucuslar;
    }  
    
    public List<Ucus> gecmisucusliste() {
        List<Ucus> ucuslar = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String str = now.format(formatter);

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GECMISUCUS_SELECT_ALL);) {
            preparedStatement.setString(1, str);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int flight_id = rs.getInt("flight_id");
                String ucus_kalkis = rs.getString("kalkis_ad");
                String ucus_varis = rs.getString("varis_ad");
                String flight_date = rs.getString("flight_date");
                String flight_hour = rs.getString("flight_hour");
                ucus_saat=ucus_saat.substring(0, 5);
                String flight_time = rs.getString("flight_time");
                String company_name = rs.getString("company_name");
                String ucak_ad = rs.getString("ucak_ad");
                Double flight_fare = rs.getDouble("flight_fare");
                ucuslar.add(new Ucus(flight_id, flight_date,ucus_saat, flight_time, flight_fare,company_name,ucak_ad,ucus_kalkis,ucus_varis));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucuslar;
    } 
    
    public List<Company> company() {
        List<Company> company = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int company_id = rs.getInt("company_id");
                String company_name = rs.getString("company_name");
                company.add(new Company(company_id, company_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return company;
    }
    
    public List<Ucak> ucak() {
        List<Ucak> ucak = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ucak_id = rs.getInt("ucak_id");
                String ucak_ad = rs.getString("ucak_ad");               
                ucak.add(new Ucak(ucak_id, ucak_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucak;
    }
    
    public List<Airport> havaalani() {
        List<Airport> havaalani = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int airport_id = rs.getInt("airport_id");
                String airport_name = rs.getString("airport_name");
                String airport_code = rs.getString("airport_code");
                airport.add(new Airport(airport_id, airport_name, airport_code));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalani;
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
