package tranlong5252.foodsupplychain.controllers.admin;

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
        if (account != null && account.getRole() == 1) {
            int page = getPage(req);
            req.setAttribute("page", page);
            req.setAttribute("maxPage", getMaxPage());

            String action = req.getParameter("action");
            switch (action != null ? action : "") {
                case "searchCompanies":
                    searchCompanies(req, resp);
                    break;
                default:
                    req.setAttribute("companies", ClientCompanyDao.getInstance().getListByPage(page));
                    break;
            }

            List<Region> regions = RegionDao.getInstance().getList();
            req.setAttribute("regions", regions);

            req.getRequestDispatcher("admin/companies.jsp").forward(req, resp);
            return;
        }
        if (account != null && account.getRole() == 0) {
            resp.sendRedirect("ClientCompany");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Companies");
            resp.sendRedirect("Login");
        }
    }

    private void searchCompanies(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("companyName");
        req.setAttribute("companies", ClientCompanyDao.getInstance().search(name, getPage(req)));
        req.setAttribute("maxPage", ClientCompanyDao.getInstance().countSearch(name) / 10 + 1);
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
        int count = ClientCompanyDao.getInstance().count();
        int maxPage = count / 10 + (count % 10 == 0 ? 0 : 1);
        return maxPage;
    }
}

