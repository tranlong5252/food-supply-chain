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

public class ClientCompanyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action != null ? action : "") {
            case "updateCompany":
                editClientCompany(req, resp);
                break;
        }
        resp.sendRedirect("ClientCompany");
    }

    private void editClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Account account = Util.getAccount(req);
            if (account != null && account.getRole() == 0) {
                ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
                clientCompany.setName(req.getParameter("name"));
                clientCompany.setTaxCode(req.getParameter("taxCode"));
                clientCompany.setSpecification(req.getParameter("specification"));
                //clientCompany.setRegion(req.getParameter("specification"));
                ClientCompanyDao.getInstance().update(clientCompany);
                req.setAttribute("company", clientCompany);
                return;
            }
            if (account != null && account.getRole() == 1) {
                resp.sendRedirect("Companies");
            }
            else {
                HttpSession session = req.getSession();
                session.setAttribute("redirect", "Companies");
                resp.sendRedirect("Login");
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Account account = Util.getAccount(req);
            if (account != null && account.getRole() == 1) {
                resp.sendRedirect("Companies");
            }
            if (account != null && account.getRole() == 0) {
                ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
                req.setAttribute("company", clientCompany);
                req.getRequestDispatcher("client/company.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("redirect", "Companies");
                resp.sendRedirect("Login");
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }
}

