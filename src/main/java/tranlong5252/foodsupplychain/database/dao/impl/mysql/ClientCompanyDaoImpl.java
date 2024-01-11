package tranlong5252.foodsupplychain.database.dao.impl.mysql;

import tranlong5252.foodsupplychain.database.dao.AccountDao;
import tranlong5252.foodsupplychain.database.dao.ClientCompanyDao;
import tranlong5252.foodsupplychain.database.dao.RegionDao;
import tranlong5252.foodsupplychain.model.Account;
import tranlong5252.foodsupplychain.model.ClientCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String stm = """
                SELECT id,name,tax_code,specification,account_id,region_id
                FROM client_company
                    JOIN client_account ac
                        ON client_company.id = ac.company_id
                    LEFT OUTER JOIN food_supply_chain.company_region cr
                        ON client_company.id = cr.company_id
                """;
        return statement(stm,
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
        int from = (page - 1) * 10;
        int to = page * 10;
        String stm = """
                SELECT id,name,tax_code,specification,account_id,region_id
                FROM client_company
                    JOIN client_account ac
                        ON client_company.id = ac.company_id
                    LEFT OUTER JOIN food_supply_chain.company_region cr
                        ON client_company.id = cr.company_id
                LIMIT ?, ?
                """;
        return statement(stm, statement -> {
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
        String stm = """
                SELECT id,name,tax_code,specification,account_id,region_id
                FROM client_company
                    JOIN client_account ac
                        ON client_company.id = ac.company_id
                    LEFT OUTER JOIN food_supply_chain.company_region cr
                        ON client_company.id = cr.company_id
                WHERE id = ?
                """;
        return statement(stm, statement -> {
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
            String stm = "UPDATE client_company SET name = ?, tax_code = ?, specification = ? WHERE id = ?";
            statement(stm, statement -> {
                statement.setString(1, company.getName());
                statement.setString(2, company.getTaxCode());
                statement.setString(3, company.getSpecification());
                statement.setInt(4, company.getId());
                return statement.executeUpdate();
            });
            if (company.getRegion() != null) {
                String regionStm = "INSERT INTO company_region (company_id, region_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE region_id = ?";
                statement(regionStm, statement -> {
                    statement.setInt(1, company.getId());
                    statement.setInt(2, company.getRegion().getId());
                    statement.setInt(3, company.getRegion().getId());
                    return statement.executeUpdate();
                });
            }
            if (company.getAccount() != null) {
                String accountStm = "INSERT INTO client_account (company_id, account_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE account_id = ?";
                statement(accountStm, statement -> {
                    statement.setInt(1, company.getId());
                    statement.setInt(2, company.getAccount().getId());
                    statement.setInt(3, company.getAccount().getId());
                    return statement.executeUpdate();
                });
            }
            return company.getId();
        }
        String companyStm = """
                INSERT INTO client_company (name, tax_code, specification)
                VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, tax_code = ?, specification = ?
                """;
        int key = statementWithKey(companyStm, statement -> {
            statement.setString(1, company.getName());
            statement.setString(2, company.getTaxCode());
            statement.setInt(3, company.getRegion().getId());
            statement.setString(4, company.getSpecification());
            statement.setString(5, company.getName());
            statement.setString(6, company.getTaxCode());
            statement.setString(7, company.getSpecification());
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            return generatedKeysResultSet.getInt(1);
        });
        if (company.getRegion() != null) {
            String regionStm = """
                    INSERT INTO company_region (company_id, region_id)
                    VALUES (?, ?) ON DUPLICATE KEY UPDATE region_id = ?
                    """;
            statement(regionStm, statement -> {
                statement.setInt(1, key);
                statement.setInt(2, company.getRegion().getId());
                statement.setInt(3, company.getRegion().getId());
                return statement.executeUpdate();
            });
        }
        if (company.getAccount() != null) {
            String accountStm = """
                    INSERT INTO client_account (company_id, account_id)
                    VALUES (?, ?) ON DUPLICATE KEY UPDATE account_id = ?
                    """;
            statement(accountStm, statement -> {
                statement.setInt(1, key);
                statement.setInt(2, company.getAccount().getId());
                statement.setInt(3, company.getAccount().getId());
                return statement.executeUpdate();
            });
        }
        return key;
    }

    @Override
    public void delete(ClientCompany company) throws SQLException {
        String regionStm = "DELETE FROM company_region WHERE company_id = ?";
        statement(regionStm, statement -> {
            statement.setInt(1, company.getId());
            statement.executeUpdate();
            return null;
        });
        String accountStm = "DELETE FROM client_account WHERE company_id = ?";
        statement(accountStm, statement -> {
            statement.setInt(1, company.getId());
            statement.executeUpdate();
            return null;
        });
        String stm = "DELETE FROM client_company WHERE id = ?";
        statement(stm, statement -> {
            statement.setInt(1, company.getId());
            statement.executeUpdate();
            return null;
        });
        AccountDao.getInstance().delete(company.getAccount());
    }

    @Override
    public int count() {
        return statement("SELECT COUNT(*) FROM client_company", statement -> {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    @Override
    public int countSearch(String name) {
        return statement("SELECT COUNT(*) FROM client_company WHERE name LIKE CONCAT('%', ?, '%')", statement -> {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    @Override
    public List<ClientCompany> search(String name, int page) {
        int from = (page - 1) * 10;
        int to = page * 10;
        String stm = """
                SELECT id,name,tax_code,specification,account_id,region_id
                FROM client_company
                    JOIN client_account ac
                        ON client_company.id = ac.company_id
                    LEFT OUTER JOIN food_supply_chain.company_region cr
                        ON client_company.id = cr.company_id
                WHERE name LIKE CONCAT('%', ?, '%')
                LIMIT ?, ?
                """;
        return statement(stm, statement -> {
            statement.setString(1, name);
            statement.setInt(2, from);
            statement.setInt(3, to);
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
    public ClientCompany getByUser(Account account) {
        String stm = """
                SELECT id,name,tax_code,specification,account_id,region_id
                FROM client_company
                    JOIN client_account ac
                        ON client_company.id = ac.company_id
                    LEFT OUTER JOIN food_supply_chain.company_region cr
                        ON client_company.id = cr.company_id
                WHERE account_id = ?
                """;
        return statement(stm, statement -> {
            statement.setInt(1, account.getId());
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
}
