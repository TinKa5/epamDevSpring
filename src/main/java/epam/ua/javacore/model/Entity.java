package epam.ua.javacore.model;


import epam.ua.javacore.repository.io.Connector;

public abstract class Entity {
    Long id;


    Entity(){}

    public Entity(String fileString, Connector connector){
        deserialize(fileString,connector);
    }


    public long getId() {
        return id;
    }


    public abstract String serialize();

    public abstract void deserialize (String string,Connector connector);

    @Override
    public String toString() {
        return serialize()+"\n";
    }
}
