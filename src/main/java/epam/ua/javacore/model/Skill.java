package epam.ua.javacore.model;


public class Skill extends Entity {
    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Long.toString(getId())+" "+name;
    }
}