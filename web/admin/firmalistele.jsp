<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Company Listesi</span>
            <a href="addCompany" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Company Ekle</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Company Adı</th>
                            <th>Company Logosu</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Company Adı</th>
                            <th>Company Logosu</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="company" items="${companyList}">
                            <tr>                                          
                                <td><c:out value="${company.company_id}" /></td>
                                <td><c:out value="${company.company_name}" /></td>
                                <td><img width="100px" height="100px" class="img-fluid" src="<%=request.getContextPath()%>/assets/data/<c:out value='${company.company_logo}' />"></td>
                            <td>
                                <a href="companyUpdate?id=<c:out value='${company.company_id}' />">
                                    <button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> Düzenle</button>
                                </a>
                                <a href="deleteCompany?id=<c:out value='${company.company_id}' />&logo=<c:out value='${company.company_logo}' />">
                                    <button class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> Sil</button>
                                </a>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>