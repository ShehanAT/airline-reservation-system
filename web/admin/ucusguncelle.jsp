<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Uçuş Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gosterucusguncelle">
                    <input type="hidden" name="flight_id" id="flight_id" value="<c:out value='${flight.flight_id}' />" />
                    <div class="form-group">
                        <label for="flight_departure_id">Uçuş Kalkış</label>
                        <select class="form-control" id="flight_departure_id" name="flight_departure_id" required>
                            <c:forEach var="airport" items="${airport}">
                                <c:choose>
                                    <c:when test= "${airport.airport_id == flight.flight_departure_id}">
                                        <option value="<c:out value='${airport.airport_id}' />" selected><c:out value='${airport.airport_name}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${airport.airport_id}' />"><c:out value='${airport.airport_name}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="end_heir_id">Uçuş Varış</label>
                        <select class="form-control" id="end_heir_id" name="end_heir_id" required>
                            <c:forEach var="airport" items="${airport}">
                                <c:choose>
                                    <c:when test= "${airport.airport_id == flight.end_heir_id}">
                                        <option value="<c:out value='${airport.airport_id}' />" selected><c:out value='${airport.airport_name}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${airport.airport_id}' />"><c:out value='${airport.airport_name}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="flight_date">Uçuş Tarih</label>
                        <input type="date" class="form-control" id="flight_date" name="flight_date" value="<c:out value='${flight.flight_date}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="flight_hour">Uçuş Saat</label>
                        <input type="time" class="form-control" id="flight_hour" name="flight_hour" value="<c:out value='${flight.flight_hour}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="flight_time">Uçuş Süre</label>
                        <input type="time" class="form-control" id="flight_time" name="flight_time" value="<c:out value='${flight.flight_time}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="company_id">Company Name</label>
                        <select class="form-control" id="company_id" name="company_id" required>
                            <c:forEach var="company" items="${company}">
                                <c:choose>
                                    <c:when test= "${company.company_id == flight.company_id}">
                                        <option value="<c:out value='${company.company_id}' />" selected><c:out value='${company.company_name}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${company.company_id}' />"><c:out value='${company.company_name}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ucak_id">Uçak Adı</label>
                        <select class="form-control" id="ucak_id" name="ucak_id" required>
                            <c:forEach var="ucak" items="${ucak}">
                                <c:choose>
                                    <c:when test= "${ucak.ucak_id == flight.ucak_id}">
                                        <option value="<c:out value='${ucak.ucak_id }' />" selected><c:out value='${ucak.ucak_ad}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${ucak.ucak_id}' />"><c:out value='${ucak.ucak_ad}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="flight_fare">Uçuş Ücreti</label>
                        <input type="number" min="1" step="any" class="form-control" id="flight_fare" name="flight_fare" placeholder="Uçuş Ücreti" value="<c:out value='${flight.flight_fare}' />"required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Güncelle</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>