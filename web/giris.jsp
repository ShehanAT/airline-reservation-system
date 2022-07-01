<%@ include file = "header.jsp" %>
<section class="text-center mb-5">
    <form class="form-signin" action="showLogin" method="post">
        <a href="flight_ticket">
            <img class="mt-3 mb-5" src="assets/images/logo.png" alt="" width="300" height="50">
        </a>
        <h1 class="h3 mb-3 font-weight-600">Tekrar Hoş Geldiniz</h1>
        <%
            String req = request.getParameter("situation");
            if (req != null) {
                out.print("<div class='alert alert-warning' role='alert'>Giriş başarısız.</div>");
            }
            String r = request.getParameter("reservation");
            if (r != null) {
                out.print("<div class='alert alert-warning' role='alert'>Reservation işlemini gerçekleştirmek için giriş yapınız.</div>");
            }
        %>
        <label for="user_email" class="sr-only">E-mail</label>
        <input type="email" id="user_email" name="user_email" class="form-control mb-3" placeholder="E-mail" required autofocus>
        
        <label for="user_password" class="sr-only">Şifre</label>
        <input type="password" id="user_password" name="user_password" class="form-control" placeholder="Şifre" required>
                
        <button class="btn btn-lg btn-warning btn-block shadow-none" type="submit">Giriş Yap</button>
    </form>
        
    <div class="mt-3 mb-3" style="font-weight: 600" >
        <a href="passwordmiunuttum" class="text-decoration-none" style="color: darkblue">Şifrenizi mi unuttunuz ?</a>
    </div>
    <div class="mt-3 mb-3">
        <span>Henüz üye olmadın mı ? </span><a href="signUp" class="text-decoration-none" style="color: #FF7F00">Üye Ol</a>
    </div>
        
</section>
<%@ include file = "footer.jsp" %>