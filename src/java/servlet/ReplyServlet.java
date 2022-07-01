package servlet;

import dao.ReplyDAO;
import dao.MessageDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Message;
import model.Reply;


@WebServlet(urlPatterns = {"/admin/replyMessage", "/admin/showReplyMessage", "/admin/reviewList", "/admin/deleteAnswer", "/admin/reviewAnswer"})

public class ReplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReplyDAO replyDAO;
    private MessageDAO messageDAO;
    public void init() {
        replyDAO = new ReplyDAO();
        messageDAO = new MessageDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
       
        try {
            switch (action) {
                case "/admin/replyMessage":
                    replyMessage(request, response);
                    break;
                case "/admin/showreplyMessage":
                    showreplyMessage(request, response);
                    break;
                case "/admin/reviewList":
                    reviewList(request, response);
                    break;
                case "/admin/deleteAnswer":
                    deleteAnswer(request, response);
                    break;  
                case "/admin/reviewAnswer":
                    reviewAnswer(request, response);
                    break;  
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void reviewList(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            List<Reply> reviewList = replyDAO.reviewList();
            request.setAttribute("reviewList", reviewList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reviewList.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void reviewAnswer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int reply_id = Integer.parseInt(request.getParameter("id"));
            Reply reply = replyDAO.reviewAnswer(reply_id);
            request.setAttribute("reply", reply);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reviewAnswer.jsp");
            dispatcher.forward(request, response);
        }       
    }
    
    private void deleteAnswer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int reply_id = Integer.parseInt(request.getParameter("id"));
            replyDAO.deleteAnswer(reply_id);
            response.sendRedirect("reviewList");
        } 
    }
    
    private void replyMessage(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            messageDAO.messagenotRead(id);
            model.Message message = replyDAO.selectMessage(id);
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("messageReply.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void showreplyMessage(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) sessionn.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int message_id = Integer.parseInt(request.getParameter("message_id"));
            String message_email = request.getParameter("message_email");
            String reply_title = new String((request.getParameter("reply_title")).getBytes("ISO-8859-1"), "UTF-8");
            String reply_contents = new String((request.getParameter("reply_contents")).getBytes("ISO-8859-1"), "UTF-8");
            Reply newReply = new Reply(message_id,reply_contents,reply_title);

            final String to = message_email;
            final String subject = reply_title;
            final String messg = reply_contents;
            final String from = "mail@gmail.com";
            final String pass = "password";

            Properties props = new Properties();    
            props.put("mail.smtp.host", "smtp.gmail.com");    
            props.put("mail.smtp.socketFactory.port", "465");    
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
            props.put("mail.smtp.auth", "true");    
            props.put("mail.smtp.port", "465");     
            Session session = Session.getDefaultInstance(props,    
            new javax.mail.Authenticator() {    
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {    
                    return new PasswordAuthentication(from,pass);  
                }    
            });       
            try {    
               MimeMessage message = new MimeMessage(session);
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
               message.setSubject(subject, "UTF-8");    
               message.setText(messg, "UTF-8");    
               Transport.send(message);    
            } catch (MessagingException e) {throw new RuntimeException(e);

            }        
            messageDAO.messageReply(message_id);
            replyDAO.addReply(newReply);
            response.sendRedirect("reviewList");
        }    
    }
}
    

