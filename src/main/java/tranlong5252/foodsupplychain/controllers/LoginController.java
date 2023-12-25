package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.dao.impl.region.AccountDao;
import tranlong5252.foodsupplychain.model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Account account = AccountDao.login(username, password);
        if (account != null) {
            Cookie cookie = new Cookie("accountId", String.valueOf(account.getId()));
            resp.addCookie(cookie);
            resp.sendRedirect("Products");
        } else {
            req.setAttribute("error", "Wrong username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
