package epam.ua.javacore.repository;

import java.util.Collection;

public interface GenericRepository<T,ID> {


    public Collection<T> getAll();
    public T get(ID id);
    public void add(T t);
    public void delete(ID id);

}
