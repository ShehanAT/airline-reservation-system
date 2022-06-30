package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mesaj;

public class MesajDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";       
    
    private static final String MESAJ_SELECT_ALL = "select * from mesaj;";
    private static final String MESAJ_DELETE = "delete from mesaj where mesaj_id = ?;";
    private static final String MESAJ_OKUNMA_UPDATE = "update mesaj set mesaj_notRead=1 where mesaj_id = ?;";
    private static final String MESAJ_CEVAP_UPDATE = "update mesaj set mesaj_review=1 where mesaj_id = ?;";
    private static final String MESAJ_INSERT = "INSERT INTO mesaj  (mesaj_surname, mesaj_email, message_subject, message_content) VALUES " +
        " (?,?,?,?);"; 
    
    public MesajDAO() {}
    
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
    
    public boolean mesajnotRead(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_OKUNMA_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean mesajreview(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_CEVAP_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public void mesajekle(Mesaj mesaj) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_INSERT)) {
            preparedStatement.setString(1, message.getMesaj_surname());
            preparedStatement.setString(2, message.getMesaj_email());
            preparedStatement.setString(3, message.getMesaj_konu());
            preparedStatement.setString(4, message.getMesaj_icerik());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public List<Mesaj> mesajlistele() {
        List<Mesaj> mesajlar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int mesaj_id = rs.getInt("mesaj_id");
                String mesaj_surname = rs.getString("mesaj_surname");
                String mesaj_email = rs.getString("mesaj_email");
                String message_subject = rs.getString("message_subject");
                String message_content = rs.getString("message_content");
                String message_date = rs.getString("message_date");
                int mesaj_notRead = rs.getInt("mesaj_notRead");
                int mesaj_review = rs.getInt("mesaj_review");
                mesajlar.add(new Mesaj(mesaj_id,mesaj_surname,mesaj_email,message_subject,message_content,message_date,mesaj_notRead,mesaj_review));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mesajlar;
    } 
    
    public boolean mesajsil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(MESAJ_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
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
