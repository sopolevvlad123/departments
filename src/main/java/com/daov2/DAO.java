package com.daov2;

import com.exception.DAOException;

public interface DAO  {

    void saveOrUpdate(Object object) throws DAOException;
    void delete (Object object) throws DAOException;
}
