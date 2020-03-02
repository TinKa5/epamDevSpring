package epam.ua.javacore.service;

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

    private GenericRepository repository;

    @Autowired
    AccountService(@Qualifier(value = "accountRepository") GenericRepository repository){
        this.repository=repository;
    }
    private static final Logger log = Logger.getLogger(AccountService.class);



    public Collection<Account> getAll(){
        log.info("getAll in Service");
        return repository.getAll();
    }


    public Account get(Long id)throws NotFoundException{
        log.info("getId in Service");
        return checkNotFoundWithId((Account)repository.get(id),id);

    }


    public Account add(Account account) throws NotFoundException{
        log.info("add in Service");
        return checkNotFound((Account)repository.add(account));

    }

    public void delete(Long id)throws NotFoundException {
        log.info("delete in Service");
        checkNotFoundWithId(repository.delete(id), id);

    }
}
