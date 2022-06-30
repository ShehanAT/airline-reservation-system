<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">İncele</h3>
            </div>
            <div class="card-body">                               
                    <div class="form-group">
                        <label for="message_surname">Gönderen Ad Soyad</label>
                        <input type="text" class="form-control" id="message_surname" name="message_surname" value="<c:out value='${review.message_surname}' />" disabled>
                    </div>
                    <div class="form-group">
                        <label for="message_date">Tarih</label>
                        <input type="text" class="form-control" id="message_date" name="message_date" value="<c:out value='${review.message_date}' />" disabled>
                    </div>
                    <div class="form-group">
                        <label for="message_email">Gönderen Email</label>
                        <input type="text" class="form-control" id="message_email" name="message_email" value="<c:out value='${review.message_email}' />" disabled>
                    </div>                           
                    <div class="form-group">
                        <label for="message_subject">Konu</label>
                        <input type="text" class="form-control" id="message_subject" name="message_subject" value="<c:out value='${review.message_subject}' />" disabled>
                    </div>
                    <div class="form-group">
                        <label for="message_content">İçerik</label>
                        <textarea class="form-control" id="message_content" name="message_content" rows="5" disabled><c:out value='${review.message_content}' /></textarea>
                    </div>
                    <div class="form-group">
                        <label for="review_title">Cevap Başlığı</label>
                        <input type="text" class="form-control" id="review_title" name="review_title" value="<c:out value='${review.review_title}' />" disabled>
                    </div>
                    <div class="form-group">
                        <label for="review_icerik">Cevap İçeriği</label>
                        <textarea class="form-control" id="review_icerik" name="review_icerik" rows="5" disabled><c:out value='${review.review_icerik}' /></textarea>
                    </div>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>