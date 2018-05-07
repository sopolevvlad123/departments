package com.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    T get(Integer id) ;

    void saveOrUpdate(T t);

    List<T> getAll() ;

    List<T> getByParam(String param, Integer id) ;

    void delete(Integer id) ;

    boolean checkUnique(String name, Integer id) ;

}
