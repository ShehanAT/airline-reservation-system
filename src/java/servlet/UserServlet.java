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

import dao.UserDAO;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

@WebServlet(urlPatterns = {"/signUp", "/showMember", "/forgotMyPassword", "/showForgotInformation", "/login", "/showLogin", "/login", "/admin/giris", "/admin/showLogin", "/admin/userList", "/admin/addAdmin", "/admin/showAddAdmin", "/admin/deleteUser", "/admin/adminUpdate", "/admin/gosteradminUpdate", "/profil", "/updateProfile", "/updatePassword", "/deleteAccount", "/admin/login", "/admin/myInformation"})

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
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
                case "/admin/showAdminUpdate":
                    gosteradminUpdate(request, response);
                    break;
                case "/admin/showLogin":
                    adminShowLogin(request, response);
                    break;
                case "/admin/login":
                    adminLogin(request, response);
                    break;
                case "/admin/myInformation":
                    adminmyInformation(request, response);
                    break;
                case "/profile":
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
            int user_id = (int) session.getAttribute("user_id");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminUpdate?id=" + user_id);
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
            int user_id = Integer.parseInt(request.getParameter("user_id_password"));
            String user_password = new String((request.getParameter("user_suan_password")).getBytes("ISO-8859-1"), "UTF-8");
            String user_password1 = new String((request.getParameter("user_password1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean control = userDAO.passwordControl(user_id, user_password);
            if (control == true) {
                User user = new User(user_id, user_password1);
                userDAO.updatePassword(user);
                session.setAttribute("user_password", user_password1);
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
            String kontrol_email = (String) session.getAttribute("user_email");
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String user_name = new String((request.getParameter("user_name")).getBytes("ISO-8859-1"), "UTF-8");
            String user_surname = new String((request.getParameter("user_surname")).getBytes("ISO-8859-1"), "UTF-8");
            String user_email = request.getParameter("user_email");
            Boolean control = userDAO.memberControl(user_email);
            if (control == true || user_email.equals(kontrol_email)) {
                User user = new User(user_id, user_name, user_surname, user_email);
                userDAO.updateProfile(user);
                session.setAttribute("user_name", user_name);
                session.setAttribute("user_surname", user_surname);
                session.setAttribute("user_email", user_email);
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
            int user_id = (int) session.getAttribute("user_id");
            String user_name = (String) session.getAttribute("user_name");
            String user_email = (String) session.getAttribute("user_email");
            String user_surname = (String) session.getAttribute("user_surname");
            User user = new User(user_id, user_name, user_surname, user_email);
            request.setAttribute("user", user);
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
            userDAO.deleteUser(id);
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
            String user_name = new String((request.getParameter("user_name")).getBytes("ISO-8859-1"), "UTF-8");
            String user_surname = new String((request.getParameter("user_surname")).getBytes("ISO-8859-1"), "UTF-8");
            String user_email = request.getParameter("user_email");
            String user_password = new String((request.getParameter("user_password")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean control = userDAO.memberControl(user_email);
            if (control == true) {
                User newUser = new User(user_name, user_surname, user_email, user_password);
                userDAO.addAdmin(newUser);
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
            List<User> userList = userDAO.uyelistele();
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
            User user = userDAO.selectUser(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminUpdate.jsp");
            request.setAttribute("user", user);
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
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String user_name = new String((request.getParameter("user_name")).getBytes("ISO-8859-1"), "UTF-8");
            String user_surname = new String((request.getParameter("user_surname")).getBytes("ISO-8859-1"), "UTF-8");
            String user_email = request.getParameter("user_email");
            String user_password = new String((request.getParameter("user_password")).getBytes("ISO-8859-1"), "UTF-8");
            User user = new User(user_id, user_name, user_surname, user_email, user_password);
            userDAO.adminUpdate(user);
            response.sendRedirect("userList");
        }
    }

    private void showForgotInformation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("user_authorization") == null) {
            String user_email = request.getParameter("user_email");
            Boolean kontrol = userDAO.memberControl(user_email);
            if (kontrol == false) {
                User user = userDAO.cipher(user_email);
                String user_password = user.getUser_password();
                final String to = user_email;
                final String subject = "HAWKEYE Giriş Şifresi";
                final String messg = "Sisteme giriş için şifreniz : " + user_password;
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
            String user_name = new String((request.getParameter("user_name")).getBytes("ISO-8859-1"), "UTF-8");
            String user_surname = new String((request.getParameter("user_surname")).getBytes("ISO-8859-1"), "UTF-8");
            String user_email = request.getParameter("user_email");
            String user_password = new String((request.getParameter("user_password1")).getBytes("ISO-8859-1"), "UTF-8");
            Boolean kontrol = userDAO.memberControl(user_email);
            if (kontrol == true) {
                User yeniKullanici = new User(user_name, user_surname, user_email, user_password);
                userDAO.signUp(yeniKullanici);
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
            String user_email = request.getParameter("user_email");
            String user_password = new String((request.getParameter("user_password")).getBytes("ISO-8859-1"), "UTF-8");

            Boolean kontrol = userDAO.memberLoginControl(user_email, user_password);
            if (kontrol == true) {
                User uye = userDAO.memberLogin(user_email, user_password);
                int user_authorization = uye.getUser_authority();
                String user_name = uye.getUser_name();
                String user_surname = uye.getUser_surname();
                int user_id = uye.getUser_id();

                session.setAttribute("user_id", user_id);
                session.setAttribute("user_name", user_name);
                session.setAttribute("user_surname", user_surname);
                session.setAttribute("user_email", user_email);
                session.setAttribute("user_authorization", user_authorization);
                session.setAttribute("user_password", user_password);

                response.sendRedirect("flight_ticket");
            } else {
                response.sendRedirect("login?situation=unsuccessful");
            }
        } else {
            response.sendRedirect("flight_ticket");
        }
    }

    private void adminLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("panel");
        }
    }

    private void adminShowLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            String admin_email = request.getParameter("admin_email");
            String admin_password = request.getParameter("admin_password");

            Boolean kontrol = userDAO.adminLoginControl(admin_email, admin_password);
            if (kontrol == true) {
                User member = userDAO.adminLogin(admin_email, admin_password);

                int user_authorization = member.getUser_authority();
                String user_name = member.getUser_name();
                String user_surname = member.getUser_surname();
                int user_id = member.getUser_id();

                session.setAttribute("user_id", user_id);
                session.setAttribute("user_name", user_name);
                session.setAttribute("user_surname", user_surname);
                session.setAttribute("user_email", admin_email);
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
            int user_id = Integer.parseInt(request.getParameter("password_id"));
            String user_sifre = request.getParameter("sil_sifre");
            Boolean kontrol = userDAO.sifrekontrol(user_id, user_sifre);
            if (kontrol == true) {
                userDAO.deleteAccount(user_id);
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
