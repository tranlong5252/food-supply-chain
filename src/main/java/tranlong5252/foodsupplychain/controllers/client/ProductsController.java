package tranlong5252.foodsupplychain.controllers.client;

import tranlong5252.foodsupplychain.database.dao.ProductDao;
import tranlong5252.foodsupplychain.model.Account;
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
            List<Product> products = ProductDao.getListProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("products.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("redirect", "Products");
            resp.sendRedirect("Login");
        }
    }

}
