package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.util.jdbc.JDBCConnectionPool;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static epam.ua.javacore.repository.jdbc.UtilTest.ChangePropertyToTest;
import static epam.ua.javacore.repository.jdbc.UtilTest.ChangePropertyToWork;
import static epam.ua.javacore.repository.jdbc.UtilTest.populateDB;

public class JdbcSkillRepositoryTest {

    Connection connection;

    @BeforeClass
    public void initTestDB()throws SQLException {
        ChangePropertyToTest();
        this.connection=JDBCConnectionPool.getConnection();
    }

    @AfterClass
    public void restoreDB(){
        ChangePropertyToWork();
    }

    @Before
    public void setUp() throws Exception {
        populateDB(connection);
    }

    @Test
    public void getSqlSelectAll() {
    }

    @Test
    public void getSqlSelectID() {
    }
}