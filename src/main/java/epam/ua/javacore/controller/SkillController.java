package epam.ua.javacore.controller;

import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;
import epam.ua.javacore.repository.io.SkillRepositoryImpl;
import epam.ua.javacore.service.SkillService;

import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class SkillController {
    SkillService service=new SkillService();

    public Stream<String> getAll(){
        return service.getAll();

    }
    public String get(Long id){
        idValidation(id);
        return service.get(id);
    }

    public String add(String name){
        entityValidation(name);
        return "Success adding "+service.add(name);
    }

    public String delete(Long id){
        idValidation(id);
        service.delete(id);
        return "Success deleting";
    }
}
