package epam.ua.javacore.util.jdbc;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;

import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.repository.jdbc.JdbcDeveloperRepository;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class JDBCMapper<T>{

    public  static Collection mapToObject(GenericRepository repository, ResultSet resultSet)throws SQLException{
        Collection result=null;
        if (repository instanceof JdbcSkillRepository){
            result=mapToSkill(resultSet);
        }else if (repository instanceof JdbcDeveloperRepository){
                result=mapToDeveloper(resultSet);
        }else if (repository instanceof )
        return result;
    }

    public  static Collection mapToSkill(ResultSet resultSet)throws SQLException{
        Set<Skill> set=new HashSet<>();
        while (resultSet.next()){
            Long id=resultSet.getLong("id");
            Skill skill=new Skill();
            skill.setId(id);
            skill.setName(resultSet.getString("name"));
            set.add(skill);
        }
        return set;
    }


    public static Collection mapToDeveloper(ResultSet resultSet)throws SQLException{
        Map<Long, Developer> developer=new HashMap<>();
        Map<Long,Skill> skill=new HashMap<>();
        Map<Long, Account> account=new HashMap<>();
        while (resultSet.next()){
            Long id=resultSet.getLong("id");
            Developer developer=new Developer();
            skill.setId(id);
            skill.setName(resultSet.getString("name"));
            map.putIfAbsent(id,skill);
        }
        return map.values();

        return null;
    }


    public static <T> void mapToStatement(PreparedStatement statement, T t) throws SQLException{
        if (t instanceof Skill){
            SkillToStatement(statement,(Skill) t);
        }else if(t instanceof Developer){
            DeveloperToStatement(statement,(Developer)t);
        }
    }

    private static void SkillToStatement(PreparedStatement statement, Skill skill ) throws SQLException{
        statement.setString(1,skill.getName());
    }


    private static void DeveloperToStatement(PreparedStatement statement, Developer developer ) throws SQLException{
        statement.setString(1,developer.getName());
        statement.setLong(2,developer.getAccount().getId());
    }






}
