package epam.ua.javacore.service;

import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.jdbc.JdbcSkillRepository;
import org.apache.log4j.Logger;
import java.util.Collection;
import static epam.ua.javacore.util.Validate.checkNotFound;
import static epam.ua.javacore.util.Validate.checkNotFoundWithId;


public class SkillService {
    JdbcSkillRepository repository=new JdbcSkillRepository();

    private static final Logger log = Logger.getLogger(SkillService.class);


    public Collection<Skill> getAll(){
        log.info("getAll in Service");
        return repository.getAll();

    }
    public Skill get(Long id)throws NotFoundException {
        log.info("getId in Service");
        try{
            return checkNotFoundWithId(repository.get(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public Skill add(Skill skill) throws NotFoundException {
        log.info("add in Service");
        try{
            return checkNotFound(repository.add(skill));
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

    }
    public void delete(Long id) throws NotFoundException {
        log.info("delete in Service");
        try{
            checkNotFoundWithId(repository.delete(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

    }
}
