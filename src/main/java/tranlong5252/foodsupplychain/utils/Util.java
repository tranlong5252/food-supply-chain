package tranlong5252.foodsupplychain.utils;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.model.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {
    public static Account getAccount(HttpServletRequest req) {
        Account account = null;
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accountId")) {
                int id = Integer.parseInt(cookie.getValue());
                account = AccountDao.getInstance().get(id);
                break;
            }
        }
        return account;
    }
}
