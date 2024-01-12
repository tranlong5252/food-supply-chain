package tranlong5252.foodsupplychain.controllers.admin;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Region;
import tranlong5252.foodsupplychain.utils.Encryption;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyController extends HttpServlet {

    private void addClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        // Nho try catch
        try {
            String name = req.getParameter("companyName").trim();
            String tax = req.getParameter("companyTaxCode").trim();
            Region region;
            try {
                region = RegionDao.getInstance().get(Integer.parseInt(req.getParameter("companyRegion")));
            } catch (Exception e) {
                req.setAttribute("error", "Region not found");
                return;
            }
            if (region == null) {
                req.setAttribute("error", "Region not found");
                return;
            }

            String specification = req.getParameter("companySpecification").trim();

            if (name.isBlank()) {
                req.setAttribute("error", "Name is empty");
                return;
            }
            if (tax.isBlank()) {
                req.setAttribute("error", "Tax code is empty");
                return;
            }
            if (specification.isBlank()) {
                req.setAttribute("error", "Specification is empty");
                return;
            }

            ClientCompany company = new ClientCompany();
            company.setName(name);
            company.setTaxCode(tax);
            company.setRegion(region);
            company.setSpecification(specification);

            int id = ClientCompanyDao.getInstance().update(company);
            company.setId(id);

            String accountName = name.split(" ")[0].toLowerCase();
            for (int i = 1; i < name.split(" ").length; i++) {
                accountName = accountName.concat(name.split(" ")[i].substring(0, 1).toUpperCase());
            }

            Account account = new Account();

            account.setUsername(Util.flattenToAscii(accountName.concat("_" + id)));
            account.setPassword(Encryption.encrypt(account.getUsername() + "_123@@123"));
            int accountId = AccountDao.getInstance().update(account);
            account.setId(accountId);

            company.setAccount(account);
            ClientCompanyDao.getInstance().update(company);

            //session.setAttribute("companies", companies);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

    }

    private void editClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("companyId"));
            ClientCompany company = ClientCompanyDao.getInstance().get(id);

            String name = req.getParameter("companyName");
            String tax = req.getParameter("companyTaxCode");
            Region region = RegionDao.getInstance().get(Integer.parseInt(req.getParameter("companyRegion")));
            String specification = req.getParameter("companySpecification");

            company.setName(name);
            company.setTaxCode(tax);
            company.setRegion(region);
            company.setSpecification(specification);

            ClientCompanyDao.getInstance().update(company);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    private void deleteClientCompany(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("companyId"));
            ClientCompany company = ClientCompanyDao.getInstance().get(id);
            if (company == null) {
                req.setAttribute("error", "Company not found");
                return;
            }
            ClientCompanyDao.getInstance().delete(company);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account == null) {
            req.getSession().setAttribute("redirect", "Companies");
            resp.sendRedirect("Login");
            return;
        }
        if (account.getRole() == 0) {
            resp.sendRedirect("Companies");
            return;
        }
        String action = req.getParameter("action");
        switch (action) {
            case "addCompany" -> addClientCompany(req, resp);
            case "editCompany" -> editClientCompany(req, resp);
            case "deleteCompany" -> deleteClientCompany(req, resp);
            case "reset" -> {
                req.getSession().removeAttribute("company");
                resp.sendRedirect("Companies");
            }
            case "searchCompany" -> {
                String name = req.getParameter("companyName");
                int page = 1;
                try {
                    page = Integer.parseInt(req.getParameter("page"));
                } catch (Exception e) {
                }
                req.setAttribute("companies", ClientCompanyDao.getInstance().search(name, page));
            }
        }
        if (req.getAttribute("error") != null) {
            req.getRequestDispatcher("Companies").forward(req, resp);
        } else {
            resp.sendRedirect("Companies");
        }
    }
}

