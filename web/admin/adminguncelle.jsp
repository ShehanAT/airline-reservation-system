<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">           
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Admin Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="gosteradminUpdate" method="post" autocomplete="off">
                    <input type="hidden" name="user_id" id="user_id" value="<c:out value='${user.user_id}' />" />
                    <div class="form-group">
                        <label for="user_ad">Ad</label>
                        <input type="text" class="form-control" id="user_ad" name="user_ad" placeholder="Ad" value="<c:out value='${user.user_ad}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="user_soyad">Soyad</label>
                        <input type="text" class="form-control" id="user_soyad" name="user_soyad" placeholder="Soyad" value="<c:out value='${user.user_soyad}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="user_email">Email</label>
                        <input type="text" class="form-control" id="user_email" name="user_email" placeholder="Email" value="<c:out value='${user.user_email}' />" required>
                    </div>
                    <div class="form-group">
                        <label for="user_password">Şifre</label>
                        <input type="password" class="form-control" id="user_password" name="user_password" placeholder="Şifre" value="<c:out value='${user.user_password}' />" required>
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