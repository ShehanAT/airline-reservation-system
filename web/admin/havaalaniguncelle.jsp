<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Havaalanı Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="gosterairportUpdate">
                    <input type="hidden" name="airport_id" id="airport_id" value="<c:out value='${airport.airport_id}' />" />
                    <div class="form-group">
                        <label for="airport_name">Ad</label>
                        <input type="text" class="form-control" id="airport_name" name="airport_name" placeholder="Havaalanı Adı" value="<c:out value='${airport.airport_name}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="airport_code">Kod</label>
                        <input type="text" class="form-control" id="airport_code" name="airport_code" placeholder="Havaalanı Kodu" value="<c:out value='${airport.airport_code}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="airport_city_id">Şehir</label>
                        <select class="form-control" id="airport_city_id" name="airport_city_id" required>
                            <c:forEach var="city" items="${airportCity}">
                                <c:choose>
                                    <c:when test= "${city.airport_city_id == airport.airport_city_id}">
                                        <option value="<c:out value='${city.airport_city_id}' />" selected><c:out value='${city.airport_city_name}' /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${city.airport_city_id}' />"><c:out value='${city.airport_city_name}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="airport_country_id">Ülke</label>
                        <select class="form-control" id="airport_country_id" name="airport_country_id" required>
                            <c:forEach var="country" items="${airportCountry}">
                                <c:choose>
                                    <c:when test= "${country.airport_country_id == airport.airport_country_id}">
                                        <option value="<c:out value='${country.airport_country_id}' />" selected><c:out value="${country.airport_country_name}" /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value='${country.airport_country_id}' />"><c:out value='${country.airport_country_name}' /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
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