package tranlong5252.foodsupplychain.dao.impl;

import tranlong5252.foodsupplychain.model.ClientCompanies;
import tranlong5252.foodsupplychain.model.ClientCompany;

public class ClientCompanyDao {

    public static ClientCompany get(int id) {
        return getListCompanies().get(id);
    }

    private static ClientCompanies companies;

    public static ClientCompanies getListCompanies() {
        if (companies == null) {
            companies = new ClientCompanies();
        }
        return companies;
    }
}
