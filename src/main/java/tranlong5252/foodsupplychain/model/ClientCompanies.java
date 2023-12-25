package tranlong5252.foodsupplychain.model;

import java.util.ArrayList;

public class ClientCompanies extends ArrayList<ClientCompany> {

    public ClientCompany getById(int id) {
        for (ClientCompany company : this) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    public void setById(int id, ClientCompany company) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id) {
                this.set(i, company);
            }
        }
    }

    public void removeById(int id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId() == id) {
                this.remove(i);
                break;
            }
        }
    }
}
