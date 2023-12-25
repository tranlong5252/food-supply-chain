package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.dao.impl.ClientCompanyDao;
import tranlong5252.foodsupplychain.dao.impl.region.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompaniesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            if (req.getAttribute("companies") == null) {
                req.setAttribute("companies", ClientCompanyDao.getListCompanies());
            }

            List<Region> regions = RegionDao.getListRegions();
            req.setAttribute("regions", regions);

            req.getRequestDispatcher("companies.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("Login");
        }
    }
}

