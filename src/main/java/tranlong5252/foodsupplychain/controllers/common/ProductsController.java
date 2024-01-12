package tranlong5252.foodsupplychain.controllers.common;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.ProductDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Product;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account != null) {
            int page = getPage(req);
            req.setAttribute("page", page);
            req.setAttribute("maxPage", getMaxPage());

            String action = req.getParameter("action");
            switch (action != null ? action : "") {
                case "searchProducts":
                    //searchCompanies(req, resp);
                    break;
                default:
                    req.setAttribute("products", getProduct(account, page));
                    break;
            }
            String role = account.getRole() == 0 ? "client" : "admin";
            req.getRequestDispatcher(role + "/products.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Products");
            resp.sendRedirect("Login");
        }
    }

    private List<Product> getProduct(Account account, int page) {
        if (account.getRole() == 1) {
            return ProductDao.getInstance().getListByPage(page);
        } else {
            ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
            return ProductDao.getInstance().getByCompany(clientCompany, page);
        }
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
        int count = ProductDao.getInstance().count();
        return count / 10 + (count % 10 == 0 ? 0 : 1);
    }
}
