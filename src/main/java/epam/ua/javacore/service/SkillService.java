package epam.ua.javacore.service;

import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.GenericRepository;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import static epam.ua.javacore.util.Validate.checkNotFound;
import static epam.ua.javacore.util.Validate.checkNotFoundWithId;

@Service
public class SkillService {
    @Autowired
    @Qualifier(value = "skillRepository")
    GenericRepository repository;

    private static final Logger log = Logger.getLogger(SkillService.class);


    public Collection<Skill> getAll(){
        log.info("getAll in Service");
        return repository.getAll();

    }
    public Skill get(Long id)throws NotFoundException {
        log.info("getId in Service");
        return checkNotFoundWithId((Skill)repository.get(id),id);

    }

    public Skill add(Skill skill) throws NotFoundException {
        log.info("add in Service");
        return checkNotFound((Skill)repository.add(skill));

    }
    public void delete(Long id) throws NotFoundException {
        log.info("delete in Service");
        checkNotFoundWithId(repository.delete(id),id);

    }
}
