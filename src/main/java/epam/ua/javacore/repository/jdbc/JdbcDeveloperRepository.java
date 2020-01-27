package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;

import epam.ua.javacore.util.jdbc.JDBCConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDeveloperRepository extends JdbcGeneric<Developer> {

    private final String sqlSelectAll="SELECT dev.id, dev.name, account.id," +
            "account.content, acstatus.status, skill.id, skill.name" +
            " FROM developers AS dev" +
            " JOIN skills_developers AS temp ON dev.id=developer_id" +
            " JOIN skills AS s ON temp.skill_id=s.id" +
            " JOIN account AS account ON dev.account_id=account.id" +
            " JOIN account_status AS acstatus ON account.status_id=acstatus.id";

    private final String sqlSelectID=sqlSelectAll+" WHERE dev.id=?";

    private final String sqlAdd="INSERT INTO developers (name, account_id) VALUES (?,?)";

    private final String sqlAddConnection="INSERT INTO developers (developer_id, account_id) VALUES (?,?)";

    private final String sqlDelete="DELETE FROM developers WHERE id=?";

    private final String sqlDeleteConnection="DELETE FROM skills_developers WHERE developer_id=?";



    @Override
    public Developer add(Developer developer) {
        Developer loadedDeveloper=super.add(developer);
        if (loadedDeveloper==null)return null;

        try {
            Connection connection=JDBCConnectionPool.getConnection();
            for (Skill skill:loadedDeveloper.getSkillSet()) {
                PreparedStatement statement = connection.prepareStatement(sqlAddConnection);
                statement.setLong(1, skill.getId());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return loadedDeveloper;
    }


    @Override
    public void delete(Long id) {
        super.delete(id);

        try {
            Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlDeleteConnection);
                statement.setLong(1, id);
                statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
