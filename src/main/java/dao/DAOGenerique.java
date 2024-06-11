package dao;

import datasourceManagement.MySQLManager;

import java.util.LinkedList;

public interface DAOGenerique<T> {

    public T create(T obj);

    public T update(T obj);

    public void delete(T obj);

    public void saveAll(LinkedList<T> obj);

    public T findById(int cle);

    public LinkedList<T> findAll();

    public LinkedList<T> findByName(String name);
}

