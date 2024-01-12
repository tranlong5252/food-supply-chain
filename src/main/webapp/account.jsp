<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>Supreme Logics Co</title>
    <%@include file="components/providers.jsp" %>
</head>
<body>
<%@include file="components/header.jsp" %>

<div class="container">
    <%@include file="components/message.jsp" %>
    <div class="card">
        <div class="card-header">
            <h1>Change Password for ${sessionScope.username}</h1>
        </div>
        <div class="card-body">
            <form action="ChangePassword" method="post">
                <label for="old_password" class="form-label">Old Password</label>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" id="old_password" name="old_password" required>
                    <span class="input-group-text" id="show_hide_old_password">
                            <i>
                                <a href="" class="fa fa-eye-slash old_password" aria-hidden=true
                                   style="text-decoration:none"></a>
                            </i>
                        </span>
                </div>
                <label for="new_password" class="form-label">New Password</label>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" id="new_password" name="new_password" required>
                    <span class="input-group-text" id="show_hide_new_password">
                            <i>
                                <a href="" class="fa fa-eye-slash new_password" aria-hidden=true
                                   style="text-decoration:none"></a>
                            </i>
                        </span>
                </div>
                <label for="new_confirm_password" class="form-label">Confirm Password</label>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" id="new_confirm_password" name="new_confirm_password"
                           required>
                    <span class="input-group-text" id="show_hide_new_confirm_password">
                            <i>
                                <a href="" class="fa fa-eye-slash new_confirm_password" aria-hidden=true
                                   style="text-decoration:none"></a>
                            </i>
                        </span>
                </div>
                <button type="submit" class="btn btn-primary">Change Password</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="components/footer.jsp" %>
</html>
<script>
    {
        let showHideOldPassword = document.getElementById("show_hide_old_password");
        showHideOldPassword.addEventListener("click", function (event) {
            event.preventDefault();
            showPassword("old_password");
        });

        let showHideNewPassword = document.getElementById("show_hide_new_password");
        showHideNewPassword.addEventListener("click", function (event) {
            event.preventDefault();
            showPassword("new_password");
        });

        let showHideConfirmPassword = document.getElementById("show_hide_new_confirm_password");
        showHideConfirmPassword.addEventListener("click", function (event) {
            event.preventDefault();
            showPassword("new_confirm_password");
        });

        function showPassword(id) {
            let password = document.getElementById(id);

            if (password.type === "password") {
                password.type = "text";
                let icon = document.getElementsByClassName(id)[0];
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            } else {
                password.type = "password";
                let icon = document.getElementsByClassName(id)[0];
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            }
        }
    }
</script>