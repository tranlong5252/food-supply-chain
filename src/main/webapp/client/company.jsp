<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <%@include file="../components/message.jsp"%>
    <div class="card">
        <div class="card-header">
            <h3>Company information</h3>
        </div>
        <div class="card-body">
            <form action="ClientCompany" method="post" id="updateCompany">
                <label class="col-sm-2" for="name">Company Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" placeholder="Enter company name" name="name" value="${requestScope.company.name}" required>
                </div>
                <label class="col-sm-2" for="taxCode">Tax code:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="taxCode" placeholder="Enter tax code" name="taxCode" value="${requestScope.company.taxCode}" required>
                </div>
                <label class="col-sm-2" for="Region">Region:</label>
                <div class="col-sm-10">
                    <select id="Region" class="form-select" required name="region">
                        <option value="">Choose region</option>
                        <c:forEach items="${requestScope.regions}" var="region">
                            <option ${region.id == requestScope.company.region.id
                                    ? 'selected'
                                    : ''
                                    } value="${region.id}">${region.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-2" for="specification">Specification:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="specification" placeholder="Enter specification" name="specification" value="${requestScope.company.specification}" required>
                </div>
            </form>
        </div>
        <div class="card-footer">
            <div class="col-sm-10">
                <button type="submit" name="action" value="updateCompany" class="btn btn-success" form="updateCompany">Update</button>
            </div>
        </div>
    </div>

</div>
</body>
<%@include file="../components/footer.jsp"%>
</html>
