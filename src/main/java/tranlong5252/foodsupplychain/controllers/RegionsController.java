package tranlong5252.foodsupplychain.controllers;

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

public class RegionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            String action = req.getParameter("action");
            switch (action != null ? action : "") {
                case "searchRegions":
                    searchRegions(req, resp);
                    break;
                default:
                    req.setAttribute("regions", RegionDao.getInstance().getList());
                    break;
            }
            switch (action != null ? action : "") {
                case "selectRegion":
                    int regionId = Integer.parseInt(req.getParameter("regionId"));
                    Region region = RegionDao.getInstance().get(regionId);
                    req.getSession().setAttribute("region", region);
                    resp.sendRedirect("Regions");
                    break;
                case "closeRegionStatus":
                    req.getSession().removeAttribute("region");
                    resp.sendRedirect("Regions");
                    break;
                default:
                    req.getRequestDispatcher("regions.jsp").forward(req, resp);
                    break;
            }

        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Regions");
            resp.sendRedirect("Login");
        }
    }

    private void searchRegions(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("regionName");
        req.setAttribute("regions", RegionDao.getInstance().search(name));
    }
}
