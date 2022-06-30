package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MesajDAO;
import javax.servlet.http.HttpSession;
import model.Mesaj;

@WebServlet(urlPatterns = {"/admin/messageList", "/admin/deleteMessage", "/contact", "/showAddMessage"})

public class MessageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MesajDAO messageDAO;

    public void init() {
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
                case "/admin/messageList":
                    messageList(request, response);
                    break;
                case "/admin/deleteMessage":
                    deleteMessage(request, response);
                    break;
                case "/contact":
                    mesajekle(request, response);
                    break;
                case "/showAddMessage":
                    showAddMessage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void messageList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Mesaj> messageList = messageDAO.messageListle();
            request.setAttribute("messageList", messageList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("messageListle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteMessage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int message_id = Integer.parseInt(request.getParameter("id"));
            messageDAO.deleteMessage(message_id);
            response.sendRedirect("messageList");
        }
    }

    private void mesajekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("contact.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddMessage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String message_surname = new String((request.getParameter("message_surname")).getBytes("ISO-8859-1"), "UTF-8");
        String message_email = request.getParameter("message_email");
        String message_konu = new String((request.getParameter("message_konu")).getBytes("ISO-8859-1"), "UTF-8");
        String message_icerik = new String((request.getParameter("message_icerik")).getBytes("ISO-8859-1"), "UTF-8");
        Mesaj newMessage = new Mesaj(message_surname, message_email, message_konu, message_icerik);
        mesajDAO.mesajekle(newMessage);
        response.sendRedirect("contact?situation=successful");
    }
}
