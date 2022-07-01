<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "navigasyon.jsp" %>
<section class="container" style="margin-bottom: 200px; margin-top: 100px; font-weight: 600;">
    <div class="row">
        <div class="col">                 
            <div class="card shadow-sm border ">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-10">Kullanıcı Bilgilerim</div>
                        <div class="col-sm-2">
                            <a style="color:#FF7F00" data-toggle="modal" href="#exampleModal">Hesabımı Sil</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Hesabımı Sil</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="deleteAccount" method="post">
                        <input type="hidden" id="password_id" name="password_id" value="<c:out value="${user.user_id}" />">
                            <p>Bu işlem geri alınamaz. Emin misiniz ?</p>
                            <div class="form-group">
                                <label for="sil_sifre" class="col-form-label">Şifreniz: </label>
                                <input type="password" class="form-control" id="sil_sifre" name="sil_sifre" required>
                            </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
                                <button type="submit" class="btn btn-danger">Sil</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
        <%                
        String req = request.getParameter("situation");
            if (req != null) {
                if (req.equals("successful")) {
                    out.print("<div class=' alert alert-success mt-3' role='alert'>Güncelleme İşlemi Başarıyla Tamamlandı.</div>");
                } else if (req.equals("hatali")) {
                    out.print("<div class=' alert alert-warning mt-3' role='alert'>Güncelleme İşlemi Tamamlanamadı. Şu anki şifre hatalı.</div>");
                } else if (req.equals("uyari")) {
                    out.print("<div class=' alert alert-warning mt-3' role='alert'>Hesap Silme İşlemi Tamamlanamadı. Şifre hatalı.</div>");
                }  else {
                    out.print("<div class=' alert alert-warning mt-3' role='alert'>Güncelleme İşlemi Tamamlanamadı. Bu eposta kullanılıyor.</div>");
                }
            }
        %>
        <div class="row mt-3">
            <div class="col-sm-6">                 
                <div class="card border">
                    <p class="card-header text-center" style="background-color: #F1F2F8;">Üyelik Bilgilerim</p>
                    <div class="card-body">
                        <form class="text-muted" action="updateProfile" method="post">
                            <input type="hidden" id="user_id" name="user_id" value="<c:out value="${user.user_id}" />">
                                <div class="form-group row">
                                    <label for="user_name" class="col-sm-4 col-form-label">Ad</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="user_name" name="user_name" value="<c:out value="${user.user_name}" />" placeholder="Ad" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="user_surname" class="col-sm-4 col-form-label">Soyad</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="user_surname" name="user_surname" value="<c:out value="${user.user_surname}" />" placeholder="Soyad" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="user_email" class="col-sm-4 col-form-label">Email Adres</label>
                                    <div class="col-sm-8">
                                        <input type="email" class="form-control" id="user_email" name="user_email" value="<c:out value="${user.user_email}" />" placeholder="Email" required>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-outline-warning">Güncelle</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">                 
                <div class="card border">
                    <p class="card-header text-center" style="background-color: #F1F2F8;">Şifre Güncelleme</p>
                    <div class="card-body">
                        <form class="text-muted" id="sifreguncelleme" name="sifreguncelleme" method="POST" action="updatePassword" onsubmit="return Kontrol()">
                            <input type="hidden" id="user_id_sifre" name="user_id_sifre" value="<c:out value="${user.user_id}" />">
                                <div class="form-group row">
                                    <label for="user_suan_sifre" class="col-sm-4 col-form-label">Şu anki Şifre</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="user_suan_sifre" name="user_suan_sifre" autocomplete="current-password" placeholder="Şu anki Şifre" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="user_sifre1" class="col-sm-4 col-form-label">Yeni Şifre</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="user_sifre1" name="user_sifre1" autocomplete="new-password" placeholder="Yeni Şifre" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="user_sifre2" class="col-sm-4 col-form-label">Yeni Şifre Tekrar</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="user_sifre2" name="user_sifre2" autocomplete="new-password" placeholder="Yeni Şifre Tekrar" required>
                                    </div>
                                </div>
                                <div class="alert alert-warning" role="alert" id="1" style="display:none">Şifre en az 8 karakter uzunluğunda olmalıdır.</div>
                                <div class="alert alert-warning" role="alert" id="2" style="display:none">Şifreler uyuşmuyor.</div>
                                <button type="submit" class="btn btn-outline-warning">Güncelle</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
</section>
<script>
    function Kontrol() {
        var formUyeOl = document.forms["sifreguncelleme"];
        var sifre1 = formUyeOl["user_sifre1"];
        var sifre2 = formUyeOl["user_sifre2"];
        sifre1 = sifre1.value;
        sifre2 = sifre2.value;
        if (sifre1.length < 8) {
            $("#1").css("display", "");
            return false;
        } else {
            $("#1").css("display", "none");
        }
        if (sifre1 === sifre2) {
            return true;
        } else {
            $("#2").css("display", "");
        }
        return false;
    }

</script>

<%@ include file = "footer.jsp" %>
