package epam.ua.javacore.service;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
import epam.ua.javacore.repository.jdbc.JdbcGeneric;

import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class AccountService {
    JdbcGeneric repository=new JdbcAccountRepository();

    public Stream<String> getAll(){
        return repository.getAll().stream().map(x->x.toString());
    }

    public String get(Long id){
       return repository.get(id).toString();
    }

    public String add(String name,String accountStatus){
        Account account=new Account();
        account.setContent(name);
        account.setAccountStatus(AccountStatus.valueOf(accountStatus));
        return repository.add(account).toString();
    }

    public void delete(Long id){
        if (!repository.delete(id)){

        };
    }
}
