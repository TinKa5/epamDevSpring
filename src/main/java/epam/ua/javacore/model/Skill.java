package epam.ua.javacore.model;

import epam.ua.javacore.util.ModelUtil;
import epam.ua.javacore.repository.Connector;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class Skill extends Entity {
    String skill;

    public Skill(String fileString, Connector connector){
        super();
    }

    public String serialize() {
        return super.id.toString()+" "+skill;
    }

    public void deserialize(String string, Connector connector) {
        String[] strings=string.split(" ");
        super.id= toLong(strings[0]);
        skill=strings[1];
        connector.registration(this);
    }
}