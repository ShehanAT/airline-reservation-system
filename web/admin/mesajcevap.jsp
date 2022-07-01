<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Message Replyla</h3>
            </div>
            <div class="card-body">
                <form class="form" autocomplete="off" method="post" action="showMessageReply">
                    <input type="hidden" name="message_id" id="message_id" value="<c:out value='${message.message_id}' />" />
                    <div class="form-group">
                        <label for="message_surname">Gönderen Ad Soyad</label>
                        <input type="text" class="form-control" id="message_surname" name="message_surname" value="<c:out value='${message.message_surname}' />" readonly>
                    </div>
                    <div class="form-group">
                        <label for="message_date">Tarih</label>
                        <input type="text" class="form-control" id="message_date" name="message_date" value="<c:out value='${message.message_date}' />" readonly>
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
                        <label for="message_contents">İçerik</label>
                        <textarea class="form-control" id="message_contents" name="message_contents" rows="5" readonly><c:out value='${message.message_contents}' /></textarea>
                    </div>
                    <div class="form-group">
                        <label for="reply_title">Reply Başlığı</label>
                        <input type="text" class="form-control" id="reply_title" name="reply_title" required>
                    </div>
                    <div class="form-group">
                        <label for="reply_contents">Reply İçeriği</label>
                        <textarea class="form-control" id="reply_contents" name="reply_contents" rows="5" required></textarea>
                    </div>    
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Replyla</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.jsp" %>