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

@WebServlet(urlPatterns = {"/signUp", "/showMember", "/forgotMyPassword", "/showForgotInformation", "/login", "/showLogin", "/login", "/admin/giris", "/admin/showLogin", "/admin/userList", "/admin/addAdmin", "/admin/showAddAdmin", "/admin/deleteUser", "/admin/adminUpdate", "/admin/gosteradminUpdate", "/profil", "/updateProfile", "/updatePassword", "/deleteAccount", "/admin/login", "/admin/myInformation"})

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
                case "/signUp":
                    signUp(request, response);
                    break;
                case "/showMember":
                    showMember(request, response);
                    break;
                case "/showLogin":
                    showLogin(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/forgotMyPassword":
                    forgotMyPassword(request, response);
                    break;
                case "/showForgotInformation":
                    showForgotInformation(request, response);
                    break;
                case "/login":
                    uyelogin(request, response);
                    break;
                case "/admin/login":
                    adminuyelogin(request, response);
                    break;
                case "/admin/userList":
                    userList(request, response);
                    break;
                case "/admin/addAdmin":
                    addAdmin(request, response);
                    break;
                case "/admin/showAddAdmin":
                    showAddAdmin(request, response);
                    break;
                case "/admin/deleteUser":
                    deleteUser(request, response);
                    break;
                case "/admin/adminUpdate":
                    adminUpdate(request, response);
                    break;
                case "/admin/gosteradminUpdate":
                    gosteradminUpdate(request, response);
                    break;
                case "/admin/showLogin":
                    adminshowLogin(request, response);
                    break;
                case "/admin/giris":
                    admingiris(request, response);
                    break;
                case "/admin/myInformation":
                    adminmyInformation(request, response);
                    break;
                case "/profil":
                    profil(request, response);
                    break;
                case "/updateProfile":
                    updateProfile(request, response);
                    break;
                case "/updatePassword":
                    updatePassword(request, response);
                    break;
                case "/deleteAccount":
                    deleteAccount(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void adminmyInformation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminUpdate?id=" + kullanici_id);
            dispatcher.forward(request, response);
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 1) {
            response.sendRedirect("flight_ticket");
        } else {
            int kullanici_id = Integer.parseInt(request.getParameter("kullanici_id_password"));
            String kullanici_password = new String((request.getParameter("kullanici_suan_password")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_password1 = new String((request.getParameter("kullanici_password1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.passwordkontrol(kullanici_id, kullanici_password);
            if (kontrol == true) {
                Kullanici kullanici = new Kullanici(kullanici_id, kullanici_password1);
                kullaniciDAO.updatePassword(kullanici);
                session.setAttribute("kullanici_password", kullanici_password1);
                response.sendRedirect("profil?situation=successful");
            } else {
                response.sendRedirect("profil?situation=hatali");
            }
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
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
                kullaniciDAO.updateProfile(kullanici);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", kullanici_email);
                response.sendRedirect("profil?situation=successful");
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

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            kullaniciDAO.deleteUser(id);
            response.sendRedirect("userList");
        }
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showAddAdmin(HttpServletRequest request, HttpServletResponse response)
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
            String kullanici_password = new String((request.getParameter("kullanici_password")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == true) {
                Kullanici yenikullanici = new Kullanici(kullanici_ad, kullanici_soyad, kullanici_email, kullanici_password);
                kullaniciDAO.addAdmin(yenikullanici);
                response.sendRedirect("userList");
            } else {
                response.sendRedirect("userList?situation=unsuccessful");
            }
        }
    }

    private void userList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            List<Kullanici> userList = kullaniciDAO.uyelistele();
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userListle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void adminUpdate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        } else if ((Integer) session.getAttribute("user_authorization") != 2) {
            response.sendRedirect("../flight_ticket");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Kullanici kullanici = kullaniciDAO.kullanicisec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminUpdate.jsp");
            request.setAttribute("kullanici", kullanici);
            dispatcher.forward(request, response);
        }
    }

    private void gosteradminUpdate(HttpServletRequest request, HttpServletResponse response)
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
            String kullanici_password = new String((request.getParameter("kullanici_password")).getBytes("ISO-8859-1"), "UTF-8");
            Kullanici kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email, kullanici_password);
            kullaniciDAO.adminUpdate(kullanici);
            response.sendRedirect("userList");
        }
    }

    private void showForgotInformation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("user_authorization") == null) {
            String kullanici_email = request.getParameter("kullanici_email");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == false) {
                Kullanici kullanici = kullaniciDAO.sifreal(kullanici_email);
                String kullanici_password = kullanici.getKullanici_password();
                final String to = kullanici_email;
                final String subject = "HAWKEYE Giriş Şifresi";
                final String messg = "Sisteme giriş için şifreniz : " + kullanici_password;
                final String from = "mail@gmail.com";
                final String pass = "password";

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
                response.sendRedirect("forgotMyPassword?situation=successful");
            } else {
                response.sendRedirect("forgotMyPassword?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void showMember(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String kullanici_ad = new String((request.getParameter("kullanici_ad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_soyad = new String((request.getParameter("kullanici_soyad")).getBytes("ISO-8859-1"), "UTF-8");
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_password = new String((request.getParameter("kullanici_password1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = kullaniciDAO.uyekontrol(kullanici_email);
            if (kontrol == true) {
                Kullanici yeniKullanici = new Kullanici(kullanici_ad, kullanici_soyad, kullanici_email, kullanici_password);
                kullaniciDAO.signUp(yeniKullanici);
                response.sendRedirect("signUp?situation=successful");
            } else {
                response.sendRedirect("signUp?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("signUp.jsp");
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

    private void forgotMyPassword(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("forgotMyPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String kullanici_email = request.getParameter("kullanici_email");
            String kullanici_password = new String((request.getParameter("kullanici_password")).getBytes("ISO-8859-1"), "UTF-8");

            Boolean kontrol = kullaniciDAO.uyegiriskontrol(kullanici_email, kullanici_password);
            if (kontrol == true) {
                Kullanici uye = kullaniciDAO.uyegiris(kullanici_email, kullanici_password);
                int user_authorization = uye.getKullanici_yetki();
                String kullanici_ad = uye.getKullanici_ad();
                String kullanici_soyad = uye.getKullanici_soyad();
                int kullanici_id = uye.getKullanici_id();

                session.setAttribute("kullanici_id", kullanici_id);
                session.setAttribute("kullanici_ad", kullanici_ad);
                session.setAttribute("kullanici_soyad", kullanici_soyad);
                session.setAttribute("kullanici_email", kullanici_email);
                session.setAttribute("user_authorization", user_authorization);
                session.setAttribute("kullanici_password", kullanici_password);

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

    private void adminshowLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String admin_email = request.getParameter("admin_email");
            String admin_password = request.getParameter("admin_password");

            Boolean kontrol = kullaniciDAO.admingiriskontrol(admin_email, admin_password);
            if (kontrol == true) {
                Kullanici uye = kullaniciDAO.admingiris(admin_email, admin_password);

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

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == 1) {
            int kullanici_id = Integer.parseInt(request.getParameter("password_id"));
            String kullanici_sifre = request.getParameter("sil_sifre");
            Boolean kontrol = kullaniciDAO.sifrekontrol(kullanici_id, kullanici_sifre);
            if (kontrol == true) {
                kullaniciDAO.deleteAccount(kullanici_id);
                response.sendRedirect("login");
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
