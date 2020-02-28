package epam.ua.javacore.service;

import epam.ua.javacore.annotation.Timed;
import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.repository.GenericRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;


import static epam.ua.javacore.util.Validate.*;
import static epam.ua.javacore.util.Validate.checkNotFoundWithId;
@Service
public class AccountService {

    GenericRepository repository;

    @Autowired
    AccountService(@Qualifier(value = "accountRepository") GenericRepository repository){
        this.repository=repository;
    };
    private static final Logger log = Logger.getLogger(AccountService.class);



    public Collection<Account> getAll(){
        log.info("getAll in Service");
        return repository.getAll();
    }


    public Account get(Long id)throws NotFoundException{
        log.info("getId in Service");
        try {
            return checkNotFoundWithId((Account)repository.get(id),id);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }


    public Account add(Account account) throws NotFoundException{
        log.info("add in Service");
        try{
            return checkNotFound((Account)repository.add(account));
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
