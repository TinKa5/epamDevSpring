package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Skill;


public class JdbcSkillRepository extends JdbcGeneric<Skill> {


    private final String sqlSelectAll="SELECT * FROM skills";
    private final String sqlSelectID="SELECT * FROM skills WHERE id=?";
    private final String sqlAdd="INSERT INTO skills (name) VALUES (?)";
    private final String sqlDelete="DELETE FROM skills WHERE id=?";



}
