package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.ClientCompany;

import java.sql.ResultSet;
import java.util.List;

public class ClientCompanyDaoImpl implements ClientCompanyDao {

    private ClientCompany newCompany(int id, String name, String taxCode, String specification, int regionId) {
        ClientCompany company = new ClientCompany();
        company.setId(id);
        company.setName(name);
        company.setTaxCode(taxCode);
        company.setSpecification(specification);
        company.setRegion(RegionDao.getInstance().get(regionId));

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
                        resultSet.getInt("region_id")
                ))
        );
    }

    @Override
    public int add(ClientCompany obj) {
        return statementWithKey("INSERT INTO client_company (name, tax_code, region_id, specification) VALUES (?, ?, ?, ?)", statement -> {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getTaxCode());
            statement.setInt(3, obj.getRegion().getId());
            statement.setString(4, obj.getSpecification());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
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
                    resultSet.getInt("region_id")
            ));
        });
    }

    @Override
    public void update(ClientCompany company) {
        statement("UPDATE client_company SET name = ?, tax_code = ?, specification = ?, region_id = ? WHERE id = ?", statement -> {
            statement.setString(1, company.getName());
            statement.setString(2, company.getTaxCode());
            statement.setString(3, company.getSpecification());
            statement.setInt(4, company.getRegion().getId());
            statement.setInt(5, company.getId());
            statement.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(ClientCompany company) {
        statement("DELETE FROM client_company WHERE id = ?", statement -> {
            statement.setInt(1, company.getId());
            statement.executeUpdate();
            return null;
        });
    }
}
