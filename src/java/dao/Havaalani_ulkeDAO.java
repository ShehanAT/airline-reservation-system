package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Airport_country;

public class Airport_countryDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String ULKE_SELECT_ID = "select * from havaalani_ulke where airport_country_id=?;";
    private static final String ULKE_SELECT_ALL = "select * from havaalani_ulke;";
    private static final String ULKE_INSERT = "INSERT INTO Airport_country" + "  (airport_country_name) VALUES " +
        " (?);"; 
    private static final String ULKE_DELETE = "delete from Airport_country where airport_country_id = ?;";
    private static final String ULKE_UPDATE = "update Airport_country set airport_country_name = ? where airport_country_id = ?;";
    
    public Airport_countryDAO() {}
    
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
    
    public List<Airport_country> listCountry() {

        List<Airport_country> ulkeler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ULKE_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int airport_country_id = rs.getInt("airport_country_id");
                String airport_country_name = rs.getString("airport_country_name");
                ulkeler.add(new Airport_country(airport_country_id, airport_country_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ulkeler;
    }
    
    public void countryAdd(Airport_country ulke) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(ULKE_INSERT)) {
            preparedStatement.setString(1, ulke.getAirport_country_ad());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public boolean deleteCountry(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(ULKE_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    } 
    
    public boolean updateCountry(Airport_country ulke) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(ULKE_UPDATE);) {
            statement.setString(1, ulke.getAirport_country_ad());
            statement.setInt(2, ulke.getAirport_country_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public Airport_country selectCountry(int id) {
        Airport_country country = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ULKE_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String airport_country_name = rs.getString("airport_country_name");
                country = new Airport_country(id, airport_country_name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return country;
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
