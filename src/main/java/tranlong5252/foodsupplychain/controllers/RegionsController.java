package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.dao.impl.region.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.RegionList;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RegionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            RegionList regions = RegionDao.getListRegions();
            req.setAttribute("regions", regions);
            req.getRequestDispatcher("regions.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("Login");
        }
    }
}
