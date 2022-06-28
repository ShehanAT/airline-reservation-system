<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">           
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Admin Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="gosteradminekle" method="post" autocomplete="off">
                    <div class="form-group">
                        <label for="user_ad">Ad</label>
                        <input type="text" class="form-control" id="user_ad" name="user_ad" placeholder="Ad" required>
                    </div>
                    <div class="form-group">
                        <label for="user_soyad">Soyad</label>
                        <input type="text" class="form-control" id="user_soyad" name="user_soyad" placeholder="Soyad"  required>
                    </div>
                    <div class="form-group">
                        <label for="user_email">Email</label>
                        <input type="text" class="form-control" id="user_email" name="user_email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="user_sifre">Şifre</label>
                        <input type="password" class="form-control" id="user_sifre" name="user_sifre" placeholder="Şifre" required>
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