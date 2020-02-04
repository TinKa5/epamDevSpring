package epam.ua.javacore.repository.jdbc;

import jdk.nashorn.internal.objects.annotations.Property;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class UtilTest {
    private static String workProperty;
    private static String propertyPathTest="./src/test/resources/db.properties";
    private static String propertyPathWork="./src/main/resources/db.properties";

    private static String initDBScript="./src/test/resources/db/initDB.sql";
    private static String populateDBScript="./src/test/resources/db/populateDB.sql";


    public static void ChangePropertyToTest (){

        workProperty=readToString(propertyPathWork);

        try(FileInputStream is=new FileInputStream(propertyPathTest);
        FileOutputStream os=new FileOutputStream(propertyPathWork)){
            int i=-1;
            while ((i=is.read())!=-1){
                os.write(i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void ChangePropertyToWork (){

        try(FileOutputStream os=new FileOutputStream(propertyPathWork)){
            byte[] buffer=workProperty.getBytes();
            os.write(buffer,0,buffer.length);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void populateDB(Connection connection){
        String initDB=readToString(initDBScript);
        String populateDB=readToString(populateDBScript);

        try {
            Statement statement = connection.createStatement();
            statement.execute(initDB);
            statement.execute(populateDB);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    private static String readToString(String file){
        StringBuilder stringBuilder=new StringBuilder();

        try(FileInputStream is=new FileInputStream(file)){
            int i=-1;
            while ((i=is.read())!=-1){
                stringBuilder.append(i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }




}
