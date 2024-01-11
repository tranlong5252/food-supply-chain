<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Products Page</title>
    <%@include file="../components/providers.jsp" %>
</head>
<body>

<%@include file="../components/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col">
            <h2>Products</h2>
            <c:if test="${requestScope.products != null}">
                <table class="table table-striped caption-top">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.products}" var="product">
                        <tr>
                            <td>
                                <label class="col-form-label">${product.id}</label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="productName"
                                           form="edit_${product.id}"
                                           value="${product.name}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="productPrice" min="0"
                                           form="edit_${product.price}"
                                           value="${product.price}">
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="productQuantity" min="0"
                                           form="edit_${product.id}"
                                        <%--                                           value="${product.quantity}">--%>
                                </label>
                            </td>
                            <td>
                                <div class="btn-group btn-group-sm" role="group">
                                    <button type="submit" class="btn btn-secondary" name="action"
                                            form="edit_${product.id}"
                                            value="editProduct">Update
                                    </button>
                                    <button type="submit" class="btn btn-danger" name="action"
                                            form="remove_${product.id}"
                                            value="deleteProduct">Delete
                                    </button>
                                </div>
                                <form action="Product" id="edit_${product.id}">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <div class="input-group">

                                    </div>
                                </form>
                                <form action="Product" id="remove_${product.id}">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <div class="input-group">
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot class="table-light">
                    <tr>
                        <td>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="text" name="productName" form="add"
                                       placeholder="Enter name" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="productPrice" form="add" min="0"
                                       placeholder="Enter price" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="productQuantity" form="add" min="0"
                                       placeholder="Type" required>
                            </label>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-sm btn-success" name="action" value="addProduct"
                                    form="add">
                                Add
                            </button>
                        </td>
                    </tr>
                    </tfoot>
                    <caption>
                        <c:if test="${requestScope.page != null}">
                            <c:forEach var="i" begin="1" end="${requestScope.maxPage}" step="1">
                                <%--                            append current parameters--%>
                                <c:url value="Products" var="url">
                                    <c:param name="page" value="${i}"/>
                                    <c:if test="${param.productName != null}">
                                        <c:param name="productName" value="${param.productName}"/>
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
                </table>
            </c:if>
            <c:if test="${requestScope.products == null}">
                <p>No products available</p>
            </c:if>
        </div>
    </div>
</div>
<form action="Product" id="add"></form>
</body>
<%@include file="../components/footer.jsp" %>
</html>
