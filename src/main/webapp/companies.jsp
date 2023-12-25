<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Client companies manager</title>
    <link rel="stylesheet" href="css/table.css">
</head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<body>


<div class="header">
    <h1>Client Companies</h1>
    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="Logout">Logout</a>
</div>

<div class="container">
    <c:if test="${requestScope.error != null}">
        <p>${requestScope.error}</p>
    </c:if>
    <div class="row">
        <div class="col">
            <h2>Products</h2>
            <table class="table table-primary table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Tax code</th>
                    <th>Region</th>
                    <th>Industry/Agriculture</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.companies != null}">
                    <c:forEach items="${requestScope.companies}" var="company">
                        <tr>
                            <td>${company.id}</td>
                            <td>
                                <label>
                                    <input type="text" name="companyName" form="edit" value="${company.name}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="companyTaxCode" form="edit"
                                           value="${company.taxCode}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <select name="companyRegion" form="edit" required>
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
                                    <input type="text" name="companySpecification" form="edit"
                                           value="${company.specification}">
                                </label>
                            </td>
                            <td>
                                <form action="Company" id="edit">
                                    <input type="hidden" name="companyId" value="${company.id}">
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                                value="editCompany">Update
                                        </button>
                                    </div>
                                </form>
                                <form action="Company">
                                    <input type="hidden" name="companyId" value="${company.id}">
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                                value="deleteCompany">Remove
                                        </button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <tr>
                    <td>
                    </td>
                    <td>
                        <label>
                            <input type="text" name="companyName" form="add">
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="text" name="companyTaxCode" form="add">
                        </label>
                    </td>
                    <td>
                        <label>
                            <select name="companyRegion" form="add" required>
                                <option value="">Choose region</option>
                                <c:forEach items="${requestScope.regions}" var="region">
                                <option value="${region.id}">${region.name}</option>
                                </c:forEach>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="text" name="companySpecification" form="add">
                        </label>
                    <td>
                        <button type="submit" class="btn btn-sm btn-secondary" name="action" value="addCompany"
                                form="add">
                            Add
                        </button>
                    </td>
                </tbody>
            </table>
        </div>
    </div>
</div>
<form action="Company" id="add"></form>
</body>
</html>