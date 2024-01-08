<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="header">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Supreme Logics Co</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                    <c:when test="${cookie.accountId != null}">
                        <ul class="navbar-nav">
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

<div class="container">
    <div class="row">
        <div class="col-4"></div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h3>Login</h3>
                </div>
                <c:if test="${requestScope.error != null}">
                    <p class="text-bg-danger text-center">${requestScope.error}</p>
                </c:if>
                <div class="card-body">

                    <form method="POST">
                        <div class="mb-3">
                            <label class="form-label" for="username">Username</label>
                            <input class="form-control" type="text" id="username" name="username">
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="password">Password</label>
                            <input class="form-control" type="password" id="password" name="password">
                        </div>
                        <div class="d-grid">
                            <button class="btn btn-primary" type="submit">Login</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer"></div>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</div>
</body>
</html>
