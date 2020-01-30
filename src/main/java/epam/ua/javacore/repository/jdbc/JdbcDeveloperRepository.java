package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;

import epam.ua.javacore.util.jdbc.JDBCConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class JdbcDeveloperRepository extends JdbcGeneric<Developer> {

    private final String sqlSelectAll="SELECT dev.id, dev.name, account.id," +
            "account.content, account.status, skill.id, skill.name" +
            " FROM developer AS dev" +
            " left JOIN skills_developers AS temp ON dev.id=developer_id" +
            " left JOIN skill AS skill ON temp.skill_id=skill.id" +
            " left JOIN account AS account ON dev.account_id=account.id";

    private final String sqlSelectID=sqlSelectAll+" WHERE dev.id=?";

    private final String sqlAdd="INSERT INTO developer (name, account_id) VALUES (?,?)";

    private final String sqlAddConnection="INSERT INTO skills_developers (developer_id, skill_id) VALUES (?,?)";

    private final String sqlDelete="DELETE FROM developer WHERE id=?";

    private final String sqlDeleteConnection="DELETE FROM skills_developers WHERE developer_id=?";

    private final String sqlMax="SELECT MAX(developer.id) AS max FROM developer";

    @Override
    public String getSqlMax() {
        return sqlMax;
    }


    @Override
    public Developer add(Developer developer) {

        Developer loadedDeveloper = super.add(developer);

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



    @Override
    public boolean delete(Long id) {
        super.delete(id);

        try(Connection connection=JDBCConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlDeleteConnection);) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    String getSqlSelectAll() {
        return sqlSelectAll;
    }

    @Override
    String getSqlSelectID() {
        return sqlSelectID;
    }

    @Override
    String getSqlAdd() {
        return sqlAdd;
    }

    @Override
    String getSqlDelete() {
        return sqlDelete;
    }
}
