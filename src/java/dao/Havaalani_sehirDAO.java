package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Airport_city;

public class Airport_cityDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String SEHİR_SELECT_ID = "select * from airport_city where airport_city_id=?;";
    private static final String SEHİR_SELECT_ALL = "select * from airport_city;";
    private static final String SEHİR_INSERT = "INSERT INTO airport_city (airport_city_name) VALUES " +
        " (?);"; 
    private static final String SEHİR_DELETE = "delete from airport_city where havaalani_city_id = ?;";
    private static final String SEHİR_UPDATE = "update airport_city set airport_city_name = ? where havaalani_city_id = ?;";
    
    public Airport_cityDAO() {}
    
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
        
    public List<Airport_city> citylistele() {
        List<Airport_city> cityler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int airport_city_id = rs.getInt("airport_city_id");
                String airport_city_name = rs.getString("airport_city_name");
                cityler.add(new Airport_city(airport_city_id, airport_city_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cityler;
    }
    
    public void cityekle(Airport_city city) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_INSERT)) {
            preparedStatement.setString(1, city.getHavaalani_city_name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean deleteCity(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEHİR_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean cityguncelle(Airport_city city) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(SEHİR_UPDATE);) {
            statement.setString(1, city.getHavaalani_city_name());
            statement.setInt(2, city.getAirport_city_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public Airport_city chooseCity(int id) {
        Airport_city city = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String airport_city_name = rs.getString("airport_city_name");
                city = new Airport_city(id, airport_city_name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return city;
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
