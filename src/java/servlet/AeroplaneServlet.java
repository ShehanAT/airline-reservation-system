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

import dao.UcakDAO;
import javax.servlet.http.HttpSession;
import model.Company;
import model.Ucak;

@WebServlet(urlPatterns = {"/admin/ucakliste", "/admin/ucakekle", "/admin/gosterucakekle", "/admin/ucaksil", "/admin/ucakguncelle", "/admin/gosterucakguncelle"})

public class AeroplaneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UcakDAO ucakDAO;

    public void init() {
        ucakDAO = new UcakDAO();
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
                case "/admin/ucakliste":
                    ucakliste(request, response);
                    break;
                case "/admin/ucakekle":
                    ucakekle(request, response);
                    break;
                case "/admin/gosterucakekle":
                    gosterucakekle(request, response);
                    break;
                case "/admin/ucaksil":
                    ucaksil(request, response);
                    break;
                case "/admin/ucakguncelle":
                    ucakguncelle(request, response);
                    break;
                case "/admin/gosterucakguncelle":
                    gosterucakguncelle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ucakliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Ucak> ucakliste = ucakDAO.ucaklistele();
            request.setAttribute("ucakliste", ucakliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucaklistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void ucakekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Company> firma = ucakDAO.firma();
            request.setAttribute("firma", firma);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucakekle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterucakekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int firma_id = Integer.parseInt(request.getParameter("firma_id"));
            String ucak_ad = new String((request.getParameter("ucak_ad")).getBytes("ISO-8859-1"), "UTF-8");
            int ucak_koltuk = Integer.parseInt(request.getParameter("ucak_koltuk"));
            Ucak yeniucak = new Ucak(ucak_ad, ucak_koltuk, firma_id);
            ucakDAO.ucakekle(yeniucak);
            response.sendRedirect("ucakliste");
        }
    }

    private void ucaksil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int ucak_id = Integer.parseInt(request.getParameter("id"));
            ucakDAO.ucaksil(ucak_id);
            response.sendRedirect("ucakliste");
        }
    }

    private void ucakguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Ucak ucak = ucakDAO.ucaksec(id);
            List<Company> firma = ucakDAO.firma();
            request.setAttribute("firma", firma);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucakguncelle.jsp");
            request.setAttribute("ucak", ucak);
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
            Aeroplane plane = new Aeroplane(airplane_id, airplane_name, airplane_seat, business_id);
            planeDAO.updatePlane(plane);
            response.sendRedirect("flight_list");
        }
    }
}
