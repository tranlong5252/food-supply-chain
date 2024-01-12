<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <%@include file="components/providers.jsp" %>
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
                <%@include file="components/message.jsp" %>
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
                    <a href="javascript:void(0)" style="text-decoration:none"
                       onclick="location.href='https://www.youtube.com/watch?v=UqOReQEvwkM'">Forgot password?</a>
                    <br>
                </div>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</div>
</body>
<%@include file="components/footer.jsp" %>
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