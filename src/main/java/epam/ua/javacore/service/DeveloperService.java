package epam.ua.javacore.service;


import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.repository.jdbc.JdbcDeveloperRepository;
import org.apache.log4j.Logger;
import java.util.Collection;
import static epam.ua.javacore.util.Validate.checkNotFound;
import static epam.ua.javacore.util.Validate.checkNotFoundWithId;

public class DeveloperService {
    JdbcDeveloperRepository repository=new JdbcDeveloperRepository();
    private static final Logger log = Logger.getLogger(DeveloperService.class);


    public Collection<Developer> getAll(){
        log.info("getAll in Service");
        return repository.getAll();
    }

    public Developer get(Long id)throws NotFoundException{
        log.info("getId in Service");
        try {
            return checkNotFoundWithId(repository.get(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public Developer add(Developer developer)throws NotFoundException{
        log.info("add in Service");
        try{
            return checkNotFound(repository.add(developer));
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public void delete(Long id)throws NotFoundException{
        log.info("delete in Service");
        try{
            checkNotFoundWithId(repository.delete(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }
}







