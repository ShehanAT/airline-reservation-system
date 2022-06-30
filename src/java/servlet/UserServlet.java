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
import javax.servlet.http.HttpSession;

import dao.KullaniciDAO;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Kullanici;

@WebServlet(urlPatterns = {"/uyeol", "/gosteruyeol", "/sifremiunuttum", "/gostersifremiunuttum", "/login", "/gostergiris", "/cikis", "/admin/giris", "/admin/gostergiris", "/admin/kullaniciliste", "/admin/adminekle", "/admin/gosteradminekle", "/admin/kullanicisil", "/admin/adminguncelle", "/admin/gosteradminguncelle", "/profil", "/profilguncelle", "/sifreguncelle", "/hesapsil", "/admin/cikis", "/admin/bilgilerim"})

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private KullaniciDAO kullaniciDAO;

    public void init() {
        kullaniciDAO = new KullaniciDAO();
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
                case "/uyeol":
                    uyeol(request, response);
                    break;
                case "/gosteruyeol":
                    gosteruyeol(request, response);
                    break;
                case "/gostergiris":
                    gostergiris(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/sifremiunuttum":
                    sifremiunuttum(request, response);
                    break;
                case "/gostersifremiunuttum":
                    gostersifremiunuttum(request, response);
                    break;
                case "/cikis":
                    uyecikis(request, response);
                    break;
                case "/admin/cikis":
                    adminuyecikis(request, response);
                    break;
                case "/admin/kullaniciliste":
                    kullaniciliste(request, response);
                    break;
                case "/admin/adminekle":
                    adminekle(request, response);
                    break;
                case "/admin/gosteradminekle":
                    gosteradminekle(request, response);
                    break;
                case "/admin/kullanicisil":
                    kullanicisil(request, response);
                    break;
                case "/admin/adminguncelle":
                    adminguncelle(request, response);
                    break;
                case "/admin/gosteradminguncelle":
                    gosteradminguncelle(request, response);
                    break;
                case "/admin/gostergiris":
                    admingostergiris(request, response);
                    break;
                case "/admin/giris":
                    admingiris(request, response);
                    break;
                case "/admin/bilgilerim":
                    adminbilgilerim(request, response);
                    break;
                case "/profil":
                    profil(request, response);
                    break;
                case "/profilguncelle":
                    profilguncelle(request, response);
                    break;
                case "/sifreguncelle":
                    sifreguncelle(request, response);
                    break;
                case "/hesapsil":
                    hesapsil(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void adminbilgilerim(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminguncelle?id=" + kullanici_id);
            dispatcher.forward(request, response);
        }
    }

    private void sifreguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int kullanici_id = Integer.parseInt(request.getParameter("kullanici_id_sifre"));
            String kullanici_sifre = new String((request.getParameter("kullanici_suan_sifre")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_sifre1 = new String((request.getParameter("kullanici_sifre1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.sifrekontrol(kullanici_id, kullanici_sifre);
            if (kontrol == true) {
                Kullanici kullanici = new Kullanici(kullanici_id, kullanici_sifre1);
                kullaniciDAO.sifreguncelle(kullanici);
                session.setAttribute("kullanici_sifre", kullanici_sifre1);
                response.sendRedirect("profil?situation=basarili");
            } else {
                response.sendRedirect("profil?situation=hatali");
            }
        }
    }

    private void profilguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            String kontrol_email = (String) session.getAttribute("kullanici_email");
            int kullanici_id = Integer.parseInt(request.getParameter("kullanici_id"));
            String kullanici_ad = new String((request.getParameter("kullanici_ad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_soyad = new String((request.getParameter("kullanici_soyad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_email = request.getParameter("kullanici_email");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == true || kullanici_email.equals(kontrol_email)) {
                Kullanici kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email);
                kullaniciDAO.profilguncelle(kullanici);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", kullanici_email);
                response.sendRedirect("profil?situation=basarili");
            } else {
                response.sendRedirect("profil?situation=unsuccessful");
            }
        }
    }

    private void profil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            String kullanici_ad = (String) session.getAttribute("kullanici_ad");
            String kullanici_email = (String) session.getAttribute("kullanici_email");
            String kullanici_soyad = (String) session.getAttribute("kullanici_soyad");
            Kullanici kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email);
            request.setAttribute("kullanici", kullanici);
            RequestDispatcher dispatcher = request.getRequestDispatcher("profil.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void kullanicisil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            kullaniciDAO.kullanicisil(id);
            response.sendRedirect("kullaniciliste");
        }
    }

    private void adminekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminekle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosteradminekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            String kullanici_ad = new String((request.getParameter("kullanici_ad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_soyad = new String((request.getParameter("kullanici_soyad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_sifre = new String((request.getParameter("kullanici_sifre")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == true) {
                Kullanici yenikullanici = new Kullanici(kullanici_ad, kullanici_soyad, kullanici_email, kullanici_sifre);
                kullaniciDAO.adminekle(yenikullanici);
                response.sendRedirect("kullaniciliste");
            } else {
                response.sendRedirect("kullaniciliste?situation=unsuccessful");
            }
        }
    }

    private void kullaniciliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Kullanici> kullaniciliste = kullaniciDAO.uyelistele();
            request.setAttribute("kullaniciliste", kullaniciliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("kullanicilistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void adminguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Kullanici kullanici = kullaniciDAO.kullanicisec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminguncelle.jsp");
            request.setAttribute("kullanici", kullanici);
            dispatcher.forward(request, response);
        }
    }

    private void gosteradminguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int kullanici_id = Integer.parseInt(request.getParameter("kullanici_id"));
            String kullanici_ad = new String((request.getParameter("kullanici_ad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_soyad = new String((request.getParameter("kullanici_soyad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_sifre = new String((request.getParameter("kullanici_sifre")).getBytes("ISO-8859-1"), "UTF-8");
            Kullanici kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email, kullanici_sifre);
            kullaniciDAO.adminguncelle(kullanici);
            response.sendRedirect("kullaniciliste");
        }
    }

    private void gostersifremiunuttum(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("user_authorization") == null) {
            String kullanici_email = request.getParameter("kullanici_email");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == false) {
                Kullanici kullanici = kullaniciDAO.sifreal(kullanici_email);
                String kullanici_sifre = kullanici.getKullanici_sifre();
                final String to = kullanici_email;
                final String subject = "HAWKEYE Giriş Şifresi";
                final String messg = "Sisteme giriş için şifreniz : " + kullanici_sifre;
                final String from = "mail@gmail.com";
                final String pass = "sifre";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, pass);
                            }
                        });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject(subject, "UTF-8");
                    message.setText(messg, "UTF-8");
                    Transport.send(message);

                } catch (MessagingException e) {
                    throw new RuntimeException(e);

                }
                response.sendRedirect("sifremiunuttum?situation=basarili");
            } else {
                response.sendRedirect("sifremiunuttum?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void gosteruyeol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String kullanici_ad = new String((request.getParameter("kullanici_ad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_soyad = new String((request.getParameter("kullanici_soyad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_sifre = new String((request.getParameter("kullanici_sifre1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == true) {
                Kullanici yeniKullanici = new Kullanici(kullanici_ad, kullanici_soyad, kullanici_email, kullanici_sifre);
                kullaniciDAO.uyeol(yeniKullanici);
                response.sendRedirect("uyeol?situation=basarili");
            } else {
                response.sendRedirect("uyeol?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void uyeol(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("uyeol.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void sifremiunuttum(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("sifremiunuttum.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void gostergiris(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_sifre = new String((request.getParameter("kullanici_sifre")).getBytes("ISO-8859-1"), "UTF-8");

            Boolean kontrol = kullaniciDAO.uyegiriskontrol(kullanici_email, kullanici_sifre);
            if (kontrol == true) {
                Kullanici uye = kullaniciDAO.uyegiris(kullanici_email, kullanici_sifre);
                int user_authorization = uye.getKullanici_yetki();
                String kullanici_ad = uye.getKullanici_ad();
                String kullanici_soyad = uye.getKullanici_soyad();
                int kullanici_id = uye.getKullanici_id();

                session.setAttribute("kullanici_id", kullanici_id);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", kullanici_email);
                session.setAttribute("user_authorization", user_authorization);
                session.setAttribute("kullanici_sifre", kullanici_sifre);

                response.sendRedirect("flight_ticket");
            } else {
                response.sendRedirect("login?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void admingiris(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("panel");
        }
    }

    private void admingostergiris(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String admin_email = request.getParameter("admin_email");
            String admin_sifre = request.getParameter("admin_sifre");

            Boolean kontrol = kullaniciDAO.admingiriskontrol(admin_email, admin_sifre);
            if (kontrol == true) {
                Kullanici uye = kullaniciDAO.admingiris(admin_email, admin_sifre);

                int user_authorization = uye.getKullanici_yetki();
                String kullanici_ad = uye.getKullanici_ad();
                String kullanici_soyad = uye.getKullanici_soyad();
                int kullanici_id = uye.getKullanici_id();

                session.setAttribute("kullanici_id", kullanici_id);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", admin_email);
                session.setAttribute("user_authorization", user_authorization);

                response.sendRedirect("panel");
            } else {
                response.sendRedirect("login?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("panel");
        }

    }

    private void hesapsil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == 1) {
            int kullanici_id = Integer.parseInt(request.getParameter("sifre_id"));
            String kullanici_sifre = request.getParameter("sil_sifre");
            Boolean kontrol = kullaniciDAO.sifrekontrol(kullanici_id, kullanici_sifre);
            if (kontrol == true) {
                kullaniciDAO.hesapsil(kullanici_id);
                response.sendRedirect("cikis");
            } else {
                response.sendRedirect("profil?situation=uyari");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    private void uyecikis(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("flight_ticket");
    }

    private void adminuyecikis(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../flight_ticket");
    }
}
