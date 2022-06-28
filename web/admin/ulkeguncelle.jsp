<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Ülke Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" method="post" autocomplete="off" action="showUpdateCountry">
                    <input type="hidden" name="airport_country_id" id="airport_country_id" value="<c:out value='${country.airport_country_id}' />" />
                    <div class="form-group">
                        <label for="airport_country_name">Ülke Adı</label>
                        <input type="text" class="form-control" id="airport_country_name" name="airport_country_name" value="<c:out value='${country.airport_country_name}' />" required>
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