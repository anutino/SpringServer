package afokeeva.dao;

import java.util.ArrayList;

public interface Dao<K extends String, T> {
    abstract ArrayList findAll();
    abstract T findEntityById(K id);
    abstract boolean add(T entity);
    abstract boolean delete(K id);
    abstract boolean update(T entity);
}
