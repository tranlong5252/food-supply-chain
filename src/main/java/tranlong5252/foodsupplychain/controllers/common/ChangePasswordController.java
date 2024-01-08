package tranlong5252.foodsupplychain.controllers.common;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.utils.Encryption;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        String confirmPassword = req.getParameter("new_confirm_password");

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Confirm password not match");
        } else {
            Account account = Util.getAccount(req);
            if (!account.getPassword().equals(Encryption.encrypt(oldPassword))) {
                req.setAttribute("error", "Wrong old password");
            } else {
                account.setPassword(Encryption.encrypt(newPassword));
                AccountDao.getInstance().update(account);
                req.setAttribute("message", "Change password successfully");
            }
        }
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }
}
