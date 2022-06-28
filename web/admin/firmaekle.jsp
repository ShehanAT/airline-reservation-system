<%@ include file = "header.jsp" %>
<%@ include file = "sidebar.jsp" %>
<%@ include file = "topbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-8 mx-auto mb-5">
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0 text-white">Company Ekle</h3>
            </div>
            <div class="card-body">
                <form class="form" action="showAddCompany" method="post" autocomplete="off" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="company_name">Ad</label>
                        <input type="text" class="form-control" id="company_name" name="company_name" placeholder="Company Adı" required>
                    </div>
                    <div class="form-group">
                        <label for="company_logo">Logo</label>
                        <input type="file" class="form-control-file" id="company_logo" name="company_logo" accept="image/*" required>
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