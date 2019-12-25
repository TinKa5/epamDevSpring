package epam.ua.javacore.controller;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.AccountRepository;
import epam.ua.javacore.repository.DeveloperRepository;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.AccountRepositoryImpl;
import epam.ua.javacore.repository.io.DeveloperRepositoryImpl;
import epam.ua.javacore.repository.io.SkillRepositoryImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class DeveloperController {
    DeveloperRepository repository=new DeveloperRepositoryImpl();
    SkillRepository skillRepository=new SkillRepositoryImpl();
    AccountRepository accountRepository=new AccountRepositoryImpl();


    public Stream<String> getAll(){
        return repository.getAll().stream().map(x->x.toString());

    }
    public String get(Long id){
        idValidation(id);
        return repository.get(id).toString();
    }

    public String add(String name, Set<Long> skillSetId,Long accountId){
        entityValidation(name);
        idValidation(skillSetId);
        idValidation(accountId);
        Set<Skill> skillSet=new HashSet<>();
        for (Long k:skillSetId) {
            skillSet.add(skillRepository.get(k));
        }
        Account account=accountRepository.get(accountId);

        return "Success adding "+repository.add(new Developer(name,skillSet,account));
    }

    public String delete(Long id){
        idValidation(id);
        repository.delete(id);
        return "Success deleting";
    }
}
