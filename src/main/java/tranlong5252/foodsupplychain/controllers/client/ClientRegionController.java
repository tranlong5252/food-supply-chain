package tranlong5252.foodsupplychain.controllers.client;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ClientRegionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //try {
        Account account = Util.getAccount(req);
        if (account == null) {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Regions");
            resp.sendRedirect("Login");
            return;
        }
        if (account.getRole() == 1) {
            resp.sendRedirect("Regions");
            return;
        }

        ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
        req.setAttribute("company", clientCompany);
        req.setAttribute("region", clientCompany.getRegion());
        req.getRequestDispatcher("client/region.jsp").forward(req, resp);
        //} catch (Exception e) {
        //    req.setAttribute("error", e.getMessage());
        //}
    }
}

