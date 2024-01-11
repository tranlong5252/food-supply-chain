package tranlong5252.foodsupplychain.controllers.client;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ClientCompanyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = Util.getAccount(req);
        if (account == null) {
            req.getSession().setAttribute("redirect", "Companies");
            resp.sendRedirect("Login");
            return;
        }
        if (account.getRole() == 1) {
            resp.sendRedirect("Companies");
            return;
        }
        String action = req.getParameter("action");
        if ((action != null ? action : "").equals("updateCompany")) {
            if (editClientCompany(req, resp)) {
                req.setAttribute("message", "Updated company information!");
                List<Region> regions = RegionDao.getInstance().getList();
                req.setAttribute("regions", regions);
            }
        }
        try {
            req.getRequestDispatcher("client/company.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("ClientCompany");
        }
    }

    private boolean editClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Account account = Util.getAccount(req);
            if (account == null) {
                HttpSession session = req.getSession();
                session.setAttribute("redirect", "Companies");
                resp.sendRedirect("Login");
                return false;
            }
            if (account.getRole() == 1) {
                resp.sendRedirect("Companies");
                return false;
            }
            if (account.getRole() == 0) {
                ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
                clientCompany.setName(req.getParameter("name"));
                clientCompany.setTaxCode(req.getParameter("taxCode"));
                //do not change region by user
                //Region region = RegionDao.getInstance().get(Integer.parseInt(req.getParameter("region")));
                //clientCompany.setRegion(region);
                clientCompany.setSpecification(req.getParameter("specification"));
                //clientCompany.setRegion(req.getParameter("specification"));
                ClientCompanyDao.getInstance().update(clientCompany);
                req.setAttribute("company", clientCompany);
                return true;
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Account account = Util.getAccount(req);
            if (account == null) {
                HttpSession session = req.getSession();
                session.setAttribute("redirect", "Companies");
                resp.sendRedirect("Login");
                return;
            }
            if (account.getRole() == 1) {
                resp.sendRedirect("Companies");
                return;
            }

            ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
            req.setAttribute("company", clientCompany);
            List<Region> regions = RegionDao.getInstance().getList();
            req.setAttribute("regions", regions);
            req.getRequestDispatcher("client/company.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }
}

