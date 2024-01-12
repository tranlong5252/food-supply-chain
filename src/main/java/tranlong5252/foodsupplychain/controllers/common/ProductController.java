package tranlong5252.foodsupplychain.controllers.common;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.ProductDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;
import tranlong5252.foodsupplychain.model.Product;
import tranlong5252.foodsupplychain.model.ProductCompany;
import tranlong5252.foodsupplychain.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Util.getAccount(req);
        if (account == null) {
            req.getSession().setAttribute("redirect", "Products");
            resp.sendRedirect("Login");
            return;
        }
        String action = req.getParameter("action");
        switch (action != null ? action : "") {
            case "addProduct":
                addProduct(req, resp);
                break;
            case "editProduct":
                editProduct(req, resp);
                break;
            case "deleteProduct":
                deleteProduct(req, resp);
                break;
            default:
                break;
        }
        if (req.getAttribute("error") != null) {
            req.getRequestDispatcher("Products").forward(req, resp);
        } else {
            resp.sendRedirect("Products");
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("productName").trim();
            String priceStr = req.getParameter("productPrice").trim();
            String quantityStr = req.getParameter("productQuantity").trim();

            if (name.isBlank()) {
                req.setAttribute("error", "Name is empty");
                return;
            }
            if (priceStr.isBlank()) {
                req.setAttribute("error", "Price is empty");
                return;
            }
            if (quantityStr.isBlank()) {
                req.setAttribute("error", "Quantity is empty");
                return;
            }

            Product product = new Product();
            product.setName(name);
            product.setPrice(Double.parseDouble(priceStr));
            product.setQuantity(Integer.parseInt(quantityStr));

            int id = ProductDao.getInstance().update(product);
            product.setId(id);

            Account account = Util.getAccount(req);
            if (account != null && account.getRole() == 0) {
                ClientCompany clientCompany = ClientCompanyDao.getInstance().getByUser(account);
                ProductDao.getInstance().addProductToCompany(product, clientCompany);
            }

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
    }


}
