package epam.ua.javacore.service;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.SkillRepositoryImpl;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;

import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class SkillService {
    JdbcSkillRepository repository=new JdbcSkillRepository();

    public Stream<String> getAll(){
        return repository.getAll().stream().map(x->x.toString());

    }
    public String get(Long id){
        return repository.get(id).toString();
    }

    public String add(String name){
        Skill skill=new Skill();
        skill.setName(name);
        return repository.add(skill).toString();
    }

    public void delete(Long id){
        repository.delete(id);

    }
}
