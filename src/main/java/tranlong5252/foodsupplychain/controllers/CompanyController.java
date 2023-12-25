package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.dao.impl.ClientCompanyDao;
import tranlong5252.foodsupplychain.dao.impl.region.RegionDao;
import tranlong5252.foodsupplychain.model.ClientCompanies;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Region;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompanyController extends HttpServlet {

    private void addClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ClientCompanies companies = (ClientCompanies) session.getAttribute("companies");
        if (companies == null) {
            companies = ClientCompanyDao.getListCompanies();
        }

        // Nho try catch
        try {
            int companyId = companies.isEmpty() ? 0 : companies.get(companies.size() - 1).getId() + 1;
            String name = req.getParameter("companyName");
            String tax = req.getParameter("companyTaxCode");
            Region region;
            try {
                region = RegionDao.getListRegions().getById(Integer.parseInt(req.getParameter("companyRegion")));
            } catch (Exception e) {
                req.setAttribute("error", "Region not found");
                return;
            }
            if (region == null) {
                req.setAttribute("error", "Region not found");
                return;
            }

            String specification = req.getParameter("companySpecification");

            ClientCompany company = new ClientCompany();
            company.setId(companyId);
            company.setName(name);
            company.setTaxCode(tax);
            company.setRegion(region);
            company.setSpecification(specification);

            companies.add(company);
            session.setAttribute("companies", companies);

//            ClientCompanyDao.getListCompanies().add(company);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

    }

    private void editClientCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ClientCompanies companies = (ClientCompanies) session.getAttribute("companies");
        if (companies != null) {
            try {
                int id = Integer.parseInt(req.getParameter("companyId"));
                ClientCompany company = companies.getById(id);

                String name = req.getParameter("companyName");
                String tax = req.getParameter("companyTaxCode");
                Region region = RegionDao.getListRegions().get(Integer.parseInt(req.getParameter("companyRegion")));
                String specification = req.getParameter("companySpecification");

                company.setName(name);
                company.setTaxCode(tax);
                company.setRegion(region);
                company.setSpecification(specification);

                companies.setById(id, company);
                session.setAttribute("companies", companies);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            req.setAttribute("error", "Company not found");
        }
    }

    private void deleteClientCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ClientCompanies companies = (ClientCompanies) session.getAttribute("companies");
        if (companies != null) {
            try {
                int id = Integer.parseInt(req.getParameter("companyId"));
                companies.removeById(id);
                //ClientCompanyDao.getListCompanies().remove(index);

                session.setAttribute("companies", companies);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            // Bao loi
            req.setAttribute("error", "Company not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addCompany".equals(action)) {
            addClientCompany(req, resp);
        } else if ("editCompany".equals(action)) {
            editClientCompany(req, resp);
        } else if ("deleteCompany".equals(action)) {
            deleteClientCompany(req, resp);
        }
        if (req.getAttribute("error") != null) {
            req.getRequestDispatcher("Companies").forward(req, resp);
        } else {
            resp.sendRedirect("Companies");
        }
    }
}

