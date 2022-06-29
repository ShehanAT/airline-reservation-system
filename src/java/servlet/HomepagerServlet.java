package servlet;

import dao.AirportDAO;
import dao.RezervasyonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Airport;
import model.Rezervasyon;

@WebServlet(urlPatterns = {"/flight_ticket", "/admin/panel"})

public class HomepagerServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private AirportDAO airportDAO;
    private RezervasyonDAO rezervasyonDAO;
    
    public void init() {
        airportDAO = new AirportDAO();
        rezervasyonDAO = new RezervasyonDAO();
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
                case "/flight_ticket":
                    flight_ticket(request, response);
                    break; 
                case "/admin/panel":
                    panel(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void flight_ticket(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        List<Airport> airportList = airportDAO.airportList();
        request.setAttribute("airportList", airportList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");      
        dispatcher.forward(request, response);
    }
    
    private void panel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
                response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
                response.sendRedirect("../flight_ticket");
        }else{
            List<Rezervasyon> rezervasyon = rezervasyonDAO.rezervasyonsayisi();
            request.setAttribute("rezervasyon", rezervasyon);

            List<Rezervasyon> flight = rezervasyonDAO.numberOfFlights();
            request.setAttribute("flight", flight);

            List<Rezervasyon> mesaj = rezervasyonDAO.mesajsayisi();
            request.setAttribute("mesaj", mesaj);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
