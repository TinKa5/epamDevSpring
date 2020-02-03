package epam.ua.javacore.util.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnectionPool {
    private static BasicDataSource bds=new BasicDataSource();
    private  static final String PROP_FILE= "./src/main/resources/db.properties";
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static int MAX_IDLE=10;
    private static int MIN_IDLE=5;
    private static int MAX_STATEMENT=100;



    static {
        initData();
        bds.setUrl(URL);
        bds.setUsername(USERNAME);
        bds.setPassword(PASSWORD);
        bds.setMaxIdle(MAX_IDLE);
        bds.setMinIdle(MIN_IDLE);
        bds.setMaxOpenPreparedStatements(MAX_STATEMENT);
        bds.setDriverClassName(DRIVER);
    }

    public static Connection getConnection() throws SQLException{

        return bds.getConnection();
    }

    private JDBCConnectionPool(){};

    private static void initData(){
        Properties properties=new Properties();
        try (FileInputStream file = new FileInputStream(PROP_FILE)){
            properties.load(file);
            }catch (IOException e){
            e.printStackTrace();
        }

        DRIVER=properties.getProperty("jdbc.driver");
        URL=properties.getProperty("jdbc.url");
        USERNAME=properties.getProperty("jdbc.username");
        PASSWORD=properties.getProperty("jdbc.password");
    }






}
