package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.constants.StatusLevel;
import tranlong5252.foodsupplychain.dao.impl.region.RegionDao;
import tranlong5252.foodsupplychain.model.ClientCompanies;
import tranlong5252.foodsupplychain.model.IndustrialAgriculturalStatus;
import tranlong5252.foodsupplychain.model.NatureStatus;
import tranlong5252.foodsupplychain.model.Population;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.model.RegionList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RegionController extends HttpServlet {

    private void addRegion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RegionList regions = (RegionList) session.getAttribute("regions");
        if (regions == null) {
            regions = RegionDao.getListRegions();
        }

        // Nho try catch
        try {
        int regionId = regions.isEmpty() ? 0 : regions.get(regions.size() - 1).getId() + 1;
        String name = req.getParameter("regionName");

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
        region.setId(regionId);
        region.setName(name);
        region.setStatuses(new ArrayList<>());
        region.setPopulation(population);
        region.setNatureStatus(natureStatus);

        regions.add(region);
        session.setAttribute("regions", regions);

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    private void editRegion(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        RegionList companies = (RegionList) session.getAttribute("regions");
        if (companies != null) {
            try {
                int index = Integer.parseInt(req.getParameter("regionId"));
                Region region = companies.getById(index);

                String name = req.getParameter("regionName");

                for (IndustrialAgriculturalStatus status : region.getStatuses()) {
                    String statusName = req.getParameter("statusName_" + status.getId());
                    status.setName(statusName);
                    StatusLevel level = StatusLevel.getByValue(Integer.parseInt(req.getParameter("statusLevel_" + status.getId())));
                    status.setLevel(level);
                    double value = Double.parseDouble(req.getParameter("statusValue_" + status.getId()));
                    status.setValue(value);
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

                session.setAttribute("regions", companies);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            // Bao loi
            req.setAttribute("error", "Không tìm thấy danh sách khu vực");
        }
    }

    private void deleteRegion(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        RegionList regions = (RegionList) session.getAttribute("regions");
        if (regions != null) {
            try {
                int id = Integer.parseInt(req.getParameter("regionId"));
                Region region = regions.getById(id);
                regions.remove(region);
                session.setAttribute("regions", regions);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            req.setAttribute("error", "Không tìm thấy danh sách khu vực");
        }
    }


    private void addRegionStatus(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        RegionList regions = (RegionList) session.getAttribute("regions");
        if (regions != null) {
            try {
                int id = Integer.parseInt(req.getParameter("regionId"));
                Region region = regions.getById(id);

                IndustrialAgriculturalStatus status = new IndustrialAgriculturalStatus();
                String name = req.getParameter("statusName");
                status.setName(name);
                StatusLevel level = StatusLevel.getByValue(Integer.parseInt(req.getParameter("statusLevel")));
                status.setLevel(level);
                double value = Double.parseDouble(req.getParameter("statusValue"));
                status.setValue(value);

                region.getStatuses().add(status);

                session.setAttribute("regions", regions);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            req.setAttribute("error", "Không tìm thấy danh sách khu vực");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void deleteRegionStatus(HttpServletRequest req, HttpServletResponse resp) {

    }
}
