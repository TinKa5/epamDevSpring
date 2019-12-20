package epam.ua.javacore.repository;

import epam.ua.javacore.model.Developer;


import java.util.Collection;
import java.util.stream.Collectors;

import static epam.ua.javacore.util.FileUtil.deleteFromFile;
import static epam.ua.javacore.util.FileUtil.readFromFile;
import static epam.ua.javacore.util.FileUtil.writeToFile;

public class DeveloperRepositoryImpl extends FileImplementationGenericRepository implements DeveloperRepository {
    final String FILE="Developer.txt";

    public Collection<Developer> getAll() {
        if (connector==null){connector=new Connector();}
        new SkillRepositoryImpl().getAll();
        new AccountRepositoryImpl().getAll();
        return readFromFile(FILE).map(x->new Developer(x,connector)).collect(Collectors.toList());
    }

    public Developer get(Long id) {
        String fileString=readFromFile(FILE,id);
        return new Developer(fileString,connector);
    }

    public void add(Developer t) {
        writeToFile(FILE,t.serialize());
    }

    public void delete(Long id) {
        deleteFromFile(FILE,id);
    }
}

