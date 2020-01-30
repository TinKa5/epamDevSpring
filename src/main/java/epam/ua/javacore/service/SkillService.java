package epam.ua.javacore.service;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.SkillRepositoryImpl;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;

import java.util.Collection;
import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class SkillService {
    JdbcSkillRepository repository=new JdbcSkillRepository();

    public Collection<Skill> getAll(){
        return repository.getAll();

    }
    public Skill get(Long id){
        return repository.get(id);
    }

    public Skill add(Skill skill){
        return repository.add(skill);
    }

    public void delete(Long id){
        repository.delete(id);

    }
}
