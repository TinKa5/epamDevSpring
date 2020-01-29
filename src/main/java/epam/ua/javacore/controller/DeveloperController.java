package epam.ua.javacore.controller;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.AccountRepository;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.AccountRepositoryImpl;
import epam.ua.javacore.repository.jdbc.JdbcDeveloperRepository;
import epam.ua.javacore.repository.jdbc.JdbcGeneric;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;
import epam.ua.javacore.service.DeveloperService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class DeveloperController {
    DeveloperService service=new DeveloperService();


    public Stream<String> getAll(){
        return service.getAll();

    }
    public String get(Long id){
        idValidation(id);
        return service.get(id);
    }

    public String add(String name, Set<Long> skillSetId,Long accountId){
        entityValidation(name);
        idValidation(skillSetId);
        idValidation(accountId);
        return "Success adding "+service.add(name,skillSetId,accountId);
    }

    public String delete(Long id){
        idValidation(id);
        service.delete(id);
        return "Success deleting";
    }
}
