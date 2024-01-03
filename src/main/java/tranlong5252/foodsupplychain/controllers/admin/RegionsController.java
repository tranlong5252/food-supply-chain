package tranlong5252.foodsupplychain.controllers.admin;

import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.RegionList;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
        RegionList regions = new RegionList(RegionDao.getInstance().getList());
        if (popDisMin != null && !popDisMin.isEmpty()) {
            regions = regions.filterByPopulationDistributionMin(Double.parseDouble(popDisMin));
        }
        if (popDisMax != null && !popDisMax.isEmpty()) {
            regions = regions.filterByPopulationDistributionMax(Double.parseDouble(popDisMax));
        }
        if (popMigMin != null && !popMigMin.isEmpty()) {
            regions = regions.filterByPopulationMigrationMin(Integer.parseInt(popMigMin));
        }
        if (popMigMax != null && !popMigMax.isEmpty()) {
            regions = regions.filterByPopulationMigrationMax(Integer.parseInt(popMigMax));
        }
        if (popUrbMin != null && !popUrbMin.isEmpty()) {
            regions = regions.filterByPopulationUrbanizationMin(Integer.parseInt(popUrbMin));
        }
        if (popUrbMax != null && !popUrbMax.isEmpty()) {
            regions = regions.filterByPopulationUrbanizationMax(Integer.parseInt(popUrbMax));
        }
        if (natAgriMin != null && !natAgriMin.isEmpty()) {
            regions = regions.filterByNatureAgricultureLandMin(Double.parseDouble(natAgriMin));
        }
        if (natAgriMax != null && !natAgriMax.isEmpty()) {
            regions = regions.filterByNatureAgricultureLandMax(Double.parseDouble(natAgriMax));
        }
        if (natForMin != null && !natForMin.isEmpty()) {
            regions = regions.filterByNatureForestLandMin(Double.parseDouble(natForMin));
        }
        if (natForMax != null && !natForMax.isEmpty()) {
            regions = regions.filterByNatureForestLandMax(Double.parseDouble(natForMax));
        }
        if (natDis != null && !natDis.isEmpty()) {
            regions = regions.filterByNatureDisaster(natDis);
        }
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
        req.setAttribute("regions", new ArrayList<>(regions));
    }
}
