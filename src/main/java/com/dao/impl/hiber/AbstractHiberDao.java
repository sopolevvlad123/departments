package com.dao.impl.hiber;

import com.dao.DAO;
import com.exception.DAOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public  abstract class AbstractHiberDao implements DAO {

    private final static Logger logger = Logger.getLogger(AbstractHiberDao.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        try {
            sessionFactory.getCurrentSession().merge(object);
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to save/update " + object.getClass().getSimpleName() + " by Hibernate");
        }
    }

}
