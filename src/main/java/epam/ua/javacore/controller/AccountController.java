package epam.ua.javacore.controller;

import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    Account get(@PathVariable Long id)throws NotFoundException{
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Account> get() {
        return service.getAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Account add(@RequestBody Account account) throws NotFoundException{
       return service.add(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) throws NotFoundException{
       service.delete(id);
    }
}
