package tranlong5252.foodsupplychain.controllers.admin;

import tranlong5252.foodsupplychain.constants.StatusLevel;
import tranlong5252.foodsupplychain.database.dao.IndustrialStatusDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.IndustrialAgriculturalStatus;
import tranlong5252.foodsupplychain.model.NatureStatus;
import tranlong5252.foodsupplychain.model.Population;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.StatusList;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegionController extends HttpServlet {

    private void addRegion(HttpServletRequest req, HttpServletResponse resp) {
        //HttpSession session = req.getSession();
        //List<Region> regions = RegionDao.getInstance().getList();

        // Nho try catch
        try {
            String name = req.getParameter("regionName");
            if (name == null || name.isBlank()) {
                req.setAttribute("error", "Name is empty");
                return;
            }

            double distribution = Double.parseDouble(req.getParameter("regionPopDis"));
            int migration = Integer.parseInt(req.getParameter("regionPopMig"));
            int urbanization = Integer.parseInt(req.getParameter("regionPopUrb"));
            Population population = new Population();
            population.setDistribution(distribution);
            population.setMigration(migration);
            population.setUrbanization(urbanization);

            double agricultureLand = Double.parseDouble(req.getParameter("regionNatAgri"));
            double forestLand = Double.parseDouble(req.getParameter("regionNatFor"));
            String disaster = req.getParameter("regionNatDis");
            NatureStatus natureStatus = new NatureStatus();
            natureStatus.setAgricultureLand(agricultureLand);
            natureStatus.setForestLand(forestLand);
            natureStatus.setDisaster(disaster);

            Region region = new Region();
            region.setName(name);
            region.setStatuses(new StatusList());
            region.setPopulation(population);
            region.setNatureStatus(natureStatus);

            RegionDao.getInstance().update(region);
            //session.setAttribute("regions", regions);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    private void editRegion(HttpServletRequest req, HttpServletResponse resp) {
        //HttpSession session = req.getSession();
        try {
            int regionId = Integer.parseInt(req.getParameter("regionId"));
            Region region = RegionDao.getInstance().get(regionId);

            String name = req.getParameter("regionName");

            for (IndustrialAgriculturalStatus status : region.getStatuses()) {
                String statusName = req.getParameter("statusName_" + status.getId());
                status.setName(statusName);
                StatusLevel level = StatusLevel.getByValue(Integer.parseInt(req.getParameter("statusLevel_" + status.getId())));
                status.setLevel(level);
                double value = Double.parseDouble(req.getParameter("statusValue_" + status.getId()));
                status.setValue(value);
                int potential = Integer.parseInt(req.getParameter("statusPotential_" + status.getId()));
                status.setPotential(potential);
                int development = Integer.parseInt(req.getParameter("statusDevelopment_" + status.getId()));
                status.setDevelopment(development);
                IndustrialStatusDao.getInstance().update(status);
            }

            double distribution = Double.parseDouble(req.getParameter("regionPopDis"));
            int migration = Integer.parseInt(req.getParameter("regionPopMig"));
            int urbanization = Integer.parseInt(req.getParameter("regionPopUrb"));
            Population population = new Population();
            population.setDistribution(distribution);
            population.setMigration(migration);
            population.setUrbanization(urbanization);

            double agricultureLand = Double.parseDouble(req.getParameter("regionNatAgri"));
            double forestLand = Double.parseDouble(req.getParameter("regionNatFor"));
            String disaster = req.getParameter("regionNatDis");
            NatureStatus natureStatus = new NatureStatus();
            natureStatus.setAgricultureLand(agricultureLand);
            natureStatus.setForestLand(forestLand);
            natureStatus.setDisaster(disaster);

            region.setName(name);
            region.setPopulation(population);
            region.setNatureStatus(natureStatus);

            RegionDao.getInstance().update(region);

            //session.setAttribute("regions", regions);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    private void deleteRegion(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        try {
            int id = Integer.parseInt(req.getParameter("regionId"));
            RegionDao dao = RegionDao.getInstance();
            Region region = dao.get(id);
            try {
                dao.delete(region);
            } catch (SQLException e) {
                req.setAttribute("error", e.getMessage());
                return;
            }

            Region regionSession = (Region) session.getAttribute("region");
            if (regionSession != null && region.getId() == regionSession.getId()) {
                session.removeAttribute("region");
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

    }


    private void addRegionStatus(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        //List<Region> regions = RegionDao.getInstance().getList();
        //if (regions != null) {
        try {
            int regionId = Integer.parseInt(req.getParameter("regionId"));
            Region region = RegionDao.getInstance().get(regionId);
            IndustrialAgriculturalStatus status = new IndustrialAgriculturalStatus();
            String name = req.getParameter("statusName");
            status.setName(name);
            StatusLevel level = StatusLevel.getByValue(Integer.parseInt(req.getParameter("statusLevel")));
            status.setLevel(level);
            double value = Double.parseDouble(req.getParameter("statusValue"));
            status.setValue(value);

            int potential = Integer.parseInt(req.getParameter("statusPotential"));
            status.setPotential(potential);

            int development = Integer.parseInt(req.getParameter("statusDevelopment"));
            status.setDevelopment(development);
            int id = IndustrialStatusDao.getInstance().update(status);
            status.setId(id);
            region.getStatuses().add(status);
            RegionDao.getInstance().update(region);

            session.setAttribute("region", region);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        /*} else {
            req.setAttribute("error", "Không tìm thấy danh sách khu vực");
        }*/
    }

    private void deleteRegionStatus(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            int regionId = Integer.parseInt(req.getParameter("regionId"));
            Region region = RegionDao.getInstance().get(regionId);

            int statusId = Integer.parseInt(req.getParameter("statusId"));
            IndustrialAgriculturalStatus status = region.getStatuses().getById(statusId);
            if (status != null) {
                region.getStatuses().remove(status);
                IndustrialStatusDao.getInstance().delete(status);
                RegionDao.getInstance().update(region);
                session.setAttribute("region", region);
            } else {
                req.setAttribute("error", "Không tìm thấy trạng thái");
            }

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account == null) {
            req.getSession().setAttribute("redirect", "Regions");
            resp.sendRedirect("Login");
            return;
        }
        if (account.getRole() == 0) {
            resp.sendRedirect("Regions");
            return;
        }
        String action = req.getParameter("action");
        switch (action != null ? action : "") {
            case "addRegion":
                addRegion(req, resp);
                break;
            case "editRegion":
                editRegion(req, resp);
                break;
            case "deleteRegion":
                deleteRegion(req, resp);
                break;
            case "addRegionStatus":
                addRegionStatus(req, resp);
                break;
            case "deleteRegionStatus":
                deleteRegionStatus(req, resp);
                break;
            default:
                break;
        }
        if (req.getAttribute("error") != null) {
            req.getRequestDispatcher("Regions").forward(req, resp);
        } else {
            resp.sendRedirect("Regions");
        }
    }
}
