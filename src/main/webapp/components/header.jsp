<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Supreme Logics Co</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="Companies">Companies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Regions">Regions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Products">Products</a>
                    </li>
                </ul>
                <c:choose>
                    <c:when test="${cookie.accountId != null && sessionScope.username != null}">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="Account">Hi ${sessionScope.username}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link link-danger" href="Logout">Logout</a>
                            </li>
                        </ul>

                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="Login">Login</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</div>
<br>