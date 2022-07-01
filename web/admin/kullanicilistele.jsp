<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <%
        String req = request.getParameter("situation");
        if (req != null) {
            if (req.equals("unsuccessful")) {
                out.print("<div class='alert alert-warning' role='alert'>Bu Email kullanılıyor. Kayıt işlemi gerçekleştirilemedi.</div>");
            }
        }
    %>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Kullanıcı Listesi</span>
            <a href="addAdmin" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Admin Ekle</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Ad</th>
                            <th>Soyad</th>
                            <th>Email</th>
                            <th>Yetki</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Ad</th>
                            <th>Soyad</th>
                            <th>Email</th>
                            <th>Yetki</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="user" items="${userliste}">
                            <tr>
                                <td><c:out value="${user.user_id}" /></td>
                                <td><c:out value="${user.user_ad}" /></td>
                                <td><c:out value="${user.user_soyad}" /></td>
                                <td><c:out value="${user.user_email}" /></td>
                                <c:choose>
                                    <c:when test= "${user.user_authorization == 1}">
                                        <td>Üye</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Admin</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <c:if test="${user.user_authorization > 1}">
                                        <a href="adminUpdate?id=<c:out value='${user.user_id}' />">
                                            <button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> Düzenle</button>
                                        </a>
                                    </c:if>                                               
                                    <a href="deleteUser?id=<c:out value='${user.user_id}' />">
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