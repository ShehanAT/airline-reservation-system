package servlet;

import dao.FlightDAO;
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

import model.Company;
import model.Airport;
import model.Ucak;
import model.Ucus;

@WebServlet(urlPatterns = {"/admin/deleteFlight", "/admin/createFlight", "/admin/showCreateFlight", "/admin/currentFlightList", "/admin/pastFlightList", "/admin/updateFlight", "/admin/showUpdateFlight"})

public class FlightServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FlightDAO flightDAO;

    public void init() {
        flightDAO = new FlightDAO();
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
                case "/admin/createFlight":
                    createFlight(request, response);
                    break;
                case "/admin/showCreateFlight":
                    showCreateFlight(request, response);
                    break;
                case "/admin/currentFlightList":
                    currentFlightList(request, response);
                    break;
                case "/admin/pastFlightList":
                    pastFlightList(request, response);
                    break;
                case "/admin/deleteFlight":
                    deleteFlight(request, response);
                    break;
                case "/admin/updateFlight":
                    updateFlight(request, response);
                    break;
                case "/admin/showUpdateFlight":
                    gosterupdateFlight(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void deleteFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("id"));
            flightDAO.deleteFlight(flight_id);
            response.sendRedirect("currentFlightList");
        }
    }

    private void currentFlightList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Ucus> currentFlightList = flightDAO.currentFlightList();
            request.setAttribute("currentFlightList", currentFlightList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listCurrentFlights.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void pastFlightList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Ucus> pastFlightList = flightDAO.pastFlightList();
            request.setAttribute("pastFlightList", pastFlightList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listCurrentFlights.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void createFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Airport> airport = flightDAO.airport();
            request.setAttribute("airport", airport);

            List<Company> company = flightDAO.company();
            request.setAttribute("company", company);

            List<Ucak> ucak = flightDAO.ucak();
            request.setAttribute("plane", ucak);

            RequestDispatcher dispatcher = request.getRequestDispatcher("createFlight.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showCreateFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int flight_departure_id = Integer.parseInt(request.getParameter("flight_departure_id"));
            int end_heir_id = Integer.parseInt(request.getParameter("end_heir_id"));
            String flight_date = request.getParameter("flight_date");
            String flight_hour = request.getParameter("flight_hour");
            String flight_time = request.getParameter("flight_time");
            int company_id = Integer.parseInt(request.getParameter("company_id"));
            int plane_id = Integer.parseInt(request.getParameter("plane_id"));
            double flight_fare = Double.parseDouble(request.getParameter("flight_fare"));

            Ucus newFlight = new Ucus(flight_departure_id, end_heir_id, flight_date, flight_hour, flight_time, company_id, plane_id, flight_fare);
            Boolean sonuc = flightDAO.ucuskontrol(newFlight);
            if (sonuc == false) {
                response.sendRedirect("currentFlightList?durum=basarisiz");
            } else {
                flightDAO.createFlight(newFlight);
                response.sendRedirect("currentFlightList");
            }
        }
    }

    private void updateFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Ucus flight = flightDAO.ucussec(id);
            List<Company> company = flightDAO.company();
            request.setAttribute("company", company);
            List<Ucak> ucak = flightDAO.ucak();
            request.setAttribute("plane", ucak);
            List<Airport> airport = flightDAO.airport();
            request.setAttribute("airport", airport);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateFlight.jsp");
            request.setAttribute("flight", flight);
            dispatcher.forward(request, response);
        }
    }

    private void showUpdateFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("flight_id"));
            int flight_departure_id = Integer.parseInt(request.getParameter("flight_departure_id"));
            int end_heir_id = Integer.parseInt(request.getParameter("end_heir_id"));
            String flight_date = request.getParameter("flight_date");
            String flight_hour = request.getParameter("flight_hour");
            String flight_time = request.getParameter("flight_time");
            int company_id = Integer.parseInt(request.getParameter("company_id"));
            int plane_id = Integer.parseInt(request.getParameter("plane_id"));
            Double flight_fare = Double.parseDouble(request.getParameter("flight_fare"));
            Ucus flight = new Ucus(flight_id, flight_departure_id, end_heir_id, flight_date, flight_hour, flight_time, company_id, plane_id, flight_fare);
            flightDAO.updateFlight(flight);
            response.sendRedirect("currentFlightList");
        }
    }

}
