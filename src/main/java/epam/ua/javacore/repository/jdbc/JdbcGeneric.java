package epam.ua.javacore.repository.jdbc;


import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;

import java.sql.*;
import java.util.Collection;

import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToObject;
import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToStatement;


public abstract class JdbcGeneric<T> implements GenericRepository<T,Long> {

    abstract String getSqlSelectAll();
    abstract String getSqlSelectID();
    abstract String getSqlAdd();
    abstract String getSqlDelete();
    abstract String getSqlMax();


    @Override
    public Collection<T> getAll() {
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
    public T get(Long id) {
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
    public T add(T t) {

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
    public boolean delete(Long id) {
        try(Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(getSqlDelete())) {

            statement.setLong(1,id);
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    Long maxId(){

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
