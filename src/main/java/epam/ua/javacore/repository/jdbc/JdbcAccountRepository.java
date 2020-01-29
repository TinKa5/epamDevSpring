package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Account;

public class JdbcAccountRepository extends JdbcGeneric<Account> {

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
    String getSqlSelectAll() {
        return sqlSelectAll;
    }

    @Override
    String getSqlSelectID() {
        return sqlSelectID;
    }

    @Override
    String getSqlAdd() {
        return sqlAdd;
    }

    @Override
    String getSqlDelete() {
        return sqlDelete;
    }

}
