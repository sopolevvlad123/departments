package com.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    T get(Integer id) throws SQLException;

    void saveOrUpdate(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    List<T> getByParam(String param, Integer id) throws SQLException;

    void delete(Integer employeeId) throws SQLException;

    boolean checkUnique(T t) throws SQLException;

}
