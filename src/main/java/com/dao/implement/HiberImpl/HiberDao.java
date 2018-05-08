package com.dao.implement.HiberImpl;

import com.bean.Department;
import com.bean.Employee;
import com.dao.IGenericDAO;
import com.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiberDao<T> implements IGenericDAO<T> {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class<T> clazz;
    private static Map<String,String> uniqueCheckHelpMap = new HashMap<>();

    static {
        uniqueCheckHelpMap.put(Department.class.getName(),"department_name");
        uniqueCheckHelpMap.put(Employee.class.getName(), "email");
    }

    public HiberDao(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T get(Integer id){
        try (Session session = sessionFactory.openSession()) {
             return session.get(clazz, id);
        }
    }

    @Override
    public void saveOrUpdate(T object)  {
       Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List getAll()  {
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
    public List<T> getByParam(String param, Integer id){
        Transaction tx = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            org.hibernate.query.Query<T> query = session.createQuery("FROM " + clazz.getName() + " WHERE " + param + " = " + id,clazz);
            beanList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return beanList;
    }


    @Override
    public void delete(Integer id)  {
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
    public T getByName(String name) {
        return null;
    }


}
