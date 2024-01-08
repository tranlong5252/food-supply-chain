package tranlong5252.foodsupplychain.controllers.common;

import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            req.getRequestDispatcher("account.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Account");
            resp.sendRedirect("Login");
        }
    }
}
