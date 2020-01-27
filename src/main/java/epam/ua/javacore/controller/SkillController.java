package epam.ua.javacore.controller;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.SkillRepositoryImpl;

import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class SkillController {
    SkillRepository repository=new SkillRepositoryImpl();

    public Stream<String> getAll(){
        return repository.getAll().stream().map(x->x.toString());

    }
    public String get(Long id){
        idValidation(id);
        return repository.get(id).toString();
    }

    public String add(String name){
        entityValidation(name);
        Skill skill=new Skill();
        skill.setName(name);
        return "Success adding "+repository.add(skill);
    }

    public String delete(Long id){
        idValidation(id);
        repository.delete(id);
        return "Success deleting";
    }
}
