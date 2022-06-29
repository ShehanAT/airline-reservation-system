package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ucak;
import model.Company;

public class UcakDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   

    private static final String UCAK_SELECT_ALL = "SELECT plane_id, ucak_ad, ucak_koltuk, company.company_name FROM ucak INNER JOIN company ON ucak.company_id=company.company_id;";
    private static final String FIRMA_SELECT_ALL ="select * from company;";
    private static final String UCAK_INSERT ="INSERT INTO ucak (ucak_ad, ucak_koltuk, company_id) VALUES (?,?,?);";
    private static final String UCAK_DELETE = "delete from ucak where plane_id = ?;";
    private static final String UCAK_UPDATE = "update ucak set ucak_ad = ?, ucak_koltuk=?, company_id=? where plane_id = ?;";
    private static final String UCAK_SELECT_ID = "SELECT * FROM ucak  where plane_id=?;";
    
    public UcakDAO() {}
    
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
    
    public List<Ucak> ucaklistele() {
        List<Ucak> ucaklar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int plane_id = rs.getInt("plane_id");
                String ucak_ad = rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                String company_name = rs.getString("company_name");
                ucaklar.add(new Ucak(plane_id, ucak_ad, ucak_koltuk, company_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucaklar;
    }  
    
    public boolean ucaksil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UCAK_DELETE);) {
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
    
    public void addFlight(Ucak ucak) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_INSERT)) {
            preparedStatement.setString(1, ucak.getUcak_ad());
            preparedStatement.setInt(2, ucak.getUcak_koltuk());
            preparedStatement.setInt(3, ucak.getFirma_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean ucakguncelle(Ucak ucak) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCAK_UPDATE);) {
            statement.setString(1, ucak.getUcak_ad());
            statement.setInt(2, ucak.getUcak_koltuk());
            statement.setInt(3, ucak.getFirma_id());       
            statement.setInt(4, ucak.getUcak_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }  
    
    public Ucak ucaksec(int id) {
        Ucak ucak = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ucak_ad = rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                int company_id = rs.getInt("company_id");
                ucak = new Ucak(id, ucak_ad, ucak_koltuk, company_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucak;
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
