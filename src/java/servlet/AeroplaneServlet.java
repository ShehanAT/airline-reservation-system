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

import dao.AirplaneDAO;
import javax.servlet.http.HttpSession;
import model.Company;
import model.Airplane;

@WebServlet(urlPatterns = {"/admin/flightList", "/admin/addFlight", "/admin/showAddFlight", "/admin/deleteAirplane", "/admin/updatePlane", "/admin/showAirplaneUpdate"})

public class AeroplaneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AirplaneDAO airplaneDAO;

    public void init() {
        airplaneDAO = new AirplaneDAO();
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
                case "/admin/flightList":
                    flightList(request, response);
                    break;
                case "/admin/addFlight":
                    addFlight(request, response);
                    break;
                case "/admin/showAddFlight":
                    showAddFlight(request, response);
                    break;
                case "/admin/deleteAirplane":
                    deleteAirplane(request, response);
                    break;
                case "/admin/updatePlane":
                    updatePlane(request, response);
                    break;
                case "/admin/showAirplaneUpdate":
                    updateToShow(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void flightList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Airplane> flightList = airplaneDAO.airplaneList();
            request.setAttribute("flightList", flightList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("flightList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Company> company = airplaneDAO.company();
            request.setAttribute("company", company);
            RequestDispatcher dispatcher = request.getRequestDispatcher("addFlight.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showAddFlight(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int company_id = Integer.parseInt(request.getParameter("company_id"));
            String ucak_name = new String((request.getParameter("ucak_name")).getBytes("ISO-8859-1"), "UTF-8");
            int ucak_seat = Integer.parseInt(request.getParameter("ucak_seat"));
            Airplane yeniucak = new Airplane(ucak_name, ucak_seat, company_id);
            airplaneDAO.addFlight(yeniucak);
            response.sendRedirect("flightList");
        }
    }

    private void deleteAirplane(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int plane_id = Integer.parseInt(request.getParameter("id"));
            airplaneDAO.deleteAirplane(plane_id);
            response.sendRedirect("flightList");
        }
    }

    private void updatePlane(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Airplane airplane = airplaneDAO.selectAirplane(id);
            List<Company> company = airplaneDAO.company();
            request.setAttribute("company", company);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucakguncelle.jsp");
            request.setAttribute("plane", airplane);
            dispatcher.forward(request, response);
        }
    }

    private void updateToShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("flight_id"));
            int business_id = Integer.parseInt(request.getParameter("business_id"));
            int airplane_seat = Integer.parseInt(request.getParameter("airplane_seat"));
            String airplane_name = new String((request.getParameter("aircraft_name")).getBytes("ISO-8859-1"), "UTF-8");
            Airplane plane = new Airplane(flight_id, airplane_name, airplane_seat, business_id);
            airplaneDAO.updateAirplane(plane);
            response.sendRedirect("flight_list");
        }
    }
}
