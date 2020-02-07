package epam.ua.javacore.repository.jdbc;


import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import static epam.ua.javacore.repository.jdbc.UtilTest.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JdbcAccuntRepositoryTest {

    static Connection connection;
    JdbcAccountRepository repository=new JdbcAccountRepository();;

    Account entity1=new Account("romik@gmail.com", AccountStatus.valueOf("ACTIVE"));
    Account entity2=new Account("luka@gmail.com", AccountStatus.valueOf("NONACTIVE"));


    @BeforeClass
    public static void initTestDB(){
        ChangePropertyToTest();
        try {
            connection = JDBCConnectionPool.getConnection();
        }catch (SQLException e){
            ChangePropertyToWork();
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void restoreDB(){
        ChangePropertyToWork();
    }

    @Before
    public void setUp() {
        populateDB(connection);
    }

    @Test
    public void testGetAll() {
        Collection entities=repository.getAll();
        assertThat(3).isEqualTo(entities.size());
        assertThat(repository.getAll()).usingElementComparatorIgnoringFields("id").contains(entity1);
        assertThat(repository.getAll()).doesNotHaveDuplicates();

    }

    @Test
    public void testGetId() {
        assertThat(repository.get(2L)).isEqualToIgnoringGivenFields(entity1,"id");
    }

    @Test
    public void testGetIdNotExist() {
        assertThat(repository.get(100L)).isNull();
    }

    @Test
    public void testAdd(){
        assertThat(repository.getAll()).usingElementComparatorIgnoringFields("id").doesNotContain(entity2);
        repository.add(entity2);
        assertThat(repository.getAll()).usingElementComparatorIgnoringFields("id").contains(entity2);
    }

    @Test
    public void testDelete(){
        Collection<Account> allOld=repository.getAll();
        Account account=(Account) allOld.toArray()[0];
        assertThat(repository.delete(account.getId())).isTrue();
        assertThat(repository.getAll().size()).isEqualTo(allOld.size()-1);
        assertThat(repository.getAll()).doesNotContain(account);
    }

    @Test
    public void testDeleteNotExist(){
        Collection<Account> allOld=repository.getAll();
        Long maxid=allOld.stream().map(Account::getId).max(Long::compareTo).get();
        assertThat(repository.delete(maxid+1)).isFalse();
        assertThat(repository.getAll().size()).isEqualTo(allOld.size());
    }

}