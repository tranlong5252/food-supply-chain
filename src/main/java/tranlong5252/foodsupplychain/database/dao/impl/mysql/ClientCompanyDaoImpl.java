package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.ClientCompany;

import java.sql.ResultSet;
import java.util.List;

public class ClientCompanyDaoImpl implements ClientCompanyDao {

    private ClientCompany newCompany(int id, String name, String taxCode, String specification, int regionId, int accountId) {
        ClientCompany company = new ClientCompany();
        company.setId(id);
        company.setName(name);
        company.setTaxCode(taxCode);
        company.setSpecification(specification);
        company.setRegion(RegionDao.getInstance().get(regionId));
        company.setAccount(AccountDao.getInstance().get(accountId));

        return company;
    }

    @Override
    public List<ClientCompany> getList() {
        return statement("SELECT * FROM client_company",
                statement -> fetchRecords(statement, resultSet -> newCompany(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("tax_code"),
                        resultSet.getString("specification"),
                        resultSet.getInt("region_id"),
                        resultSet.getInt("account_id")
                ))
        );
    }

    @Override
    public List<ClientCompany> getListByPage(int page) {
        //1 page = 50 entries
        int from = (page - 1) * 50;
        int to = page * 50;
        return statement("SELECT * FROM client_company LIMIT ?, ?", statement -> {
            statement.setInt(1, from);
            statement.setInt(2, to);
            return fetchRecords(statement, resultSet -> newCompany(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("tax_code"),
                    resultSet.getString("specification"),
                    resultSet.getInt("region_id"),
                    resultSet.getInt("account_id")
            ));
        });
    }

    @Override
    public ClientCompany get(int id) {
        return statement("SELECT * FROM client_company WHERE id = ?", statement -> {
            statement.setInt(1, id);
            return fetch(statement, resultSet -> newCompany(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("tax_code"),
                    resultSet.getString("specification"),
                    resultSet.getInt("region_id"),
                    resultSet.getInt("account_id")
            ));
        });
    }

    @Override
    public int update(ClientCompany company) {
        if (company.getId() != 0) {
            statement("UPDATE client_company SET name = ?, tax_code = ?, specification = ?, region_id = ?, account_id = ? WHERE id = ?", statement -> {
                statement.setString(1, company.getName());
                statement.setString(2, company.getTaxCode());
                statement.setString(3, company.getSpecification());
                statement.setInt(4, company.getRegion().getId());
                statement.setInt(5, company.getAccount().getId());
                statement.setInt(6, company.getId());
                return statement.executeUpdate();
            });
            return company.getId();
        }
        return statementWithKey("INSERT INTO client_company (name, tax_code, region_id, specification) VALUES (?, ?, ?, ?)", statement -> {
            statement.setString(1, company.getName());
            statement.setString(2, company.getTaxCode());
            statement.setInt(3, company.getRegion().getId());
            statement.setString(4, company.getSpecification());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
        });
    }

    @Override
    public void delete(ClientCompany company) {
        statement("DELETE FROM client_company WHERE id = ?", statement -> {
            statement.setInt(1, company.getId());
            statement.executeUpdate();
            return null;
        });
        AccountDao.getInstance().delete(company.getAccount());
    }

    @Override
    public List<ClientCompany> search(String name) {
        String stm = "SELECT * FROM client_company WHERE name LIKE CONCAT('%', ?, '%')";
        return statement(stm, statement -> {
            statement.setString(1, name);
            return fetchRecords(statement, resultSet -> newCompany(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("tax_code"),
                    resultSet.getString("specification"),
                    resultSet.getInt("region_id"),
                    resultSet.getInt("account_id")
            ));
        });
    }
}
