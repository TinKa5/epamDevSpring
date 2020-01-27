package epam.ua.javacore.repository.jdbc;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.util.jdbc.JDBCConnectionPool;
import java.sql.*;
import java.util.Collection;

import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToObject;
import static epam.ua.javacore.util.jdbc.JDBCMapper.mapToStatement;
import static epam.ua.javacore.util.jdbc.JDBCUtil.getLastInsertId;

public class JdbcSkillRepository extends JdbcGeneric<Skill> {


    private final String sqlSelectAll="SELECT * FROM skills";
    private final String sqlSelectID="SELECT * FROM skills WHERE id=?";
    private final String sqlAdd="INSERT INTO skills (name) VALUES (?)";
    private final String sqlDelete="DELETE FROM skills WHERE id=?";



}
