package com.daov2.impl.hiber;

import com.daov2.DAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractHiberDao implements DAO {
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

    @Override
    public void delete(Integer id) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Object deletedObject = session.load(Object.class, id);
            if (deletedObject != null) {
                session.delete(deletedObject);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to delete bean by Hibernate",e);
        }
    }
}
