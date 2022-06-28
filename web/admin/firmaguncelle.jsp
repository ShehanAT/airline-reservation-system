<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Company Güncelle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="showCompanyUpdate" method="post"  autocomplete="off" enctype="multipart/form-data">
                    <input type="hidden" name="company_id" id="company_id" value="<c:out value='${company.company_id}' />" />
                    <input type="hidden" name="logo" id="logo" value="<c:out value='${company.company_logo}' />" />
                    <div class="form-group">
                        <label for="company_name">Ad</label>
                        <input type="text" class="form-control" id="company_name" name="company_name" placeholder="Company Adı" value="<c:out value='${company.company_name}' />" required>
                    </div>
                    <div class="form-group">   
                        <label for="company_logo">Logo</label>
                        <input type="file" class="form-control-file" id="company_logo" name="company_logo" accept="image/*"  required>
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