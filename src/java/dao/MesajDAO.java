package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Message;

public class MessageDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";       
    
    private static final String MESAJ_SELECT_ALL = "select * from message;";
    private static final String MESAJ_DELETE = "delete from message where message_id = ?;";
    private static final String MESAJ_OKUNMA_UPDATE = "update message set message_notRead=1 where message_id = ?;";
    private static final String MESAJ_CEVAP_UPDATE = "update message set message_review=1 where message_id = ?;";
    private static final String MESAJ_INSERT = "INSERT INTO message  (message_surname, message_email, message_subject, message_content) VALUES " +
        " (?,?,?,?);"; 
    
    public MessageDAO() {}
    
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
    
    public boolean messagenotRead(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_OKUNMA_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean messagereview(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_CEVAP_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public void addMessage(Message message) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_INSERT)) {
            preparedStatement.setString(1, message.getMessage_surname());
            preparedStatement.setString(2, message.getMessage_email());
            preparedStatement.setString(3, message.getMessage_konu());
            preparedStatement.setString(4, message.getMessage_icerik());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public List<Message> messageListle() {
        List<Message> messageslar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                String message_surname = rs.getString("message_surname");
                String message_email = rs.getString("message_email");
                String message_subject = rs.getString("message_subject");
                String message_content = rs.getString("message_content");
                String message_date = rs.getString("message_date");
                int message_notRead = rs.getInt("message_notRead");
                int message_review = rs.getInt("message_review");
                messageslar.add(new Message(message_id,message_surname,message_email,message_subject,message_content,message_date,message_notRead,message_review));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mesajlar;
    } 
    
    public boolean deleteMessage(int id) throws SQLException {
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
