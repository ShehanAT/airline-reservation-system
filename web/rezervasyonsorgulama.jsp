<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "navigasyon.jsp" %>
<main class="main_search">
    <div class="search">
        <div class="container fill_height">
            <div class="row fill_height">
                <div class="col fill_height">
                    <div class="search_tabs_container">
                        <form action="reservationInquiry" method="post" id="search_form_1" class="search_panel_content d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-lg-between justify-content-start">
                            <div class="search_item">
                                <div>PNR Numaranız</div>
                                <input type="text" name="pnr_no" id="pnr_no" class="search_input" value="<c:out value='${reservationLogin.pnr_no}' />" required="required">
                            </div>
                            <div class="search_item">
                                <div>Soyadınız</div>
                                <input type="text" name="traveller_soyad" id="traveller_soyad" class="search_input" value="<c:out value='${reservationLogin.traveller_soyad}' />" required="required">
                            </div>
                            <button class="search_button">Yeniden Sorgula</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<c:choose>
    <c:when test="${empty reservation}">
        <div class="text-center" style="margin-bottom: 130px; margin-top: 100px;">
            <i class="fas fa-exclamation text-dark fa-4x mt-3" style="margin-left: 40px;"></i>
            <h2 class="mb-3 mt-4">Reservation Bulunamadı</h2>
        </div>
    </c:when>    
    <c:otherwise>
        <section class="container mb-5">
            <div class="card" style="border: 1px solid rgba(0,0,0,.125); margin-top: 100px;">
                <h5 class="card-header text-center" style="background-color: #F1F2F8">REZERVASYON BİLGİLERİ</h5>
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <h5 class="card-title"><img class="img-fluid" src="<%=request.getContextPath()%>/assets/data/<c:out value='${reservationinformation.company_logo}' />" style="width: 1.5em"> <c:out value='${reservationinformation.company_name}' /> - <c:out value='${reservationinformation.ucak_name}' /></h5>
                        <h5 class="card-title">PNR NUMARASI : <span class="text-white bg-dark"><c:out value='${reservation.pnr_no}' /></span></h5>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Uçuş Tarihi: <span class="font-weight-bold"><c:out value='${reservationinformation.flight_date}' /></span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Nereden : <span class="font-weight-bold"><c:out value='${reservationinformation.departure_city}' /> | <c:out value='${reservationinformation.departure_name}' /> [<c:out value='${reservationinformation.departure_code}' />]</span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Nereye : <span class="font-weight-bold"><c:out value='${reservationinformation.arrival_city}' /> | <c:out value='${reservationinformation.arrival_ad}' /> [<c:out value='${reservationinformation.arrival_code}' />]</span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Kalkış Saati : <span class="font-weight-bold"><c:out value='${reservationinformation.flight_hour}' /></span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Uçuş süresi : <span class="font-weight-bold"><c:out value='${reservationinformation.ucus_s}' /> sa <c:out value='${reservationinformation.ucus_d}' /> dk</span></p>
                    </div>
                </div>
            </div>

            <div class="card" style="border: 1px solid rgba(0,0,0,.125); margin-top: 50px;">
                <h5 class="card-header text-center" style="background-color: #F1F2F8">YOLCU BİLGİLERİ</h5>
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <p>Ad Soyad : <span class="font-weight-bold"><c:out value='${reservation.traveller_ad}' /> <c:out value='${reservation.traveller_soyad}' /></span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <c:choose>
                            <c:when test= "${reservation.traveller_tip == 1}">
                                <p>Yolcu Tipi : <span class="font-weight-bold">Çocuk</span></p>
                            </c:when>
                            <c:otherwise>
                                <p>Yolcu Tipi : <span class="font-weight-bold">Yetişkin</span></p>
                            </c:otherwise>
                        </c:choose>    
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>TC Kimlik No : <span class="font-weight-bold"><c:out value='${reservation.traveller_tc}' /></span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>E-mail : <span class="font-weight-bold"><c:out value='${reservation.traveller_email}' /></span></p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Telefon : <span class="font-weight-bold"><c:out value='${reservation.traveller_tel}' /></span></p>
                    </div>
                </div>
            </div>
        </section>
    </c:otherwise>
</c:choose>                          
    
<%@ include file = "footer.jsp" %>
