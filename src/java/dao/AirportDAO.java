package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Airport;
import model.Airport_City;
import model.Airport_Country;

public class AirportDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";  
    
    
    private static final String HAVAALANI_SELECT_ALL = "SELECT airport_id, airport_name, airport_code, havaalani_ulke.airport_country_id, havaalani_ulke.airport_country_name, havaalani_city.havaalani_city_id, havaalani_city.airport_city_name  FROM havaalani INNER JOIN havaalani_ulke ON airport.airport_country_id= havaalani_ulke.airport_country_id INNER JOIN havaalani_city ON airport.havaalani_city_id= havaalani_city.havaalani_city_id;";
    private static final String HAVAALANI_INSERT ="INSERT INTO havaalani (airport_name, airport_code, havaalani_city_id, airport_country_id) VALUES (?,?,?,?);";
    private static final String HAVAALANI_SEHIR_SELECT_ALL ="select * from havaalani_city;";
    private static final String HAVAALANI_ULKE_SELECT_ALL ="select * from havaalani_ulke;";
    private static final String HAVAALANI_DELETE = "delete from havaalani where airport_id = ?;";
    private static final String HAVAALANI_SELECT_ID = "SELECT * FROM havaalani  where airport_id=?;";
    private static final String HAVAALANI_UPDATE = "update havaalani set airport_name = ?, airport_code=?, airport_country_id=?, havaalani_city_id=? where airport_id = ?;";
    
    public AirportDAO() {}
    
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
          
    public List<Airport> airportList() {

        List<Airport> airport = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int airport_city_id = rs.getInt("havaalani_city_id");
                String airport_city_name = rs.getString("airport_city_name");
                int airport_country_id = rs.getInt("airport_country_id");
                String airport_country_name = rs.getString("airport_country_name");
                String airport_name = rs.getString("airport_name");
                String airport_code = rs.getString("airport_code");
                int airport_id = rs.getInt("airport_id");
                airport.add(new Airport(airport_id, airport_country_id, airport_city_id, airport_name, airport_code, airport_country_name, airport_city_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return airport;
    }
    
    public List<Airport_City> airportCity() {

        List<Airport_City> airportCity = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SEHIR_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_city_id = rs.getInt("havaalani_city_id");
                String airport_city_name = rs.getString("airport_city_name");
                airportCity.add(new Airport_City(havaalani_city_id, airport_city_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return airportCity;
    }
    
    public List<Airport_Country> airportCountry() {

        List<Airport_Country> airportCountry = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_ULKE_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int airport_country_id = rs.getInt("airport_country_id");
                String airport_country_name = rs.getString("airport_country_name");
                airportCountry.add(new Airport_Country(airport_country_id, airport_country_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return airportCountry;
    }
    
    public void addAirport(Airport airport) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_INSERT)) {
            preparedStatement.setString(1, airport.getHavaalani_ad());
            preparedStatement.setString(2, airport.getHavaalani_code());
            preparedStatement.setInt(3, airport.getAirport_city_id());
            preparedStatement.setInt(4, airport.getAirport_country_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean deleteAirport(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(HAVAALANI_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public Airport selectAirport(int id) {
        Airport havaalani = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String airport_name = rs.getString("airport_name");
                String airport_code = rs.getString("airport_code");
                int airport_country_id = rs.getInt("airport_country_id");
                int havaalani_city_id = rs.getInt("havaalani_city_id");
                
                havaalani = new Airport(id, airport_country_id, havaalani_city_id,airport_name, airport_code);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalani;
    } 
    
    public boolean airportUpdate(Airport airport) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(HAVAALANI_UPDATE);) {
            statement.setString(1, airport.getHavaalani_ad());
            statement.setString(2, airport.getHavaalani_code());
            statement.setInt(3, airport.getAirport_country_id());
            statement.setInt(4, airport.getAirport_city_id());
            statement.setInt(5, airport.getHavaalani_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
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
