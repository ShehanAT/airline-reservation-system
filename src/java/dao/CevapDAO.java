package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reply;
import model.Message;

public class ReplyDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   
    
    private static final String CEVAP_SELECT_ALL = "select * from reply\n" +
                                                "INNER JOIN  message ON (message.message_id = reply.message_id);";
    private static final String CEVAP_DELETE = "delete from reply where reply_id = ?;";
    private static final String MESAJ_SELECT_ID = "SELECT * FROM message  where message_id=?;";
    private static final String CEVAP_INSERT = "INSERT INTO reply (message_id, review_icerik, review_title) VALUES (?,?,?);";
    private static final String CEVAP_SELECT_ID = "select * from reply\n" +
                                                "INNER JOIN  message ON (message.message_id = reply.message_id)"+
                                                "where reply_id=?;";
    
    public ReplyDAO() {}
    
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
    
    public List<Reply> reviewList() {
        List<Reply> answers = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reply_id = rs.getInt("reply_id");
                int message_id = rs.getInt("message_id");
                String reply_icerik = rs.getString("reply_icerik");
                String reply_title = rs.getString("reply_title");
                String reply_tarih = rs.getString("reply_tarih");
                String message_surname = rs.getString("message_surname");
                String message_email = rs.getString("message_email");
                String message_konu = rs.getString("message_konu");
                String message_icerik = rs.getString("message_icerik");
                String message_tarih = rs.getString("message_tarih");
                
                answers.add(new Reply(reply_id,message_id,reply_icerik,reply_title,reply_tarih,message_surname,message_email, message_konu, message_icerik,message_tarih));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return answers;
    } 
    
    public Message selectMessage(int id) {
        Message message = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String message_surname = rs.getString("message_surname");
                String message_email = rs.getString("message_email");
                String message_konu = rs.getString("message_konu");
                String message_icerik = rs.getString("message_icerik");
                String message_tarih = rs.getString("message_tarih");
                int message_notRead = rs.getInt("message_notRead");
                int message_reply = rs.getInt("message_reply");
                message = new Message(id, message_surname, message_email, message_konu, message_icerik, message_tarih,message_notRead,message_reply);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return message;
    } 
    
    public Reply reviewAnswer(int id) {
        Reply review = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int review_id = rs.getInt("review_id");
                int message_id = rs.getInt("message_id");
                String review_icerik = rs.getString("review_icerik");
                String review_title = rs.getString("review_title");
                String review_tarih = rs.getString("reply_tarih");
                String message_surname = rs.getString("message_surname");
                String message_email = rs.getString("message_email");
                String message_konu = rs.getString("message_subject");
                String message_content = rs.getString("message_content");
                String message_date = rs.getString("message_date");
                reply = new Reply(reply_id,message_id,reply_icerik,reply_title,reply_tarih,message_surname,message_email, message_subject, message_content,message_date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return reply;
    }    
    
    public void addReply(Reply reply) throws SQLException {
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_INSERT)) {
            preparedStatement.setInt(1, reply.getMessage_id());
            preparedStatement.setString(2, reply.getReply_icerik());
            preparedStatement.setString(3, reply.getReply_title());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean deleteAnswer(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(CEVAP_DELETE);) {
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
