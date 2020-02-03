package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;


public class JdbcSkillRepository implements SkillRepository, JdbcGeneric<Skill> {


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
