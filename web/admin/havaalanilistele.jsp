<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Havaalanı Listesi</span>
            <a href="addAirport" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Havaalanı Ekle</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Ülke Adı</th>
                            <th>Şehir Adı</th>
                            <th>Havaalanı Adı</th>
                            <th>Havaalanı Kodu</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Ülke Adı</th>
                            <th>Şehir Adı</th>
                            <th>Havaalanı Adı</th>
                            <th>Havaalanı Kodu</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="havaalani" items="${airportList}">
                            <tr>
                                <td><c:out value="${airport.airport_id}" /></td>
                                <td><c:out value="${airport.airport_country_name}" /></td>
                                <td><c:out value="${airport.airport_city_name}" /></td>
                                <td><c:out value="${airport.airport_name}" /></td>
                                <td><c:out value="${airport.airport_code}" /></td>
                                <td>
                                    <a href="airportUpdate?id=<c:out value='${airport.airport_id}' />">
                                        <button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> Düzenle</button>
                                    </a>
                                    <a href="deleteAirport?id=<c:out value='${airport.airport_id}' />">
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