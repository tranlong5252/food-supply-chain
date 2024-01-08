<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Client companies manager</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<body>

<%@include file="../components/header.jsp"%>

<div class="container">
    <c:if test="${requestScope.error != null}">
        <p class="text-bg-warning">${requestScope.error}</p>
    </c:if>
    <div class="row">
        <div class="col">
            <h2>Client companies management</h2>
            <div class="find-object">
                <form action="Companies" id="searchCompanies"></form>
                <form action="Companies" id="reset"></form>
                    <div class="input-group">
                        <label>
                            <input type="text" name="companyName" placeholder="Find by name" form="searchCompanies">
                        </label>
                        <button type="submit" class="btn btn-sm btn-secondary" name="action" value="searchCompanies" form="searchCompanies">
                            Search
                        </button>
                        <button type="submit" class="btn btn-sm btn-outline-secondary" form="reset">
                            Reset
                        </button>
                    </div>
            </div>
            <div class="table-responsive">
                <table class="table table-sm table-primary table-striped table-hover caption-top table-bordered">
                <caption>
                    <c:if test="${requestScope.page != null}">
                        <c:forEach var="i" begin="1" end="${requestScope.maxPage}" step="1">
<%--                            append current parameters--%>
                            <c:url value="Companies" var="url">
                                <c:param name="page" value="${i}"/>
                                <c:if test="${param.companyName != null}">
                                    <c:param name="companyName" value="${param.companyName}"/>
                                </c:if>
                                <c:if test="${param.action != null}">
                                    <c:param name="action" value="${param.action}"/>
                                </c:if>
                            </c:url>
<%--                            action--%>
                            <c:if test="${requestScope.page == i}">
                                <a href="${url}" class="btn btn-sm btn-secondary">${i}</a>
                            </c:if>
                            <c:if test="${requestScope.page != i}">
                                <a href="${url}" class="btn btn-sm btn-outline-secondary">${i}</a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </caption>
                <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Tax code</th>
                    <th scope="col">Region</th>
                    <th scope="col">Specification</th>
                    <th scope="col">Username</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.companies != null}">
                    <c:forEach items="${requestScope.companies}" var="company">
                        <tr>
                            <td>
                                <label class="col-form-label">${company.id}</label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="companyName" form="edit"
                                           value="${company.name}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="companyTaxCode" form="edit"
                                           value="${company.taxCode}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <select class="form-control-plaintext" name="companyRegion" form="edit" required>
                                        <option value="">Choose region</option>
                                        <c:forEach items="${requestScope.regions}" var="region">
                                            <option ${region.id == company.region.id
                                                    ? 'selected'
                                                    : ''
                                                    } value="${region.id}">${region.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="companySpecification"
                                           form="edit"
                                           value="${company.specification}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="companyUsername"
                                           disabled
                                           value="${company.account.username}">
                                </label>
                            <td>
                                <div class="btn-group btn-group-sm" role="group">
                                    <button type="submit" class="btn btn-secondary" name="action"
                                            form="edit"
                                            value="editCompany">Update
                                    </button>
                                    <button type="submit" class="btn btn-danger" name="action"
                                            form="remove_${company.id}"
                                            value="deleteCompany">Delete
                                    </button>
                                </div>
                                <form action="Company" id="edit">
                                    <input type="hidden" name="companyId" value="${company.id}">
                                    <div class="input-group">

                                    </div>
                                </form>
                                <form action="Company" id="remove_${company.id}">
                                    <input type="hidden" name="companyId" value="${company.id}">
                                    <div class="input-group">
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
                    <tfoot class="table-light">
                <tr>
                    <td>
                    </td>
                    <td>
                        <label>
                            <input class="form-control-plaintext" type="text" name="companyName" form="add" placeholder="Enter name" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="form-control-plaintext" type="text" name="companyTaxCode" form="add" placeholder="Enter Tax code" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <select class="form-control-plaintext" name="companyRegion" form="add" required>
                                <option value="">Choose region</option>
                                <c:forEach items="${requestScope.regions}" var="region">
                                <option value="${region.id}">${region.name}</option>
                                </c:forEach>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="form-control-plaintext" type="text" name="companySpecification" form="add" placeholder="Type" required>
                        </label>
                    </td>
                    <td></td>
                    <td>
                        <button type="submit" class="btn btn-sm btn-success" name="action" value="addCompany"
                                form="add">
                            Add
                        </button>
                    </td>
                </tr>
                </tfoot>
            </table>
            </div>
        </div>
    </div>
</div>
<form action="Company" id="add"></form>
</body>
<%@include file="../components/footer.jsp"%>
</html>