package servlet;

import dao.UcusDAO;
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

@WebServlet(urlPatterns = {"/admin/ucussil", "/admin/ucusolustur", "/admin/gosterucusolustur", "/admin/guncelucusliste", "/admin/gecmisucusliste", "/admin/ucusguncelle", "/admin/gosterucusguncelle"})

public class FlightServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UcusDAO ucusDAO;

    public void init() {
        ucusDAO = new UcusDAO();
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
                case "/admin/ucusolustur":
                    ucusolustur(request, response);
                    break;
                case "/admin/gosterucusolustur":
                    gosterucusolustur(request, response);
                    break;
                case "/admin/guncelucusliste":
                    guncelucusliste(request, response);
                    break;
                case "/admin/gecmisucusliste":
                    gecmisucusliste(request, response);
                    break;
                case "/admin/ucussil":
                    ucussil(request, response);
                    break;
                case "/admin/ucusguncelle":
                    ucusguncelle(request, response);
                    break;
                case "/admin/gosterucusguncelle":
                    gosterucusguncelle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ucussil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("id"));
            ucusDAO.ucussil(flight_id);
            response.sendRedirect("guncelucusliste");
        }
    }

    private void guncelucusliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Ucus> guncelucusliste = ucusDAO.guncelucusliste();
            request.setAttribute("guncelucusliste", guncelucusliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("guncelucuslarilistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gecmisucusliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Ucus> gecmisucusliste = ucusDAO.gecmisucusliste();
            request.setAttribute("gecmisucusliste", gecmisucusliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("gecmisucuslarilistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void ucusolustur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Airport> airport = ucusDAO.airport();
            request.setAttribute("airport", airport);

            List<Company> company = ucusDAO.company();
            request.setAttribute("company", company);

            List<Ucak> ucak = ucusDAO.ucak();
            request.setAttribute("ucak", ucak);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ucusolustur.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterucusolustur(HttpServletRequest request, HttpServletResponse response)
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
            int ucak_id = Integer.parseInt(request.getParameter("ucak_id"));
            double flight_fare = Double.parseDouble(request.getParameter("flight_fare"));

            Ucus yeniucus = new Ucus(flight_departure_id, end_heir_id, flight_date, flight_hour, flight_time, company_id, ucak_id, flight_fare);
            Boolean sonuc = ucusDAO.ucuskontrol(yeniucus);
            if (sonuc == false) {
                response.sendRedirect("guncelucusliste?durum=basarisiz");
            } else {
                ucusDAO.ucusolustur(yeniucus);
                response.sendRedirect("guncelucusliste");
            }
        }
    }

    private void ucusguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Ucus ucus = ucusDAO.ucussec(id);
            List<Company> company = ucusDAO.company();
            request.setAttribute("company", company);
            List<Ucak> ucak = ucusDAO.ucak();
            request.setAttribute("ucak", ucak);
            List<Airport> airport = ucusDAO.airport();
            request.setAttribute("airport", airport);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucusguncelle.jsp");
            request.setAttribute("ucus", ucus);
            dispatcher.forward(request, response);
        }
    }

    private void gosterucusguncelle(HttpServletRequest request, HttpServletResponse response)
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
            int ucak_id = Integer.parseInt(request.getParameter("ucak_id"));
            Double flight_fare = Double.parseDouble(request.getParameter("flight_fare"));
            Ucus ucus = new Ucus(flight_id, flight_departure_id, end_heir_id, flight_date, flight_hour, flight_time, company_id, ucak_id, flight_fare);
            ucusDAO.ucusguncelle(ucus);
            response.sendRedirect("guncelucusliste");
        }
    }

}
