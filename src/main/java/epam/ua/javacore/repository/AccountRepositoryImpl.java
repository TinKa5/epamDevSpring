package epam.ua.javacore.repository;

import epam.ua.javacore.model.Account;


import java.util.Collection;
import java.util.stream.Collectors;

import static epam.ua.javacore.util.FileUtil.deleteFromFile;
import static epam.ua.javacore.util.FileUtil.readFromFile;
import static epam.ua.javacore.util.FileUtil.writeToFile;

public class AccountRepositoryImpl extends FileImplementationGenericRepository implements AccountRepository{
    final String FILE="Account.txt";

    public Collection<Account> getAll() {
        if (connector==null){connector=new Connector();}
        return readFromFile(FILE).map(x->new Account(x,connector)).collect(Collectors.toList());
    }

    public Account get(Long id) {
        String fileString=readFromFile(FILE,id);
        return new Account(fileString,connector);
    }

    public void add(Account t) {
        writeToFile(FILE,t.serialize());
    }

    public void delete(Long id) {
        deleteFromFile(FILE,id);
    }
}
