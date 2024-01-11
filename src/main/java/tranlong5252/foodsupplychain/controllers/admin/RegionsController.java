package tranlong5252.foodsupplychain.controllers.admin;

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
        if (account != null && account.getRole() == 1) {
            int page = getPage(req);
            req.setAttribute("page", page);
            req.setAttribute("maxPage", getMaxPage());

            String action = req.getParameter("action");
            switch (action != null ? action : "") {
                case "searchRegions":
                    searchRegions(req, resp);
                    break;
                case "filterRegions":
                    filterRegions(req, resp);
                    break;
                case "resetRegions":
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
                case "resetRegions":
                    resp.sendRedirect("Regions");
                    break;
                //case "filterRegions":

                default:
                    req.getRequestDispatcher("admin/regions.jsp").forward(req, resp);
                    break;
            }
        }
        if (account != null && account.getRole() == 0) {
            resp.sendRedirect("ClientRegion");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Regions");
            resp.sendRedirect("Login");
        }
    }

    private void searchRegions(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("regionName");
        req.setAttribute("regions", RegionDao.getInstance().search(name, getPage(req)));
        req.setAttribute("maxPage", RegionDao.getInstance().countSearch(name) / 10 + 1);
    }


    private void filterRegions(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String popDisMin = req.getParameter("regionPopDisMin");
        String popDisMax = req.getParameter("regionPopDisMax");
        String popMigMin = req.getParameter("regionPopMigMin");
        String popMigMax = req.getParameter("regionPopMigMax");
        String popUrbMin = req.getParameter("regionPopUrbMin");
        String popUrbMax = req.getParameter("regionPopUrbMax");
        String natAgriMin = req.getParameter("regionNatAgriMin");
        String natAgriMax = req.getParameter("regionNatAgriMax");
        String natForMin = req.getParameter("regionNatForMin");
        String natForMax = req.getParameter("regionNatForMax");
        String natDis = req.getParameter("regionNatDis");

        double popDisMinValue = 0;
        double popDisMaxValue = Double.MAX_VALUE;
        int popMigMinValue = 1;
        int popMigMaxValue = 10;
        int popUrbMinValue = 1;
        int popUrbMaxValue = 10;
        double natAgriMinValue = 0;
        double natAgriMaxValue = 100;
        double natForMinValue = 0;
        double natForMaxValue = 100;

        if (popDisMin != null && !popDisMin.isEmpty()) {
            popDisMinValue = Double.parseDouble(popDisMin);
        }
        if (popDisMax != null && !popDisMax.isEmpty()) {
            popDisMaxValue = Double.parseDouble(popDisMax);
        }
        if (popMigMin != null && !popMigMin.isEmpty()) {
            popMigMinValue = Integer.parseInt(popMigMin);
        }
        if (popMigMax != null && !popMigMax.isEmpty()) {
            popMigMaxValue = Integer.parseInt(popMigMax);
        }
        if (popUrbMin != null && !popUrbMin.isEmpty()) {
            popUrbMinValue = Integer.parseInt(popUrbMin);
        }
        if (popUrbMax != null && !popUrbMax.isEmpty()) {
            popUrbMaxValue = Integer.parseInt(popUrbMax);
        }
        if (natAgriMin != null && !natAgriMin.isEmpty()) {
            natAgriMinValue = Double.parseDouble(natAgriMin);
        }
        if (natAgriMax != null && !natAgriMax.isEmpty()) {
            natAgriMaxValue = Double.parseDouble(natAgriMax);
        }
        if (natForMin != null && !natForMin.isEmpty()) {
            natForMinValue = Double.parseDouble(natForMin);
        }

        if (popDisMinValue > popDisMaxValue) {
            double temp = popDisMinValue;
            popDisMinValue = popDisMaxValue;
            popDisMaxValue = temp;
        }
        if (popMigMinValue > popMigMaxValue) {
            int temp = popMigMinValue;
            popMigMinValue = popMigMaxValue;
            popMigMaxValue = temp;
        }
        if (popUrbMinValue > popUrbMaxValue) {
            int temp = popUrbMinValue;
            popUrbMinValue = popUrbMaxValue;
            popUrbMaxValue = temp;
        }
        if (natAgriMinValue > natAgriMaxValue) {
            double temp = natAgriMinValue;
            natAgriMinValue = natAgriMaxValue;
            natAgriMaxValue = temp;
        }
        if (natForMinValue > natForMaxValue) {
            double temp = natForMinValue;
            natForMinValue = natForMaxValue;
            natForMaxValue = temp;
        }

        var regions = RegionDao.getInstance().filter(getPage(req),
                popDisMinValue, popDisMaxValue, popMigMinValue, popMigMaxValue, popUrbMinValue, popUrbMaxValue,
                natAgriMinValue, natAgriMaxValue, natForMinValue, natForMaxValue, natDis
        );

        req.setAttribute("regionPopDisMin", popDisMin);
        req.setAttribute("regionPopDisMax", popDisMax);
        req.setAttribute("regionPopMigMin", popMigMin);
        req.setAttribute("regionPopMigMax", popMigMax);
        req.setAttribute("regionPopUrbMin", popUrbMin);
        req.setAttribute("regionPopUrbMax", popUrbMax);
        req.setAttribute("regionNatAgriMin", natAgriMin);
        req.setAttribute("regionNatAgriMax", natAgriMax);
        req.setAttribute("regionNatForMin", natForMin);
        req.setAttribute("regionNatForMax", natForMax);
        req.setAttribute("regionNatDis", natDis);
        req.setAttribute("regions", regions);
    }

    private int getPage(HttpServletRequest req) {
        int page = 1;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
        }
        return page;
    }

    private int getMaxPage() {
        int count = RegionDao.getInstance().count();
        return count / 10 + (count % 10 == 0 ? 0 : 1);
    }
}
