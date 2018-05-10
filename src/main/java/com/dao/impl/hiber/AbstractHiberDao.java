package com.dao.impl.hiber;

import com.dao.DAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public  abstract class AbstractHiberDao implements DAO {
    final static Logger logger = Logger.getLogger(AbstractHiberDao.class);

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to save/update -- " + object.getClass() + " by Hibernate",e);
        }
    }

}
