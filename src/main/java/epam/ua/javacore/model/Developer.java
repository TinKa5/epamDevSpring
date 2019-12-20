package epam.ua.javacore.model;

import epam.ua.javacore.util.ModelUtil;

import epam.ua.javacore.repository.Connector;

import java.util.HashSet;
import java.util.Set;

import static epam.ua.javacore.util.ModelUtil.StringToSetId;
import static epam.ua.javacore.util.ModelUtil.toLong;


public class Developer extends Entity {
    String name;
    Set<Skill> skillSet;
    Account account;

    public Developer(String fileString, Connector connector){
        super();
    }

    Developer(Long id,String name, Set<Skill> skillSet, Account account) {


    }

    public String serialize() {
        return super.id.toString()+" "+name+" "+ ModelUtil.setToString(skillSet)+" "+account.getId();
    }

    public void deserialize(String string, Connector connector) {

        String[] strings=string.split(" ");
        super.id= toLong(strings[0]);
        name=strings[1];

        skillSet=new HashSet<Skill>();
        for (Long skillId: StringToSetId(strings[2])) {
           skillSet.add((Skill)connector.createConnection(Skill.class,skillId));
        }

        account=(Account)connector.createConnection(Account.class,toLong(strings[3]));


        connector.registration(this);
    }


}
