package servlet;

import dao.CevapDAO;
import dao.MesajDAO;
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

import model.Cevap;
import model.Mesaj;

@WebServlet(urlPatterns = {"/admin/replyMessage", "/admin/gosterreplyMessage", "/admin/reviewList", "/admin/deleteAnswer", "/admin/reviewAnswer"})

public class ReplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CevapDAO replyDAO;
    private MesajDAO messageDAO;
    public void init() {
        replyDAO = new CevapDAO();
        messageDAO = new MesajDAO();
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
                case "/admin/gosterreplyMessage":
                    gosterreplyMessage(request, response);
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
            List<Cevap> reviewList = cevapDAO.reviewList();
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
            Cevap reply = replyDAO.reviewAnswer(cevap_id);
            request.setAttribute("reply", cevap);
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
            int cevap_id = Integer.parseInt(request.getParameter("id"));
            cevapDAO.deleteAnswer(cevap_id);
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
            mesajDAO.mesajnotRead(id);
            Mesaj mesaj = cevapDAO.selectMessage(id);
            request.setAttribute("mesaj", mesaj); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("mesajcevap.jsp");      
            dispatcher.forward(request, response);
        }
    }
    
    private void gosterreplyMessage(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) sessionn.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int mesaj_id = Integer.parseInt(request.getParameter("mesaj_id"));
            String mesaj_email = request.getParameter("mesaj_email");
            String cevap_title = new String((request.getParameter("cevap_title")).getBytes("ISO-8859-1"), "UTF-8");
            String cevap_icerik = new String((request.getParameter("cevap_icerik")).getBytes("ISO-8859-1"), "UTF-8");
            Cevap yenicevap = new Cevap(mesaj_id,cevap_icerik,cevap_title);

            final String to = mesaj_email; 
            final String subject = cevap_title;
            final String messg = cevap_icerik;
            final String from = "mail@gmail.com";
            final String pass = "sifre";

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
            mesajDAO.mesajcevap(mesaj_id);
            cevapDAO.cevapekle(yenicevap);
            response.sendRedirect("reviewList");
        }    
    }
}
    

