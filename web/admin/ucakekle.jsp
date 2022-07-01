<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
    <div class="container-fluid">
        <div class="col-md-8 mx-auto mb-5">
                    <div class="card card-outline-secondary">
                        <div class="card-header">
                            <h3 class="mb-0 text-white">Uçak Ekle</h3>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" method="post" autocomplete="off" action="showAddFlight">
                                <div class="form-group">
                                    <label for="ucak_name">Uçak Adı</label>
                                    <input type="text" class="form-control" id="ucak_name" name="ucak_name" placeholder="Uçak Adı" required>
                                </div>
                                <div class="form-group">
                                    <label for="company_id">Uçak Firması</label>
                                    <select class="form-control" id="company_id" name="company_id" required>
                                        <c:forEach var="company" items="${company}">
                                        <option value="<c:out value='${company.company_id}' />"><c:out value="${company.company_name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="ucak_koltuk">Uçak Koltuk Sayısı</label>
                                    <input type="number" min="0" class="form-control" id="ucak_koltuk" name="ucak_koltuk" placeholder="Uçak Koltuk Sayısı" required>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark btn-block">Ekle</button>
                                </div>
                            </form>
                        </div>
                    </div>
    </div>
<%@ include file = "footer.jsp" %>