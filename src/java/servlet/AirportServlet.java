package servlet;

import dao.AirportDAO;
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
import model.Airport_City;
import model.Airport_Country;

@WebServlet(urlPatterns = {"/admin/airportList", "/admin/addAirport", "/admin/showAirportAdd", "/admin/deleteAirport", "/admin/airportUpdate", "/admin/showAirportUpdate"})

public class AirportServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private AirportDAO airportDAO;

    public void init() {
        airportDAO = new AirportDAO();
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
                case "/admin/airportList":
                    airportList(request, response);
                    break; 
                case "/admin/addAirport":
                    addAirport(request, response);
                    break;  
                case "/admin/showAirportAdd":
                    showAirportAdd(request, response);
                    break;
                case "/admin/deleteAirport":
                    deleteAirport(request, response);
                    break;
                case "/admin/airportUpdate":
                    airportUpdate(request, response);
                    break;                    
                case "/admin/showAirportUpdate":
                    showAirportUpdate(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void airportList(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            List<Airport> airportList = airportDAO.airportList();
            request.setAttribute("airportList", airportList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("airportList.jsp");
            dispatcher.forward(request, response);
        }    
    }
    
    private void addAirport(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            List<Airport_City> airportCity = airportDAO.airportCity();
            request.setAttribute("airportCity", airportCity);
            List<Airport_Country> airportCountry = airportDAO.airportCountry();
            request.setAttribute("airportCountry", airportCountry);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addAirport.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void showAirportAdd(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int airport_country_id = Integer.parseInt(request.getParameter("airport_country_id"));
            int airportCity_id = Integer.parseInt(request.getParameter("airportCity_id"));
            String airport_name = new String((request.getParameter("airport_name")).getBytes("ISO-8859-1"), "UTF-8");
            String airport_code = new String((request.getParameter("airport_code")).getBytes("ISO-8859-1"), "UTF-8");
            Airport newAirport = new Airport(airport_country_id, airportCity_id, airport_name, airport_code);
            airportDAO.addAirport(newAirport);
            response.sendRedirect("airportList");
        }
    }
    
    private void deleteAirport(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int airport_id = Integer.parseInt(request.getParameter("id"));
            airportDAO.deleteAirport(airport_id);
            response.sendRedirect("airportList");
        }
    }
    
    private void airportUpdate(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            Airport airport = airportDAO.selectAirport(id);
            List<Airport_City> airportCity = airportDAO.airportCity();
            request.setAttribute("airportCity", airportCity);
            List<Airport_Country> airportCountry = airportDAO.airportCountry();
            request.setAttribute("airportCountry", airportCountry);
            RequestDispatcher dispatcher = request.getRequestDispatcher("airportUpdate.jsp");
            request.setAttribute("airport", airport);
            dispatcher.forward(request, response);
        }     
    }
    
    private void showAirportUpdate(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int airport_id = Integer.parseInt(request.getParameter("airport_id"));
            int airportCity_id = Integer.parseInt(request.getParameter("airportCity_id"));
            int airport_country_id = Integer.parseInt(request.getParameter("airport_country_id"));
            String airport_name = new String((request.getParameter("airport_name")).getBytes("ISO-8859-1"), "UTF-8");
            String airport_code = new String((request.getParameter("airport_code")).getBytes("ISO-8859-1"), "UTF-8");
            Airport airport = new Airport(airport_id, airport_country_id, airportCity_id, airport_name, airport_code);
            airportDAO.airportUpdate(airport);
            response.sendRedirect("airportList");
        }
        
    }
    
}
