package epam.ua.javacore.controller;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.repository.jdbc.JdbcAccountRepository;
import epam.ua.javacore.repository.jdbc.JdbcGeneric;
import epam.ua.javacore.service.AccountService;

import java.util.stream.Stream;

import static epam.ua.javacore.util.Validate.entityValidation;
import static epam.ua.javacore.util.Validate.idValidation;

public class AccountController {
    AccountService service=new AccountService();

    public Stream<String> getAll(){
        return service.getAll().stream().map(x->x.toString());
    }

    public String get(Long id){
        idValidation(id);
        return service.get(id).toString();
    }

    public String add(String name,String accountStatus){
        entityValidation(name);

        Account account=new Account();
        account.setContent(name);
        account.setAccountStatus(AccountStatus.valueOf(accountStatus));
        return "Success adding "+service.add(account);
    }

    public String delete(Long id){
        idValidation(id);
        service.delete(id);
        return "Success deleting";
    }
}
