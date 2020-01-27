package epam.ua.javacore.model;

import epam.ua.javacore.repository.io.Connector;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class Skill extends Entity {
    String name;

    public Skill(){};

    public Skill(String fileString, Connector connector){
        super(fileString,connector);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String serialize() {
        return name;
    }

    public void deserialize(String string, Connector connector) {
        String[] strings=string.split(" ");
        super.id= toLong(strings[0]);
        name =strings[1];
        connector.registration(this);
    }

    @Override
    public String toString() {
        return Long.toString(getId())+" "+name;
    }
}