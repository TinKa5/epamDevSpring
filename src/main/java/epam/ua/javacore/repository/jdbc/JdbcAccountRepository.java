package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.repository.AccountRepository;

public class JdbcAccountRepository implements AccountRepository, JdbcGeneric<Account> {

    private final String sqlSelectAll="SELECT * FROM account";
    private final String sqlSelectID="SELECT * FROM account WHERE id=?";
    private final String sqlAdd="INSERT INTO account (content, status) VALUES (?,?)";
    private final String sqlDelete="DELETE FROM account WHERE id=?";
    private final String sqlMax="SELECT MAX(id) AS max FROM account";

    @Override
    public String getSqlMax() {
        return sqlMax;
    }

    @Override
    public String getSqlSelectAll() {
        return sqlSelectAll;
    }

    @Override
    public String getSqlSelectID() {
        return sqlSelectID;
    }

    @Override
    public String getSqlAdd() {
        return sqlAdd;
    }

    @Override
    public String getSqlDelete() {
        return sqlDelete;
    }

}
