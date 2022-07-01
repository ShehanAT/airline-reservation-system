package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Airplane;
import model.Company;

public class AirplaneDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";   

    private static final String AIRPLANE_SELECT_ALL = "SELECT plane_id, airplane_name, airplane_seat, company.company_name FROM airplane INNER JOIN company ON airplane.company_id=company.company_id;";
    private static final String FIRMA_SELECT_ALL ="select * from company;";
    private static final String AIRPLANE_INSERT ="INSERT INTO airplane (airplane_name, airplane_seat, company_id) VALUES (?,?,?);";
    private static final String AIRPLANE_DELETE = "delete from airplane where plane_id = ?;";
    private static final String AIRPLANE_UPDATE = "update airplane set airplane_name = ?, airplane_seat=?, company_id=? where plane_id = ?;";
    private static final String AIRPLANE_SELECT_ID = "SELECT * FROM airplane  where plane_id=?;";
    
    public AirplaneDAO() {}
    
    protected Connection getConnection() {
        Connection connection = null;
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<Airplane> airplaneList() {
        List<Airplane> airplanes = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(AIRPLANE_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int plane_id = rs.getInt("plane_id");
                String airplane_name = rs.getString("airplane_name");
                int airplane_seat = rs.getInt("airplane_seat");
                String company_name = rs.getString("company_name");
                airplanes.add(new Airplane(plane_id, airplane_name, airplane_seat, company_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return airplanes;
    }  
    
    public boolean deleteAirplane(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(AIRPLANE_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
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
    
    public void addFlight(Airplane airplane) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(AIRPLANE_INSERT)) {
            preparedStatement.setString(1, airplane.getUcak_name());
            preparedStatement.setInt(2, airplane.getAirplane_seat());
            preparedStatement.setInt(3, airplane.getCompany_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean updateAirplane(Airplane airplane) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(AIRPLANE_UPDATE);) {
            statement.setString(1, airplane.getUcak_name());
            statement.setInt(2, airplane.getAirplane_seat());
            statement.setInt(3, airplane.getCompany_id());
            statement.setInt(4, airplane.getUcak_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }  
    
    public Airplane selectAirplane(int id) {
        Airplane airplane = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(AIRPLANE_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String airplane_name = rs.getString("airplane_name");
                int airplane_seat = rs.getInt("airplane_seat");
                int company_id = rs.getInt("company_id");
                airplane = new Airplane(id, airplane_name, airplane_seat, company_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return airplane;
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
