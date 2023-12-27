package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CompaniesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            String action = req.getParameter("action");
            switch (action != null ? action : "") {
                case "searchCompanies":
                    searchCompanies(req, resp);
                    break;
                default:
                    req.setAttribute("companies", ClientCompanyDao.getInstance().getList());
                    break;
            }

            List<Region> regions = RegionDao.getInstance().getList();
            req.setAttribute("regions", regions);

            req.getRequestDispatcher("companies.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Companies");
            resp.sendRedirect("Login");
        }
    }

    private void searchCompanies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("companyName");
        req.setAttribute("companies", ClientCompanyDao.getInstance().search(name));
    }
}

