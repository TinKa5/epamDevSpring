package epam.ua.javacore.service;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.repository.AccountRepository;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
import epam.ua.javacore.repository.jdbc.JdbcGeneric;

import java.util.Collection;
import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class AccountService {
    JdbcAccountRepository repository=new JdbcAccountRepository();

    public Collection<Account> getAll(){
        return repository.getAll();
    }

    public Account get(Long id){
       return repository.get(id);
    }

    public Account add(Account account){
        return  repository.add(account);
    }

    public void delete(Long id){
        if (!repository.delete(id)){

        };
    }
}
