package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String KULLANICI_INSERT = "INSERT INTO users" +
            "  (user_name, user_surname, user_email, user_password, user_authorization) VALUES " +
        " (?, ?, ?, ?,"+1+");";
    private static final String KULLANICI_SELECT_ID = "select * from users where user_id=?;";
    private static final String KULLANICI_DELETE = "delete from users where user_id = ?;";
    private static final String KULLANICI_SELECT_EMAIL = "select * from users where user_email = ?;";
    private static final String KULLANICI_SELECT_ALL = "select * from users;";
    private static final String KULLANICI_SELECT_EMAIL_SIFRE = "select * from users where user_email = ? and user_password = ?;";
    private static final String KULLANICI_INSERT_ADMIN ="INSERT INTO users (user_name, user_surname, user_email, user_password, user_authorization) VALUES (?,?,?,?,"+2+");";
    private static final String KULLANICI_UPDATE = "update users set user_name = ?, user_surname = ?, user_email = ?, user_password = ? where user_id = ?;";
    private static final String PROFIL_UPDATE = "update users set user_name = ?, user_surname = ?, user_email = ? where user_id = ?;";
    private static final String ADMIN_SELECT_EMAIL_SIFRE = "select * from users where user_email = ? and user_password = ? and user_authorization=2;";
    private static final String SIFRE_KONTROL_SELECT = "select * from users where user_id=? and user_password=?;";
    private static final String SIFRE_UPDATE = "update users set user_password = ? where user_id = ?;";
    private static final String HESAP_DELETE = "delete from users where user_id = ?;";
    public UserDAO() {}

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

    public List<User> uyelistele() {
        List<User> uyeler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_surname = rs.getString("user_surname");
                String user_email = rs.getString("user_email");
                int user_authorization = rs.getInt("user_authorization");
                uyeler.add(new User(user_id, user_name, user_surname, user_email, user_authorization));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return uyeler;
    }
    
    public boolean sifrekontrol(int id, String user_password) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SIFRE_KONTROL_SELECT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, user_password);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public boolean deleteUser(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(KULLANICI_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean deleteAccount(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(HESAP_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public User usersec(int id) {
        User user = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("user_name");
                String user_surname = rs.getString("user_surname");
                String user_email = rs.getString("user_email");
                String user_password = rs.getString("user_password");
                user = new User(id, user_name,user_surname,user_email, user_password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public User sifreal(String user_email) {
        User user = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL);) {
            preparedStatement.setString(1, user_email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user_password = rs.getString("user_password");
                user = new User(user_email, user_password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public boolean adminUpdate(User user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(KULLANICI_UPDATE);) {
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getUser_surname());
            statement.setString(3, user.getUser_email());
            statement.setString(4, user.getUser_password());
            statement.setInt(5, user.getUser_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean updatePassword(User user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(SIFRE_UPDATE);) {
            statement.setString(1, user.getUser_password());
            statement.setInt(2, user.getUser_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public boolean updateProfile(User user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(PROFIL_UPDATE);) {
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getUser_surname());
            statement.setString(3, user.getUser_email());
            statement.setInt(4, user.getUser_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public void signUp(User user) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT)) {
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_surname());
            preparedStatement.setString(3, user.getUser_email());
            preparedStatement.setString(4, user.getUser_password());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public void addAdmin(User user) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT_ADMIN)) {
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_surname());
            preparedStatement.setString(3, user.getUser_email());
            preparedStatement.setString(4, user.getUser_password());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean uyekontrol(String user_email) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL);) {
            preparedStatement.setString(1, user_email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return false;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return true;
    }
    
    public boolean uyegiriskontrol(String user_email, String user_sifre) {
        
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, user_email);
            preparedStatement.setString(2, user_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public boolean admingiriskontrol(String admin_email, String admin_sifre) {
        
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, admin_email);
            preparedStatement.setString(2, admin_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public User uyegiris(String user_email, String user_sifre) {
         
        User user = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, user_email);
            preparedStatement.setString(2, user_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_surname = rs.getString("user_surname");
                int user_authorization = rs.getInt("user_authorization");
                user = new User(user_id, user_name, user_surname, user_email, user_authorization);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public User admingiris(String admin_email, String admin_sifre) {
         
        User user = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, admin_email);
            preparedStatement.setString(2, admin_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_surname = rs.getString("user_surname");
                int user_authorization = rs.getInt("user_authorization");
                user = new User(user_id, user_name, user_surname, admin_email, user_authorization);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
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
