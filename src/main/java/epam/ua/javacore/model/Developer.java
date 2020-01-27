package epam.ua.javacore.model;

import epam.ua.javacore.repository.io.Connector;

import java.util.HashSet;
import java.util.Set;

import static epam.ua.javacore.util.ModelUtil.*;


public class Developer extends Entity {
    String name;
    Set<Skill> skillSet;
    Account account;

    public String getName() {
        return name;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public Account getAccount() {
        return account;
    }

    public Developer(String fileString, Connector connector){
        super(fileString,connector);
    }

    public Developer() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String serialize() {
        return name+" "+ setToStringId(skillSet)+" "+account.getId();
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

    @Override
    public String toString() {
        return  Long.toString(getId())+" "+name+" "+ setToString(skillSet)+" "+account.toString();
    }
}
