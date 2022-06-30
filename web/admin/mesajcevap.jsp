<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Mesaj Cevapla</h3>
            </div>
            <div class="card-body">
                <form class="form" autocomplete="off" method="post" action="gostermesajreplyla">
                    <input type="hidden" name="message_id" id="message_id" value="<c:out value='${message.message_id}' />" />
                    <div class="form-group">
                        <label for="message_surname">Gönderen Ad Soyad</label>
                        <input type="text" class="form-control" id="message_surname" name="message_surname" value="<c:out value='${message.message_surname}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="message_tarih">Tarih</label>
                        <input type="text" class="form-control" id="message_tarih" name="message_tarih" value="<c:out value='${message.message_tarih}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="message_email">Gönderen Email</label>
                        <input type="text" class="form-control" id="message_email" name="message_email" value="<c:out value='${message.message_email}' />" readonly>
                    </div>                           
                    <div class="form-group">
                        <label for="message_konu">Konu</label>
                        <input type="text" class="form-control" id="message_konu" name="message_konu" value="<c:out value='${message.message_konu}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="message_icerik">İçerik</label>
                        <textarea class="form-control" id="message_icerik" name="message_icerik" rows="5" readonly><c:out value='${message.message_icerik}' /></textarea>
                    </div>
                    <div class="form-group">
                        <label for="reply_title">Cevap Başlığı</label>
                        <input type="text" class="form-control" id="reply_title" name="reply_title" required>
                    </div>
                    <div class="form-group">
                        <label for="reply_icerik">Cevap İçeriği</label>
                        <textarea class="form-control" id="reply_icerik" name="reply_icerik" rows="5" required></textarea>
                    </div>    
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Cevapla</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>