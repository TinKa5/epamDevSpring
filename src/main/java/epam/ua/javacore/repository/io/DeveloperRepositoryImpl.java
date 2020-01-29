package epam.ua.javacore.repository.io;

import epam.ua.javacore.exeption.NotExistIDException;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.repository.DeveloperRepository;


import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static epam.ua.javacore.util.FileUtil.*;

public class DeveloperRepositoryImpl extends FileImplementationGenericRepository implements DeveloperRepository {
    final String FILE="src/main/resources/Developers.txt";

    public Collection<Developer> getAll() {
        if (connector==null){connector=new Connector();}
        new SkillRepositoryImpl().getAll();
        new AccountRepositoryImpl().getAll();
        Collection<Developer> res=readFromFile(FILE).map(x->new Developer(x,connector)).collect(Collectors.toList());
        connector=null;
        return res;
    }

    public Developer get(Long id) {
        String fileString;
        if (connector==null){connector=new Connector();}
        try {
            fileString = readFromFile(FILE, id);
        }catch (NoSuchElementException e){
            throw new NotExistIDException();
        }
       Developer res=new Developer(fileString,connector);
        connector=null;
        return res;
    }

    public Developer add(Developer t){
        Long id=getMaxId(FILE)+1;
        writeToFile(FILE,id+" "+t.serialize());
        Developer res=get(id);
        connector=null;
        return res;

    }

    public boolean delete(Long id) {
        deleteFromFile(FILE,id);
        connector=null;
        return true;
    }
}

