package servlet;

import dao.CompanyDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Company;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = {"/admin/companyList", "/admin/addCompany", "/admin/showAddCompany", "/admin/deleteCompany", "/admin/companyUpdate", "/admin/showCompanyUpdate"})

public class BusinessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CompanyDAO companyDAO;

    public void init() {
        companyDAO = new CompanyDAO();
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
                case "/admin/companyList":
                    companyList(request, response);
                    break;
                case "/admin/addCompany":
                    addCompany(request, response);
                    break;
                case "/admin/showAddCompany":
                   showAddCompany(request, response);
                    break;
                case "/admin/companyUpdate":
                    companyUpdate(request, response);
                    break;
                case "/admin/showCompanyUpdate":
                   showCompanyUpdate(request, response);
                    break; 
                case "/admin/deleteCompany":
                   deleteCompany(request, response);
                    break;      
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void companyList(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            List<Company> companyList = companyDAO.companyList();
            request.setAttribute("companyList", companyList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyList.jsp");
            dispatcher.forward(request, response);            
        }
    }
    
    private void addCompany(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("addCompany.jsp");
        dispatcher.forward(request, response);
        }
    }  
    
    private void showAddCompany(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            String company_logo = null;
            String company_name = null;

            response.setContentType("text/html; charset=UTF-8");

            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent) {
                return;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List< FileItem> fields = upload.parseRequest(request);
                Iterator< FileItem> it = fields.iterator();
                if (!it.hasNext()) {
                    return;
                }

                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                        if (company_name == null) {
                            if (fileItem.getFieldName().equals("company_name")) {
                                company_name = fileItem.getString("UTF-8");
                            }
                        }
                    } else {
                        if (fileItem.getSize() > 0) {
                            company_logo = fileItem.getName();
                            fileItem.write(new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + company_logo));
                            
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Company yenicompany = new Company(company_name, company_logo);
            companyDAO.addCompany(yenifirma);
            response.sendRedirect("companyList");
        }       
    }   
    
    private void companyUpdate(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            Company company = companyDAO.firmasec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyUpdate.jsp");
            request.setAttribute("company", company);
            dispatcher.forward(request, response);
        }
        
    }
    
    private void showCompanyUpdate(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            String logo = null;
            String company_logo = null;
            String company_name = null;
            int company_id = 0;

            response.setContentType("text/html; charset=UTF-8");
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent) {
                return;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List< FileItem> fields = upload.parseRequest(request);
                Iterator< FileItem> it = fields.iterator();
                if (!it.hasNext()) {
                    return;
                }

                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                        if (company_name == null) {
                            if (fileItem.getFieldName().equals("company_name")) {
                                company_name = fileItem.getString("UTF-8");
                            }
                        }
                        if (logo == null) {
                            if (fileItem.getFieldName().equals("logo")) {
                                logo = fileItem.getString("UTF-8");
                            }
                        }
                        if (company_id == 0) {
                            if (fileItem.getFieldName().equals("company_id")) {
                                company_id = Integer.parseInt(fileItem.getString());
                            }
                        }
                    } else {
                        if (fileItem.getSize() > 0) {
                            company_logo = fileItem.getName();
                            fileItem.write(new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + company_logo));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            File f = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + logo);
            f.delete();
            Company company = new Company(company_id, company_name, company_logo);
            companyDAO.companyUpdate(company);
            response.sendRedirect("companyList");
        }
    }
    
    private void deleteCompany(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("user_authorization") == null) {
            response.sendRedirect("login");
        }else if((Integer) session.getAttribute("user_authorization") != 2){
            response.sendRedirect("../flight_ticket");
        }else{
            int company_id = Integer.parseInt(request.getParameter("id"));
            String company_logo = request.getParameter("logo");
            File f = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + company_logo);
            f.delete();
            companyDAO.deleteCompany(company_id);
            response.sendRedirect("companyList");
        }        
    }
}
