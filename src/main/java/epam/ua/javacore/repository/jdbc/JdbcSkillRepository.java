package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Skill;


public class JdbcSkillRepository extends JdbcGeneric<Skill> {


    private final String sqlSelectAll="SELECT * FROM skill";
    private final String sqlSelectID="SELECT * FROM skill WHERE id=?";
    private final String sqlAdd="INSERT INTO skill (name) VALUES (?)";
    private final String sqlDelete="DELETE FROM skill WHERE id=?";

    private final String sqlMax="SELECT MAX(id) AS max FROM skill";

    @Override
    public String getSqlMax() {
        return sqlMax;
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
