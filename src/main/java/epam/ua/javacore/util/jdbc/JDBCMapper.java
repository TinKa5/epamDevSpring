package epam.ua.javacore.util.jdbc;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;

import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
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
        }else if (repository instanceof JdbcAccountRepository){
            result=mapToAccount(resultSet);
        }
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

    public  static Collection mapToAccount(ResultSet resultSet)throws SQLException{
        Set<Account> set=new HashSet<>();
        while (resultSet.next()){
            Account account=new Account();
            account.setId(resultSet.getLong("id"));
            account.setContent(resultSet.getString("content"));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString("status")));
            set.add(account);
        }
        return set;
    }



    public static Collection mapToDeveloper(ResultSet resultSet)throws SQLException{
        Map<Long, Developer> developer=new HashMap<>();
        Map<Long,Skill> skill=new HashMap<>();
        Map<Long, Account> account=new HashMap<>();

        while (resultSet.next()) {

            Set skillset;
            Long devId = resultSet.getLong(1);
            if (!isExist(developer, devId)) {
                Developer dev = new Developer();
                developer.put(devId, dev);
                dev.setId(devId);
                dev.setName(resultSet.getString(2));
                ////add account
                Long accounId = resultSet.getLong(3);
                if (isExist(account, accounId)) {
                    dev.setAccount(account.get(accounId));
                } else {
                    Account acc = new Account();
                    acc.setId(accounId);
                    acc.setContent(resultSet.getString(4));

                    String status=resultSet.getString(5);
                    acc.setAccountStatus((status)==null?
                            null:
                            AccountStatus.valueOf(status));
                    dev.setAccount(acc);
                    account.put(accounId, acc);
                }////add skills
                skillset = new HashSet();
                dev.setSkillSet(skillset);
            } else {
                ///if developer exist add new skill in skillset
                skillset = developer.get(devId).getSkillSet();
            }

            Long skillId = resultSet.getLong(6);
            if (isExist(skill, skillId)) {
                skillset.add(skill.get(skillId));
            } else {
                Skill sk = new Skill();
                sk.setId(skillId);
                sk.setName(resultSet.getString(7));
                skillset.add(sk);
                skill.put(skillId, sk);
            }

        }

        return developer.values();
    }


    public static <T> void mapToStatement(PreparedStatement statement, T t) throws SQLException{
        if (t instanceof Skill){
            SkillToStatement(statement,(Skill) t);
        }else if(t instanceof Developer){
            DeveloperToStatement(statement,(Developer)t);
        }else if(t instanceof Account){
            AccountToStatement(statement,(Account)t);
        }
    }

    private static void SkillToStatement(PreparedStatement statement, Skill skill ) throws SQLException{
        statement.setString(1,skill.getName());
    }


    private static void DeveloperToStatement(PreparedStatement statement, Developer developer ) throws SQLException{
        statement.setString(1,developer.getName());
        statement.setLong(2,developer.getAccount().getId());
    }

    private static void AccountToStatement(PreparedStatement statement, Account account ) throws SQLException{
        statement.setString(1,account.getContent());
        statement.setString(2,account.getAccountStatus().toString());
    }


    private static boolean isExist(Map map,Long key) {
        if (map.isEmpty()) return false;
        if (map.get(key) == null) {
            return false;
        } else {
            return true;
        }
    }
}
