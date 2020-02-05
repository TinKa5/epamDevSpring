package epam.ua.javacore.model;


import java.util.Set;

import static epam.ua.javacore.util.ModelUtil.setToString;

public class Developer extends Entity {
    String name;
    Set<Skill> skillSet;
    Account account;

    public Developer(){};

    public Developer(String name, Set<Skill> skillSet, Account account) {
        this.name = name;
        this.skillSet = skillSet;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public Account getAccount() {
        return account;
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


    @Override
    public String toString() {
        return  Long.toString(getId())+" "+name+" "+ setToString(skillSet)+" "+account.toString();
    }
}
