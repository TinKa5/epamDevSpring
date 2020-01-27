package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;
import epam.ua.javacore.util.jdbc.JDBCMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToObject;
import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToStatement;
import static epam.ua.javacore.util.jdbc.JDBCUtil.getLastInsertId;

public abstract class JdbcGeneric<T> implements GenericRepository<T,Long> {
    private final String sqlSelectAll=null;
    private final String sqlSelectID=null;
    private final String sqlAdd=null;
    private final String sqlDelete=null;



    @Override
    public Collection<T> getAll() {
        Collection<T> result=null;
        try {
            Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(sqlSelectAll);
            result=mapToObject(this,statement.executeQuery());



        }catch (SQLException e){
            e.printStackTrace();
        }
        return result.isEmpty()?result:null;
    }


    @Override
    public T get(Long id) {
        try {
            Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(sqlSelectID);
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
        try {
            Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(sqlAdd);
            mapToStatement(statement,t);
            statement.executeUpdate();
            Long id=getLastInsertId(connection.createStatement());
            return get(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement=connection.prepareStatement(sqlDelete);
            statement.setLong(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
