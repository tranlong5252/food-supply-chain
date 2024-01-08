<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Supreme Logics Co</title>
    <%@include file="../components/providers.jsp"%>
</head>
<body>
<%@include file="../components/header.jsp"%>
<body>
<%--form of update company info--%>
<div class="container">
    <form action="ClientCompany" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Company Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Enter company name" name="name" value="${requestScope.company.name}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="taxCode">Tax code:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="taxCode" placeholder="Enter tax code" name="taxCode" value="${requestScope.company.taxCode}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="phone">Phone:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="phone" placeholder="Enter phone number" name="phone" value="${requestScope.company.taxCode}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="specification">Specification:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="specification" placeholder="Enter specification" name="specification" value="${requestScope.company.specification}" required>
            </div>
        </div>
<%--        button--%>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" name="action" value="updateCompany" class="btn btn-default">Update</button>
            </div>
        </div>
    </form>
</div>
</body>
<%@include file="../components/footer.jsp"%>
</html>
