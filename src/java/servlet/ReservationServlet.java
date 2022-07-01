package servlet;

import dao.AirportDAO;
import dao.ReservationDAO;
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
import model.Reservation;

@WebServlet(urlPatterns = {"/flight_inquiry", "/admin/reservationList", "/admin/cancelReservation", "/reservationInquiry", "/makeReservation", "/myReservationTransactions", "/showMyReservationTransactions", "/updateReservation", "/residual"})
public class ReservationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AirportDAO airportDAO;
    private ReservationDAO reservationDAO;

    public void init() {
        airportDAO = new AirportDAO();
        reservationDAO = new ReservationDAO();
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
                case "/flight_inquiry":
                    flight_inquiry(request, response);
                    break;
                case "/admin/reservationList":
                    reservationList(request, response);
                    break;
                case "/admin/cancelReservation":
                    cancelReservation(request, response);
                    break;
                case "/reservationInquiry":
                    reservationInquiry(request, response);
                    break;
                case "/makeReservation":
                    makeReservation(request, response);
                    break;
                case "/myReservationTransactions":
                    myReservationTransactions(request, response);
                    break;
                case "/showMyReservationTransactions":
                    showMyReservationTransactions(request, response);
                    break;
                case "/updateReservation":
                    updateReservation(request, response);
                    break;
                case "/residual":
                    residual(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void residual(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int reservation_id = Integer.parseInt(request.getParameter("reservation_id"));
            String user_password = (String) session.getAttribute("user_password");
            String password = request.getParameter("password_delete");
            if (user_password.equals(password)) {
                reservationDAO.cancelReservation(reservation_id);
                response.sendRedirect("myReservationTransactions?cancel=successful");
            } else {
                response.sendRedirect("myReservationTransactions?cancel=unsuccessful");
            }
        }
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String traveller_name = new String((request.getParameter("traveller_name" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String traveller_surname = new String((request.getParameter("traveller_surname" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String traveller_tc = request.getParameter("traveller_tc" + id);
            String traveller_date = request.getParameter("traveller_date" + id);
            String traveller_email = request.getParameter("traveller_email" + id);
            String traveller_tel = request.getParameter("traveller_tel" + id);
            Reservation rez = new Reservation(id, traveller_name, traveller_surname, traveller_email, traveller_tel, traveller_tc, traveller_date);
            reservationDAO.updateReservation(rez);
            response.sendRedirect("myReservationTransactions?guncelleme=successful");
        }
    }

    private void myReservationTransactions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int user_id = (int) session.getAttribute("user_id");
            reservationDAO.cancellationStatus1(user_id);
            reservationDAO.cancellationStatus0(user_id);
            List<Reservation> reservationProcess = reservationDAO.reservationProcess(user_id);
            request.setAttribute("reservationProcess", reservationProcess);

            RequestDispatcher dispatcher = request.getRequestDispatcher("myReservationTransactions.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showmyReservationTransactions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("flight_id"));
            int user_id = (int) session.getAttribute("user_id");
            String traveller_email = request.getParameter("traveller_email");
            String traveller_tel = request.getParameter("traveller_tel");
            String pnr_no;
            int c_sayi = Integer.parseInt(request.getParameter("c_sayi"));
            int y_sayi = Integer.parseInt(request.getParameter("y_sayi"));
            Double u_fee = Double.parseDouble(request.getParameter("u_fee"));
            int traveller_tip;
            String traveller_name;
            String traveller_surname;
            String traveller_tc;
            String traveller_date;
            String traveller_seat;
            Boolean conclusion = false;
            for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                traveller_seat = request.getParameter("traveller_seat" + i);
                conclusion = reservationDAO.seatControl(flight_id, traveller_seat);
            }
            if (conclusion == true) {
                response.sendRedirect("myReservationTransactions?situation=unsuccessful");
            } else {
                for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                    pnr_no = getAlphaNumericString(8);
                    traveller_tip = Integer.parseInt(request.getParameter("traveller_tip" + i));
                    traveller_name = new String((request.getParameter("traveller_name" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    traveller_surname = new String((request.getParameter("traveller_surname" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    traveller_tc = request.getParameter("traveller_tc" + i);
                    traveller_date = request.getParameter("traveller_date" + i);
                    traveller_seat = request.getParameter("traveller_seat" + i);
                    Reservation reservation = new Reservation(pnr_no, traveller_name, traveller_surname, traveller_email, traveller_tel, traveller_tc, traveller_tip, traveller_seat, user_id, flight_id, traveller_date, u_fee);
                    reservationDAO.addReservation(reservation);
                }

                response.sendRedirect("myReservationTransactions?situation=successful");
            }
        }
    }

    private static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    private void cancelReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int reservation_id = Integer.parseInt(request.getParameter("id"));
            reservationDAO.cancelReservation(reservation_id);
            response.sendRedirect("reservationList");
        }
    }

    private void flight_inquiry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int airport_departure_id = Integer.parseInt(request.getParameter("departure"));
        int airport_heir_id = Integer.parseInt(request.getParameter("arrival"));
        String flight_date = request.getParameter("departure_date");
        int adultNumber = Integer.parseInt(request.getParameter("adult"));
        int childrenNumber = Integer.parseInt(request.getParameter("children"));

        Reservation reservation = new Reservation(airport_departure_id, airport_heir_id, flight_date, adultNumber, childrenNumber);
        request.setAttribute("reservation", reservation);
        List<Reservation> questionInquire = reservationDAO.oneWayQuery(reservation);
        request.setAttribute("flight_inquiry", questionInquire);
        List<Airport> airportList = airportDAO.airportList();
        request.setAttribute("airportList", airportList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("flight_inquiry.jsp");
        dispatcher.forward(request, response);
    }

    private void reservationList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Reservation> reservationList = reservationDAO.reservationList();
            request.setAttribute("reservationList", reservationList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservationList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login?reservation=unsuccessful");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Reservation flightInformation = reservationDAO.flightInformation(id);
            request.setAttribute("flightInformation", flightInformation);

            List<Reservation> seat = reservationDAO.seatinformation(id);
            request.setAttribute("seat", seat);

            Reservation seat_full = reservationDAO.fullseat(id);
            request.setAttribute("seat_full", seat_full);

            int adultNumber = Integer.parseInt(request.getParameter("adult"));
            int childrenNumber = Integer.parseInt(request.getParameter("children"));
            Reservation passengerNumber = new Reservation(adultNumber, childrenNumber);
            request.setAttribute("passengerNumber", passengerNumber);

            RequestDispatcher dispatcher = request.getRequestDispatcher("makeReservation.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void reservationInquiry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String pnr_no = request.getParameter("pnr_no");
        String traveller_surname = new String((request.getParameter("traveller_surname")).getBytes("ISO-8859-1"), "UTF-8");
        Reservation reservationLogin = new Reservation(pnr_no, traveller_surname);
        request.setAttribute("reservationLogin", reservationLogin);

        Reservation selectReservation = reservationDAO.selectReservation(pnr_no, traveller_surname);
        request.setAttribute("reservation", selectReservation);

        if (selectReservation == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservationInquiry.jsp?situation=unsuccessful");
            dispatcher.forward(request, response);
        } else {
            Reservation reservationInformation = reservationDAO.reservationInformation(selectReservation.getFlight_id(), selectReservation.getReservation_id());
            request.setAttribute("reservationInformation", reservationInformation);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservationInquiry.jsp");
            dispatcher.forward(request, response);
        }
    }
}
