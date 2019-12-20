package epam.ua.javacore.repository;


import epam.ua.javacore.model.Skill;


import java.util.Collection;
import java.util.stream.Collectors;

import static epam.ua.javacore.util.FileUtil.deleteFromFile;
import static epam.ua.javacore.util.FileUtil.readFromFile;

import static epam.ua.javacore.util.FileUtil.writeToFile;


public class SkillRepositoryImpl extends FileImplementationGenericRepository implements SkillRepository {
    final String FILE="Skills.txt";

    public Collection<Skill> getAll() {
        if (connector==null){connector=new Connector();}
        return readFromFile(FILE).map(x->new Skill(x,connector)).collect(Collectors.toList());
    }

    public Skill get(Long id) {
        String fileString=readFromFile(FILE,id);
        return new Skill(fileString,connector);
    }

    public void add(Skill t) {
        writeToFile(FILE,t.serialize());
    }

    public void delete(Long id) {
        deleteFromFile(FILE,id);
    }
}

