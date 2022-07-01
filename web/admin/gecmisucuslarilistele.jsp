<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <span class="m-0 font-weight-bold text-white">Geçmiş Uçuşların Listesi</span>
            <a href="createFlight" class="btn btn-dark btn-sm float-right"><i class="fas fa-plus"></i> Uçuş Oluştur</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Kalkış Noktası</th>
                            <th>Varış Noktası</th>
                            <th>Uçuş Tarih</th>
                            <th>Uçuş Saat</th>
                            <th>Süre</th>
                            <th>Uçuş Firması</th>
                            <th>Uçuştaki Uçak</th>
                            <th>Uçuş Ücreti</th>
                            <th>İşlemler</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Kalkış Noktası</th>
                            <th>Varış Noktası</th>
                            <th>Uçuş Tarih</th>
                            <th>Uçuş Saat</th>
                            <th>Süre</th>
                            <th>Uçuş Firması</th>
                            <th>Uçuştaki Uçak</th>
                            <th>Uçuş Ücreti</th>
                            <th>İşlemler</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="pastFlightList" items="${pastFlightList}">
                            <tr>
                                <td><c:out value="${pastFlightList.flight_id}" /></td>
                                <td><c:out value="${pastFlightList.ucus_departure}" /></td>
                                <td><c:out value="${pastFlightList.ucus_varis}" /></td>
                                <td><c:out value="${pastFlightList.flight_date}" /></td>
                                <td><c:out value="${pastFlightList.flight_hour}" /></td>
                                <td><c:out value="${pastFlightList.flight_time}" /></td>
                                <td><c:out value="${pastFlightList.company_name}" /></td>
                                <td><c:out value="${pastFlightList.ucak_name}" /></td>
                                <td><c:out value="${pastFlightList.flight_fare}" /></td>
                                <td>
                                    <a href="deleteFlight?id=<c:out value='${pastFlightList.flight_id}' />">
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