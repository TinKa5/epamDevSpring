package epam.ua.javacore.repository.jdbc;


import epam.ua.javacore.annotation.Timed;
import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;

import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToObject;
import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToStatement;


public interface JdbcGeneric<T> extends GenericRepository<T,Long> {

    String getSqlSelectAll();
    String getSqlSelectID();
    String getSqlAdd();
    String getSqlDelete();
    String getSqlMax();


    @Override

    default public Collection<T> getAll() {
        try (Connection connection=JDBCConnectionPool.getConnection();
             PreparedStatement statement=connection.prepareStatement(getSqlSelectAll())){
            Collection<T> result=mapToObject(this,statement.executeQuery());
            return !result.isEmpty()?result:null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    default public T get(Long id) {
        try (Connection connection=JDBCConnectionPool.getConnection();
             PreparedStatement statement=connection.prepareStatement(getSqlSelectID());){
            statement.setLong(1,id);
            Collection<T> result=mapToObject(this,statement.executeQuery());
             if (!result.isEmpty()){
                 return result.stream().findFirst().get();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    default public T add(T t) {

        Long i=null;
        try(Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(getSqlAdd())) {
            mapToStatement(statement,t);
            statement.executeUpdate();
            i=maxId();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return get(i);

    }

    @Override
    default public boolean delete(Long id) {
        try(Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(getSqlDelete())) {
            statement.setLong(1,id);
            return statement.executeUpdate()==0?false:true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    default Long maxId(){

        try(Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(getSqlMax());) {

           ResultSet rs=statement.executeQuery();
            rs.first();
            Long id=rs.getLong("max");
            return id;


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
