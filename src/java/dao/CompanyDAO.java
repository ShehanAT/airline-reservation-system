package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public class CompanyDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    

    private static final String FIRMA_INSERT = "INSERT INTO company (company_name, company_logo) VALUES (?, ?);";
    private static final String FIRMA_SELECT_ALL = "select * from company;";
    private static final String FIRMA_DELETE = "delete from company where company_id = ?;";
    private static final String FIRMA_SELECT_ID = "select * from company where company_id=?;";
    private static final String FIRMA_UPDATE = "update company set company_name = ?, company_logo=? where company_id = ?;";
    
    public CompanyDAO() {}
    
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
    
    public List<Company> companyList() {
        List<Company> companies = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int company_id = rs.getInt("company_id");
                String company_name = rs.getString("company_name");
                String company_logo = rs.getString("company_logo");
                companies.add(new Company(company_id, company_name, company_logo));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return companies;
    }       
    
    public void addCompany(Company company) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_INSERT)) {
            preparedStatement.setString(1, company.getCompany_name());
            preparedStatement.setString(2, company.getCompany_logo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public boolean deleteCompany(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(FIRMA_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public Company firmasec(int id) {
        Company company = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String company_name = rs.getString("company_name");
                String company_logo = rs.getString("company_logo");
                company = new Company(id, company_name, company_logo);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return company;
    }  
    
    public boolean companyUpdate(Company company) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(FIRMA_UPDATE);) {
            statement.setString(1, company.getCompany_name());
            statement.setString(2, company.getCompany_logo());
            statement.setInt(3, company.getFirma_id());
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
