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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
<%@include file="components/header.jsp" %>

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

                    <form method="POST" id="login"></form>
                    <label class="form-label" for="username">Username</label>

                    <div class="input-group mb-3">
                        <input class="form-control" type="text" id="username" name="username" form="login">
                    </div>

                    <label class="form-label" for="password">Password</label>
                    <div class="input-group mb-3">
                        <input class="form-control" type="password" id="password" name="password" form="login">
                        <span class="input-group-text" id="show_hide_password">
                            <i>
                                <a href="" class="fa fa-eye-slash" aria-hidden=true style="text-decoration:none"></a>
                            </i>
                        </span>
                    </div>
                    <div class="d-grid">
                        <button class="btn btn-primary" type="submit" form="login">Login</button>
                    </div>

                </div>
                <div class="card-footer">
<%--                    forgot password?--%>
                    <a href="javascript:void(0)" style="text-decoration:none" onclick="location.href='https://www.youtube.com/watch?v=UqOReQEvwkM'">Forgot password?</a>
                    <br>
                </div>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</div>
</body>
</html>
<script>
    {
        let showHidePassword = document.getElementById("show_hide_password");
        showHidePassword.addEventListener("click", function (event) {
            event.preventDefault();
            showPassword();
        });

        function showPassword() {
            let password = document.getElementById("password");

            if (password.type === "password") {
                password.type = "text";
                let icon = document.querySelector(".fa-eye-slash");
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            } else {
                password.type = "password";
                let icon = document.querySelector(".fa-eye");
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            }
        }
    }
</script>