package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kullanici;

public class KullaniciDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String KULLANICI_INSERT = "INSERT INTO users" +
            "  (user_ad, user_soyad, user_email, user_password, user_authorization) VALUES " +
        " (?, ?, ?, ?,"+1+");";
    private static final String KULLANICI_SELECT_ID = "select * from users where user_id=?;";
    private static final String KULLANICI_DELETE = "delete from users where user_id = ?;";
    private static final String KULLANICI_SELECT_EMAIL = "select * from users where user_email = ?;";
    private static final String KULLANICI_SELECT_ALL = "select * from users;";
    private static final String KULLANICI_SELECT_EMAIL_SIFRE = "select * from users where user_email = ? and user_password = ?;";
    private static final String KULLANICI_INSERT_ADMIN ="INSERT INTO users (user_ad, user_soyad, user_email, user_password, user_authorization) VALUES (?,?,?,?,"+2+");";
    private static final String KULLANICI_UPDATE = "update users set user_ad = ?, user_soyad = ?, user_email = ?, user_password = ? where user_id = ?;";
    private static final String PROFIL_UPDATE = "update users set user_ad = ?, user_soyad = ?, user_email = ? where user_id = ?;";
    private static final String ADMIN_SELECT_EMAIL_SIFRE = "select * from users where user_email = ? and user_password = ? and user_authorization=2;";
    private static final String SIFRE_KONTROL_SELECT = "select * from users where user_id=? and user_password=?;";
    private static final String SIFRE_UPDATE = "update users set user_password = ? where user_id = ?;";
    private static final String HESAP_DELETE = "delete from users where user_id = ?;";
    public KullaniciDAO() {}

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

    public List<Kullanici> uyelistele() {
        List<Kullanici> uyeler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_ad = rs.getString("user_ad");
                String user_soyad = rs.getString("user_soyad");
                String user_email = rs.getString("user_email");
                int user_authorization = rs.getInt("user_authorization");
                uyeler.add(new Kullanici(user_id, user_ad, user_soyad, user_email, user_authorization));
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
    
    public Kullanici usersec(int id) {
        Kullanici user = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user_ad = rs.getString("user_ad");
                String user_soyad = rs.getString("user_soyad");
                String user_email = rs.getString("user_email");
                String user_password = rs.getString("user_password");
                user = new Kullanici(id, user_ad,user_soyad,user_email, user_password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public Kullanici sifreal(String user_email) {
        Kullanici user = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL);) {
            preparedStatement.setString(1, user_email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user_password = rs.getString("user_password");
                user = new Kullanici(user_email, user_password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public boolean adminUpdate(Kullanici user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(KULLANICI_UPDATE);) {
            statement.setString(1, user.getKullanici_ad());
            statement.setString(2, user.getKullanici_soyad());
            statement.setString(3, user.getKullanici_email());
            statement.setString(4, user.getKullanici_password());
            statement.setInt(5, user.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean updatePassword(Kullanici user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(SIFRE_UPDATE);) {
            statement.setString(1, user.getKullanici_sifre());
            statement.setInt(2, user.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public boolean updateProfile(Kullanici user) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(PROFIL_UPDATE);) {
            statement.setString(1, user.getKullanici_ad());
            statement.setString(2, user.getKullanici_soyad());
            statement.setString(3, user.getKullanici_email());
            statement.setInt(4, user.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public void signUp(Kullanici user) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT)) {
            preparedStatement.setString(1, user.getKullanici_ad());
            preparedStatement.setString(2, user.getKullanici_soyad());
            preparedStatement.setString(3, user.getKullanici_email());
            preparedStatement.setString(4, user.getKullanici_sifre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public void addAdmin(Kullanici user) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT_ADMIN)) {
            preparedStatement.setString(1, user.getKullanici_ad());
            preparedStatement.setString(2, user.getKullanici_soyad());
            preparedStatement.setString(3, user.getKullanici_email());
            preparedStatement.setString(4, user.getKullanici_sifre());
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
    
    public Kullanici uyegiris(String user_email, String user_sifre) {
         
        Kullanici user = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, user_email);
            preparedStatement.setString(2, user_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_ad = rs.getString("user_ad");
                String user_soyad = rs.getString("user_soyad");
                int user_authorization = rs.getInt("user_authorization");
                user = new Kullanici(user_id, user_ad, user_soyad, user_email, user_authorization);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public Kullanici admingiris(String admin_email, String admin_sifre) {
         
        Kullanici user = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, admin_email);
            preparedStatement.setString(2, admin_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_ad = rs.getString("user_ad");
                String user_soyad = rs.getString("user_soyad");
                int user_authorization = rs.getInt("user_authorization");
                user = new Kullanici(user_id, user_ad, user_soyad, admin_email, user_authorization);
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
