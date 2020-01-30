package epam.ua.javacore.service;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.AccountRepository;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.AccountRepositoryImpl;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
import epam.ua.javacore.repository.jdbc.JdbcDeveloperRepository;
import epam.ua.javacore.repository.jdbc.JdbcGeneric;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class DeveloperService {
    JdbcDeveloperRepository repository=new JdbcDeveloperRepository();
    JdbcSkillRepository skillRepository=new JdbcSkillRepository();
    JdbcAccountRepository accountRepository=new JdbcAccountRepository();

    public Collection<Developer> getAll(){
        return repository.getAll();
    }

    public Developer get(Long id){
        return repository.get(id);
    }

    public Developer add(Developer developer){
        return repository.add(developer);
    }

    public void delete(Long id){
        repository.delete(id);

    }
}
