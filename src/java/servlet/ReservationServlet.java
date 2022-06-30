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

@WebServlet(urlPatterns = {"/flight_inquiry", "/admin/rezervasyonliste", "/admin/cancelReservation", "/reservationInquiry", "/makeReservation", "/myReservationTransactions", "/showMyReservationTransactions", "/rezervasyonguncelle", "/residual"})
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
                case "/admin/rezervasyonliste":
                    rezervasyonlistele(request, response);
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
                case "/rezervasyonguncelle":
                    rezervasyonguncelle(request, response);
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
            int rezervasyon_id = Integer.parseInt(request.getParameter("rezervasyon_id"));
            String kullanici_sifre = (String) session.getAttribute("kullanici_sifre");
            String sifre = request.getParameter("sil_sifre");
            if (kullanici_sifre.equals(sifre)) {
                reservationDAO.cancelReservation(rezervasyon_id);
                response.sendRedirect("myReservationTransactions?cancel=successful");
            } else {
                response.sendRedirect("myReservationTransactions?cancel=unsuccessful");
            }
        }
    }

    private void rezervasyonguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String yolcu_ad = new String((request.getParameter("yolcu_ad" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String yolcu_soyad = new String((request.getParameter("yolcu_soyad" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String yolcu_tc = request.getParameter("yolcu_tc" + id);
            String yolcu_tarih = request.getParameter("yolcu_tarih" + id);
            String yolcu_email = request.getParameter("yolcu_email" + id);
            String yolcu_tel = request.getParameter("yolcu_tel" + id);
            Reservation rez = new Reservation(id, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tarih);
            reservationDAO.rezervasyonguncelle(rez);
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
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            reservationDAO.cancellationStatus1(kullanici_id);
            reservationDAO.cancellationStatus0(kullanici_id);
            List<Reservation> rezervasyonislem = reservationDAO.rezervasyonislem(kullanici_id);
            request.setAttribute("rezervasyonislem", rezervasyonislem);

            RequestDispatcher dispatcher = request.getRequestDispatcher("myReservationTransactions.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gostermyReservationTransactions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int flight_id = Integer.parseInt(request.getParameter("flight_id"));
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            String yolcu_email = request.getParameter("yolcu_email");
            String yolcu_tel = request.getParameter("yolcu_tel");
            String pnr_no;
            int c_sayi = Integer.parseInt(request.getParameter("c_sayi"));
            int y_sayi = Integer.parseInt(request.getParameter("y_sayi"));
            Double u_ucret = Double.parseDouble(request.getParameter("u_ucret"));
            int yolcu_tip;
            String yolcu_ad;
            String yolcu_soyad;
            String yolcu_tc;
            String yolcu_tarih;
            String yolcu_koltuk;
            Boolean conclusion = false;
            for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                yolcu_koltuk = request.getParameter("yolcu_koltuk" + i);
                conclusion = reservationDAO.seatControl(flight_id, yolcu_koltuk);
            }
            if (conclusion == true) {
                response.sendRedirect("myReservationTransactions?situation=unsuccessful");
            } else {
                for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                    pnr_no = getAlphaNumericString(8);
                    yolcu_tip = Integer.parseInt(request.getParameter("yolcu_tip" + i));
                    yolcu_ad = new String((request.getParameter("yolcu_ad" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    yolcu_soyad = new String((request.getParameter("yolcu_soyad" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    yolcu_tc = request.getParameter("yolcu_tc" + i);
                    yolcu_tarih = request.getParameter("yolcu_tarih" + i);
                    yolcu_koltuk = request.getParameter("yolcu_koltuk" + i);
                    Reservation rezervasyon = new Reservation(pnr_no, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, yolcu_koltuk, kullanici_id, flight_id, yolcu_tarih, u_ucret);
                    reservationDAO.rezervasyonekle(rezervasyon);
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
            int rezervasyon_id = Integer.parseInt(request.getParameter("id"));
            reservationDAO.cancelReservation(rezervasyon_id);
            response.sendRedirect("rezervasyonliste");
        }
    }

    private void flight_inquiry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int airport_departure_id = Integer.parseInt(request.getParameter("gidis"));
        int airport_heir_id = Integer.parseInt(request.getParameter("varis"));
        String flight_date = request.getParameter("gidis_tarih");
        int yetiskin_sayi = Integer.parseInt(request.getParameter("yetiskin"));
        int cocuk_sayi = Integer.parseInt(request.getParameter("cocuk"));

        Reservation rezervasyon = new Reservation(airport_departure_id, airport_heir_id, flight_date, yetiskin_sayi, cocuk_sayi);
        request.setAttribute("rezervasyon", rezervasyon);
        List<Reservation> tekyonsorgula = reservationDAO.tekyonsorgulama(rezervasyon);
        request.setAttribute("flight_inquiry", tekyonsorgula);
        List<Airport> airportList = airportDAO.airportList();
        request.setAttribute("airportList", airportList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("flight_inquiry.jsp");
        dispatcher.forward(request, response);
    }

    private void rezervasyonlistele(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Reservation> rezervasyonliste = reservationDAO.rezervasyonlistele();
            request.setAttribute("rezervasyonliste", rezervasyonliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonlistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login?rezervasyon=unsuccessful");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Reservation ucusbilgileri = reservationDAO.ucusbilgileri(id);
            request.setAttribute("ucusbilgileri", ucusbilgileri);

            List<Reservation> koltuk = reservationDAO.koltukbilgi(id);
            request.setAttribute("koltuk", koltuk);

            Reservation koltuk_dolu = reservationDAO.dolukoltuk(id);
            request.setAttribute("koltuk_dolu", koltuk_dolu);

            int yetiskin_sayi = Integer.parseInt(request.getParameter("yetiskin"));
            int cocuk_sayi = Integer.parseInt(request.getParameter("cocuk"));
            Reservation yolcusayi = new Reservation(yetiskin_sayi, cocuk_sayi);
            request.setAttribute("yolcusayi", yolcusayi);

            RequestDispatcher dispatcher = request.getRequestDispatcher("makeReservation.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void reservationInquiry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String pnr_no = request.getParameter("pnr_no");
        String yolcu_soyad = new String((request.getParameter("yolcu_soyad")).getBytes("ISO-8859-1"), "UTF-8");
        Reservation reservationLogin = new Reservation(pnr_no, yolcu_soyad);
        request.setAttribute("reservationLogin", reservationLogin);

        Reservation rezervasyonsec = reservationDAO.rezervasyonsec(pnr_no, yolcu_soyad);
        request.setAttribute("rezervasyon", rezervasyonsec);

        if (rezervasyonsec == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservationInquiry.jsp?situation=unsuccessful");
            dispatcher.forward(request, response);
        } else {
            Reservation rezervasyonbilgi = reservationDAO.rezervasyonbilgi(rezervasyonsec.getUcus_id(), rezervasyonsec.getReservation_id());
            request.setAttribute("rezervasyonbilgi", rezervasyonbilgi);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservationInquiry.jsp");
            dispatcher.forward(request, response);
        }
    }
}
