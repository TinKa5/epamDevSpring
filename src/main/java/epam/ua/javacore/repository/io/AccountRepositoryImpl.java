package epam.ua.javacore.repository.io;

import epam.ua.javacore.exeption.NotExistIDException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.repository.AccountRepository;


import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static epam.ua.javacore.util.FileUtil.*;

public class AccountRepositoryImpl extends FileImplementationGenericRepository implements AccountRepository {
    final String FILE="src/main/resources/Accounts.txt";

    public Collection<Account> getAll() {
        if (connector==null){connector=new Connector();}
        return readFromFile(FILE).map(x->new Account(x,connector)).collect(Collectors.toList());
    }

    public Account get(Long id) {
        String fileString;
        try {
            fileString = readFromFile(FILE, id);
        }catch (NoSuchElementException e){
            throw new NotExistIDException();
        }
        if (connector==null){connector=new Connector();}
        return new Account(fileString,connector);

    }

    public Account add(Account t){
        Long id=getMaxId(FILE)+1;
        writeToFile(FILE,id+" "+t.serialize());
        return get(id);

    }

    public boolean delete(Long id) {

        deleteFromFile(FILE,id);
        return true;
    }
}
