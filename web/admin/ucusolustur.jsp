<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Uçuş Oluştur</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" name="flight" autocomplete="off" action="showCreateFlight" onsubmit="return Kontrol()">
                    <div class="form-group">
                        <label for="flight_departure_id">Uçuş Kalkış</label>
                        <select class="form-control" id="flight_departure_id" name="flight_departure_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <option value="<c:out value='${airport.airport_id}' />"><c:out value="${airport.airport_name}" /> | <c:out value="${airport.airport_code}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="end_heir_id">Uçuş Varış</label>
                        <select class="form-control" id="end_heir_id" name="end_heir_id" required>
                            <c:forEach var="havaalani" items="${havaalani}">
                                <option value="<c:out value='${airport.airport_id}' />"><c:out value="${airport.airport_name}" /> | <c:out value="${airport.airport_code}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="flight_date">Uçuş Tarih</label>
                        <input type="date" class="form-control" id="flight_date" name="flight_date" required>
                    </div>
                    <div class="form-group">
                        <label for="flight_hour">Uçuş Saat</label>
                        <input type="time" class="form-control" id="flight_hour" name="flight_hour" required>
                    </div>
                    <div class="form-group">
                        <label for="flight_time">Uçuş Süre</label>
                        <input type="time" class="form-control" id="flight_time" name="flight_time"required>
                    </div>

                    <div class="form-group">
                        <label for="company_id">Company Name</label>
                        <select class="form-control" id="company_id" name="company_id" required>
                            <c:forEach var="company" items="${company}">
                                <option value="<c:out value='${company.company_id}' />"><c:out value="${company.company_name}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="plane_id">Uçak Adı</label>
                        <select class="form-control" id="plane_id" name="plane_id" required>
                            <c:forEach var="plane" items="${ucak}">
                                <option value="<c:out value='${ucak.plane_id}' />"><c:out value="${ucak.ucak_ad}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="flight_fare">Uçuş Ücreti</label>
                        <input type="number" min="1" step="any" class="form-control" id="flight_fare" name="flight_fare" placeholder="Uçuş Ücreti" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Oluştur</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function Kontrol() {
        var formKontrol = document.forms["flight"];
        var gidis = formKontrol["flight_departure_id"];
        var varis = formKontrol["end_heir_id"];
        gidis = gidis.value;
        varis = varis.value;
        if (gidis === varis) {
            swal({
                title: "Hata",
                text: "Gidiş Yeri ve Varış Yeri aynı olamaz!",
                icon: "warning",
                button: "Tamam!",
            });
            return false;
        } else {
            return true;
        }

    }

</script>
<%@ include file = "footer.jsp" %>