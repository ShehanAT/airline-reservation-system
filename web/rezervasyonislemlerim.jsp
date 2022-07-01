<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "navigasyon.jsp" %>
<section class="container" style="margin-bottom: 200px; margin-top: 100px; font-weight: 600;">
    <div class="row">
        <div class="col">                 
            <div class="card shadow-sm border ">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-10">Reservation İşlemlerim</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%        
    String req = request.getParameter("situation");
        if (req != null) {
            if (req.equals("successful")) {
                out.print("<div class=' alert alert-success mt-3' role='alert'>Rezervasyonunuz oluşturulmuştur.</div>");
            } else {
                out.print("<div class=' alert alert-warning mt-3' role='alert'>Reservation oluşturma işlemi tamamlanamadı. Seçili seatlar rezervedir.</div>");
            }
        }
    String re = request.getParameter("guncelleme");
        if (re != null) {
            if (re.equals("successful")) {
                out.print("<div class=' alert alert-success mt-3' role='alert'>Güncelleme işlemi başarıyla tamamlandı.</div>");
            }
        }
    String r = request.getParameter("cancel");
        if (r != null) {
            if (r.equals("unsuccessful")) {
                out.print("<div class=' alert alert-warning mt-3' role='alert'>Reservation cancellation could not be completed. Password is incorrect.</div>");
            }else{
                out.print("<div class=' alert alert-success mt-3' role='alert'>Reservation cancellation completed successfully.</div>");
            }
        }
    %>
    <c:if test="${empty reservationProcess}">
        <div class="mt-5 text-center">
            <i class="far fa-calendar-times text-dark fa-4x mb-3"></i>
            <h2>No Reservation Found</h2>
            <p>No reservations made.</p>
        </div>
        </c:if>
    <c:forEach var="rez" items="${reservationProcess}">
        <div class="row mt-2">
            <div class="col-12">
                <div class="card bg-payment-card shadow-sm border" style="border: 1px solid rgba(0,0,0,.125); margin-top: 10px;">
                    <p class="card-header text-center" style="background-color: #F1F2F8">Reservation Bilgileri</p>
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title"><img class="img-fluid" src="<%=request.getContextPath()%>/assets/data/<c:out value="${rez.company_logo}" />" style="width: 1.5em"> <c:out value="${rez.company_name}" /> - <c:out value="${rez.ucak_ad}" /></h5>
                            <p class="card-title">PNR NUMARASI : <span class="text-white bg-dark"><c:out value="${rez.pnr_no}" /></span></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Uçuş Tarihi: <span class="font-weight-bold"><c:out value="${rez.flight_date}" /></span></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Nereden : <span class="font-weight-bold"><c:out value="${rez.departure_ad}" /></span></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Nereye : <span class="font-weight-bold"><c:out value="${rez.arrival_ad}" /></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Kalkış Saati : <span class="font-weight-bold"><c:out value="${rez.flight_hour}" /></span></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Varış Saati : <span class="font-weight-bold"><c:out value="${rez.arrival_saat}" /></span></p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p>Uçuş süresi : <span class="font-weight-bold"><c:out value="${rez.ucus_s}" /> sa <c:out value="${rez.ucus_d}" />  dk</span></p>
                        </div>
                        <div class="card-text-payment ln-h14 my-3">
                            <p class="f-09em card-text-payment cursor-pointer" data-toggle="modal" data-target="#fly-class-go-<c:out value="${rez.reservation_id}" />"> YOLCU DETAYLARI <i class="fas fa-chevron-right" aria-hidden="true"></i> </p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p class="card-title mt-2">İptal Hakkı <br> Uçuş süresinden 2 saat önceye kadar uçuşunuzu iptal edebilirsiniz.</p>     
                                <c:choose>
                                    <c:when test= "${rez.situation > 0}">
                                    <a style="color:#FF7F00" data-toggle="modal" href="#exampleModal<c:out value="${rez.reservation_id}" />">İptal Et</a>
                                </c:when>
                                <c:otherwise>
                                    <p style="color:#FF7F00"> İptal Etme Hakkınız Yok </p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="d-flex justify-content-between">
                            <p class="card-title mt-2">Değişiklik Hakkı <br> Uçuş süresinden 2 saat önceye kadar informationlerinizi değiştirebilirsiniz.</p>
                                <c:choose>
                                    <c:when test= "${rez.situation > 0}">
                                    <a style="color:#FF7F00" data-toggle="modal" href="#exampleModal0<c:out value="${rez.reservation_id}" />">Bilgileri Değiştir</a>
                                </c:when>
                                <c:otherwise>
                                    <p style="color:#FF7F00"> Değişiklik Hakkınız Yok </p>
                                </c:otherwise>
                            </c:choose>
                        </div> 
                    </div>
                </div>
                <div class="modal fade" id="exampleModal<c:out value="${rez.reservation_id}" />" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel<c:out value="${rez.reservation_id}" />" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel<c:out value="${rez.reservation_id}" />">Rezervasyonu İptal Et</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="residual" method="post">
                                    <input type="hidden" id="reservation_id" name="reservation_id" value="<c:out value="${rez.reservation_id}" />">
                                        <p>Bu işlem geri alınamaz. Emin misiniz ?</p>
                                        <div class="form-group">
                                            <label for="password_delete" class="col-form-label">Şifreniz: </label>
                                            <input type="password" class="form-control" id="password_delete" name="password_delete" required>
                                        </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
                                            <button type="submit" class="btn btn-danger">Onayla</button>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="exampleModal0<c:out value="${rez.reservation_id}" />" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel0<c:out value="${rez.reservation_id}" />" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel0<c:out value="${rez.reservation_id}" />">Bilgileri Değiştir</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="updateReservation">
                                            <input type="hidden" name="id" value="<c:out value='${rez.reservation_id}' />" />
                                            <div class="form-group row">
                                                <label for="traveller_ad<c:out value="${rez.reservation_id}" />" class="col-sm-2 col-form-label">Ad :  </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="traveller_ad<c:out value="${rez.reservation_id}" />" name="traveller_ad<c:out value="${rez.reservation_id}" />" value="<c:out value="${rez.traveller_ad}" />" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="traveller_surname<c:out value="${rez.reservation_id}" />" class="col-sm-2 col-form-label">Soyad :  </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="traveller_surname<c:out value="${rez.reservation_id}" />" name="traveller_surname<c:out value="${rez.reservation_id}" />" value="<c:out value="${rez.traveller_surname}" />" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="traveller_tc<c:out value="${rez.reservation_id}" />" class="col-sm-3 col-form-label">TC Kimlik :  </label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" pattern="[0-9]{11}" id="traveller_tc<c:out value="${rez.reservation_id}" />" name="traveller_tc<c:out value="${rez.reservation_id}" />" value="<c:out value="${rez.traveller_tc}" />" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="traveller_date<c:out value="${rez.reservation_id}" />" class="col-sm-4 col-form-label">Doğum Tarihi :  </label>
                                                <div class="col-sm-8">
                                                    <input type="date" class="form-control" id="traveller_date<c:out value="${rez.reservation_id}" />" name="traveller_date<c:out value="${rez.reservation_id}" />" value="<c:out value="${rez.traveller_date}" />" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="traveller_email<c:out value="${rez.reservation_id}" />" class="col-sm-2 col-form-label">Email :  </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="traveller_email<c:out value="${rez.reservation_id}" />" name="traveller_email<c:out value="${rez.reservation_id}" />" value="<c:out value="${rez.traveller_email}" />" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="traveller_tel<c:out value="${rez.reservation_id}" />" class="col-sm-3 col-form-label">Telefon :  </label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="traveller_tel<c:out value="${rez.reservation_id}" />" name="traveller_tel<c:out value="${rez.reservation_id}" />" pattern="[0-9]{11}" title="05554443322 biçiminde yazınız." value="<c:out value="${rez.traveller_tel}" />" required>
                                                </div>
                                            </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
                                                <button type="submit" class="btn btn-danger">Güncelle</button>                         
                                            </div>
                                    </form>
                                </div>
                            </div>
                        </div>  
                        <div class="modal fade" id="fly-class-go-<c:out value="${rez.reservation_id}" />" tabindex="-1" aria-labelledby="fly-class-go-<c:out value="${rez.reservation_id}" />" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="fly-class-go-<c:out value="${rez.reservation_id}" />">YOLCU DETAYLARI</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Ad Soyad: <c:out value="${rez.traveller_ad}" /> <c:out value="${rez.traveller_surname}" /></p>
                                        <p>TC Kimlik No: <c:out value="${rez.traveller_tc}" /></p>
                                        <p>Doğum Tarihi: <c:out value="${rez.traveller_date}" /></p>
                                        <p>Bilet Ücreti: <c:out value="${rez.yolcu_ucret}" /> ₺</p>
                                        <p>Koltuk No: <c:out value="${rez.seat_no}" /></p>
                                        <br><br>
                                        <h5 style="color:orange">Contact Information</h5>
                                        <p>Email : <c:out value="${rez.yolcu_email}" /></p>
                                        <p>Telefon : <c:out value="${rez.yolcu_tel}" /></p>
                                    </div>
                                </div>
                            </div>
                        </div>
            </div>
        </div>
    </c:forEach>
</section>
<%@ include file = "footer.jsp" %>
