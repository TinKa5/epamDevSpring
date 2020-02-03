package epam.ua.javacore.service;

import epam.ua.javacore.exeption.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
import org.apache.log4j.Logger;
import java.util.Collection;


import static epam.ua.javacore.util.Validate.*;
import static epam.ua.javacore.util.Validate.checkNotFoundWithId;

public class AccountService {
    JdbcAccountRepository repository=new JdbcAccountRepository();
    private static final Logger log = Logger.getLogger(AccountService.class);

    public Collection<Account> getAll(){
        log.info("getAll in Service");
        return repository.getAll();
    }

    public Account get(Long id)throws NotFoundException{
        log.info("getId in Service");
        try {
            return checkNotFoundWithId(repository.get(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public Account add(Account account) throws NotFoundException{
        log.info("add in Service");
        try{
            return checkNotFound(repository.add(account));
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public void delete(Long id)throws NotFoundException {
        log.info("delete in Service");
        try {
            checkNotFoundWithId(repository.delete(id), id);
        } catch (NotFoundException e) {
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }
}
