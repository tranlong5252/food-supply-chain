package tranlong5252.foodsupplychain.controllers;

import tranlong5252.foodsupplychain.database.dao.ProductDao;
import tranlong5252.foodsupplychain.model.Cart;
import tranlong5252.foodsupplychain.model.CartProduct;
import tranlong5252.foodsupplychain.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartController extends HttpServlet {
    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        // Nho try catch
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        // Add to cart

        // Kiem tra coi product co ton tai k
        Product product = ProductDao.getProduct(productId);
        if (product != null) {

            // Kiem tra coi product co trong cart chua
            CartProduct cartProduct = null;
            int i = 0;
            for (CartProduct cp : cart) {
                if (cp.getProduct().getId() == productId) {
                    cartProduct = cp;
                    break;
                }
                i++;
            }

            if (cartProduct != null) {
                cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                cart.set(i, cartProduct);
            } else {
                cartProduct = new CartProduct(product, quantity);
                cart.add(cartProduct);
            }

        } else {
            // Bao loi

        }

        session.setAttribute("cart", cart);
        // -----------
    }

    private void updateCartProduct(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {

            int index = Integer.parseInt(req.getParameter("index"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            CartProduct cartProduct = cart.get(index);
            cartProduct.setQuantity(quantity);

            session.setAttribute("cart", cart);
        } else {
            // Bao loi

        }
    }

    private void removeCartProduct(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {

            int index = Integer.parseInt(req.getParameter("index"));
            cart.remove(index);

            session.setAttribute("cart", cart);
        } else {
            // Bao loi

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addtocart".equals(action)) {
            addToCart(req, resp);
        } else if ("updatecartproduct".equals(action)) {
            updateCartProduct(req, resp);
        } else if ("removecartproduct".equals(action)) {
            removeCartProduct(req, resp);
        }
        resp.sendRedirect("Products");
    }

}
