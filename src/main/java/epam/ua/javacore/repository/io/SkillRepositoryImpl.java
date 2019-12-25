package epam.ua.javacore.repository.io;


import epam.ua.javacore.exeption.NotExistIDException;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.SkillRepository;


import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epam.ua.javacore.util.FileUtil.*;


public class SkillRepositoryImpl extends FileImplementationGenericRepository implements SkillRepository {
    final String FILE="src/main/resources/Skills.txt";

    public Collection<Skill> getAll() {
        if (connector==null){connector=new Connector();}
        Stream<String> str=readFromFile(FILE);
        return str.map(x->new Skill(x,connector)).collect(Collectors.toList());
    }

    public Skill get(Long id) {
        String fileString;
        try {
            fileString = readFromFile(FILE, id);
        }catch (NoSuchElementException e){
            throw new NotExistIDException();
        }
        if (connector==null){connector=new Connector();}
        return new Skill(fileString,connector);
    }

    public Skill add(Skill t) {
        Long id=getMaxId(FILE)+1;
        writeToFile(FILE,id+" "+t.serialize());
        return get(id);
    }

    public void delete(Long id) {
        deleteFromFile(FILE,id);
    }
}

