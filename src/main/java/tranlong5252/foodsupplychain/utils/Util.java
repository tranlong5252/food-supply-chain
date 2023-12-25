package tranlong5252.foodsupplychain.utils;

import tranlong5252.foodsupplychain.constants.StatusLevel;
import tranlong5252.foodsupplychain.model.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static tranlong5252.foodsupplychain.dao.impl.region.AccountDao.getById;

public class Util {
    public static Account getAccount(HttpServletRequest req) {
        Account account = null;
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return account;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accountId")) {
                int id = Integer.parseInt(cookie.getValue());
                account = getById(id);
                break;
            }
        }
        return account;
    }
}
