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
    public @ResponseBody
    Account get(@PathVariable Long id) {
        Account account = null;
        try {
            account = service.get(id);
        } catch (NotFoundException e) {
        }
        return account;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Account> get() {
        return service.getAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Account add(@RequestBody Account account) {
        Account newAccount = null;
        try {
            newAccount = service.add(account);
        } catch (NotFoundException e) {
        }
        return newAccount;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (NotFoundException e) {
        }

    }
}
