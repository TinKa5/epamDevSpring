package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;

import epam.ua.javacore.repository.DeveloperRepository;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class JdbcDeveloperRepository implements DeveloperRepository, JdbcGeneric<Developer> {

    private final String sqlSelectAll = "SELECT dev.id, dev.name, ac.id," +
            "ac.content, ac.status, sk.id, sk.name" +
            " FROM developer dev" +
            " left JOIN skills_developers temp ON dev.id=temp.developer_id" +
            " left JOIN skill sk ON temp.skill_id=sk.id" +
            " left JOIN account ac ON dev.account_id=ac.id";

    private final String sqlSelectID = sqlSelectAll + " WHERE dev.id=?";

    private final String sqlAdd = "INSERT INTO developer (name, account_id) VALUES (?,?)";

    private final String sqlAddConnection = "INSERT INTO skills_developers (developer_id, skill_id) VALUES (?,?)";

    private final String sqlDelete = "DELETE FROM developer WHERE id=?";

    private final String sqlDeleteConnection = "DELETE FROM skills_developers WHERE developer_id=?";

    private final String sqlMax = "SELECT MAX(developer.id) AS max FROM developer";

    @Override
    public String getSqlMax() {
        return sqlMax;
    }


   @Override
    public Developer add(Developer developer) {

        Developer loadedDeveloper = JdbcGeneric.super.add(developer);

        if (loadedDeveloper == null) return null;
        Set<Skill> skills = developer.getSkillSet();
        for (Skill skill : skills) {
            try (Connection connection = JDBCConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sqlAddConnection)) {
                statement.setLong(1, loadedDeveloper.getId());
                statement.setLong(2, skill.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return get(loadedDeveloper.getId());
    }

/*
    @Override
    public boolean delete(Long id) {
        boolean result = JdbcGeneric.super.delete(id);

        if (result) {
            try (Connection connection = JDBCConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sqlDeleteConnection);) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
*/
    @Override
    public String getSqlSelectAll() {
        return sqlSelectAll;
    }

    @Override
    public String getSqlSelectID() {
        return sqlSelectID;
    }

    @Override
    public String getSqlAdd() {
        return sqlAdd;
    }

    @Override
    public String getSqlDelete() {
        return sqlDelete;
    }
}
