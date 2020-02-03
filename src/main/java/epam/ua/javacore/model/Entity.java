package epam.ua.javacore.model;


public abstract class Entity {
    Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

