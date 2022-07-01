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

import dao.Airport_CityDAO;
import model.Airport_City;

import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/admin/cityList", "/admin/cityDelete", "/admin/addCity", "/admin/showAddCity", "/admin/updateCity", "/admin/showCityUpdate"})

public class Airport_CityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Airport_CityDAO airport_cityDAO;

    public void init() {
        airport_cityDAO = new Airport_CityDAO();
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
                case "/admin/cityList":
                    cityList(request, response);
                    break;
                case "/admin/addCity":
                    addCity(request, response);
                    break;
                case "/admin/showAddCity":
                    showAddCity(request, response);
                    break;
                case "/admin/updateCity":
                    updateCity(request, response);
                    break;
                case "/admin/showCityUpdate":
                    showCityUpdate(request, response);
                    break;
                case "/admin/deleteCity":
                    deleteCity(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void cityList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Airport_City> cityList = airport_cityDAO.cityLists();
            request.setAttribute("cityList", cityList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("citylistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void addCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("addCity.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showAddCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            String airportCityName = new String((request.getParameter("airportCity_name")).getBytes("ISO-8859-1"), "UTF-8");
            Airport_City newCity = new Airport_City(airportCityName);
            airport_cityDAO.addCity(newCity);
            response.sendRedirect("cityList");
        }
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int airportCity_id = Integer.parseInt(request.getParameter( "id"));
            airport_cityDAO.deleteCity(airportCity_id);
            response.sendRedirect("cityList");
        }
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Airport_City city = airport_cityDAO.chooseCity(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateCity.jsp");
            request.setAttribute("city", city);
            dispatcher.forward(request, response);
        }
    }

    private void showCityUpdate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int airportCity_id = Integer.parseInt(request.getParameter("airportCity_id"));
            String airportCityName = new String((request.getParameter("airportCityName")).getBytes("ISO-8859-1"), "UTF-8");
            Airport_City city = new Airport_City(airportCity_id, airportCityName);
            airport_cityDAO.updateCity(city);
            response.sendRedirect("cityList");
        }
    }
}
