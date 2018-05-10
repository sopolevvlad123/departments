package com.sandbox;

import com.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    T get(Integer id) throws DAOException;

    void saveOrUpdate(T t) throws DAOException;

    List<T> getAll() throws DAOException;

    List<T> getByParam(String param, Integer id) throws DAOException ;

    void delete(Integer id) throws DAOException ;

    T getByUniqueField(String field) throws DAOException ;

}
