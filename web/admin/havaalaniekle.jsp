<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Havaalanı Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="showAirportAdd">
                    <div class="form-group">
                        <label for="airport_name">Ad</label>
                        <input type="text" class="form-control" id="airport_name" name="airport_name" placeholder="Havaalanı Adı" required>
                    </div>
                    <div class="form-group">
                        <label for="airport_code">Kod</label>
                        <input type="text" class="form-control" id="airport_code" name="airport_code" placeholder="Havaalanı Kodu" required>
                    </div>
                    <div class="form-group">
                        <label for="havaalani_city_id">Şehir</label>
                        <select class="form-control" id="havaalani_city_id" name="havaalani_city_id" required>
                            <c:forEach var="city" items="${airportCity}">
                                <option value="<c:out value='${city.havaalani_city_id}' />"><c:out value="${city.airport_city_name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="airport_country_id">Ülke</label>
                        <select class="form-control" id="airport_country_id" name="airport_country_id" required>
                            <c:forEach var="ulke" items="${airportCountry}">
                                <option value="<c:out value='${ulke.airport_country_id}' />"><c:out value="${ulke.airport_country_name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Ekle</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>