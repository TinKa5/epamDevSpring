package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Account;

public class JdbcAccountRepository extends JdbcGeneric<Account> {

    private final String sqlSelectAll="SELECT * FROM account";
    private final String sqlSelectID="SELECT * FROM account WHERE id=?";
    private final String sqlAdd="INSERT INTO account (content, status) VALUES (?,?)";
    private final String sqlDelete="DELETE FROM account WHERE id=?";



}
