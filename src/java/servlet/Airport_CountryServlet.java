package servlet;

import dao.Airport_CountryDAO;
import model.Airport_Country;

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


@WebServlet(urlPatterns = {"/admin/countryList", "/admin/deleteCountry", "/admin/countryAdd", "/admin/updateCountry", "/admin/showUpdateCountry", "/admin/showCountryAdd"})

public class Airport_CountryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Airport_CountryDAO airport_countryDAO;

    public void init() {
        airport_countryDAO = new Airport_CountryDAO();
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
                case "/admin/countryList":
                    countryList(request, response);
                    break;
                case "/admin/countryAdd":
                    countryAdd(request, response);
                    break;
                case "/admin/showCountryAdd":
                    showCountryAdd(request, response);
                    break;
                case "/admin/updateCountry":
                    updateCountry(request, response);
                    break;
                case "/admin/showUpdateCountry":
                    showUpdateCountry(request, response);
                    break;
                case "/admin/deleteCountry":
                    deleteCountry(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void countryList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Airport_Country> countryList = airport_countryDAO.listCountry();
            request.setAttribute("country", countryList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listCountry.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void countryAdd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("countryAdd.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showCountryAdd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            String airport_country_name = new String((request.getParameter("airport_country_name")).getBytes("ISO-8859-1"), "UTF-8");
            Airport_Country newCountry = new Airport_Country(airport_country_name);
            airport_countryDAO.countryAdd(newCountry);
            response.sendRedirect("country");
        }
    }

    private void updateCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Airport_Country country = airport_countryDAO.selectCountry(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateCountry.jsp");
            request.setAttribute("country", country);
            dispatcher.forward(request, response);
        }
    }

    private void showUpdateCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int airport_country_id = Integer.parseInt(request.getParameter("airport_country_id"));
            String airport_country_name = new String((request.getParameter("airport_country_name")).getBytes("ISO-8859-1"), "UTF-8");
            Airport_Country country = new Airport_Country(airport_country_id, airport_country_name);
            airport_countryDAO.updateCountry(country);
            response.sendRedirect("country");
        }
    }

    private void deleteCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int airport_country_id = Integer.parseInt(request.getParameter("id"));
            airport_countryDAO.deleteCountry(airport_country_id);
            response.sendRedirect("country");
        }
    }
}
