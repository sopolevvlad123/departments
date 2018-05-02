package com.dao.implement;

import com.dao.IGenericDAO;
import com.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class HiberDao<T> implements IGenericDAO<T> {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class<T> clazz;

    public HiberDao(Class<T> clazz){
        this.clazz = clazz;
    }
    @Override
    public T get(Integer id) throws SQLException {
        Transaction tx = null;
        T bean;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            bean = session.get(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }

        return bean;
    }

    @Override
    public void saveOrUpdate(T object) throws SQLException {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(object);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List getAll() throws SQLException {
        Transaction tx = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            beanList = session.createQuery("FROM " + clazz.getName()).list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return beanList;
    }

    @Override
    public List<T> getByParam(String param, Integer id) throws SQLException {
        Transaction tx = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            beanList = session.createQuery("FROM " + clazz.getName() + " WHERE " + param + " = " + id).list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return beanList;
    }


    @Override
    public void delete(Integer id) throws SQLException {
        T bean = get(id);
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(bean);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean checkUnique(T object) throws SQLException {
        Transaction tx = null;
        T bean;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            bean = session.find(clazz,object);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return bean == null;
    }

}
