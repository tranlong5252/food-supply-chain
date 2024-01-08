<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products Page</title>
    <%@include file="components/providers.jsp"%>
</head>
<body>

<%@include file="components/header.jsp"%>

<div class="container">
    <h1>Welcome to our shop!</h1>
    <a href="Logout">Logout</a>
    <div class="row">
        <div class="col">
            <h2>Products</h2>
            <table class="table table-primary table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.products != null}">
                    <c:forEach items="${requestScope.products}" var="product">
                        <form action="Cart">
                            <tr>
                                <td>${pageScope.product.id}</td>
                                <td>${pageScope.product.name}</td>
                                <td>${pageScope.product.price}</td>
                                <td>
                                    <input type="hidden" name="productId" value="${pageScope.product.id}">
                                    <!--<input type="hidden" name="action" value="addtocart">-->
                                    <div class="input-group">
                                        <input class="form-control form-control-sm" type="number" min="1" max="20"
                                               name="quantity" required>
                                        <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                                value="addtocart">Add
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="col">
            <h2>Cart</h2>
            <table class="table table-success table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${sessionScope.cart != null}" var="cartIsNotNull">
                    <c:forEach items="${sessionScope.cart}" var="cartProduct" varStatus="cartProductStatus">
                        <tr>
                            <td>${pageScope.cartProduct.product.name}</td>
                            <td>
                                <form action="Cart">
                                    <div class="input-group">
                                        <input type="hidden" name="index" value="${pageScope.cartProductStatus.index}">
                                        <input class="form-control form-control-sm" type="number" min="1" max="20"
                                               value="${pageScope.cartProduct.quantity}" name="quantity">
                                        <button class="btn btn-sm btn-secondary" type="submit" name="action"
                                                value="updatecartproduct">Update
                                        </button>
                                    </div>
                                </form>
                            </td>
                            <td>${pageScope.cartProduct.subtotal}</td>
                            <td>
                                <form action="Cart">
                                    <input type="hidden" name="index" value="${pageScope.cartProductStatus.index}">
                                    <button class="btn btn-sm btn-danger" type="submit" name="action"
                                            value="removecartproduct">Remove
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="2">
                        Total
                    </th>
                    <th>
                        <c:if test="${pageScope.cartIsNotNull}">
                            ${sessionScope.cart.getTotal()}
                        </c:if>
                    </th>
                    <th>
                        <button class="btn btn-sm btn-success">Checkout</button>
                    </th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
<%@include file="components/footer.jsp"%>
</html>
