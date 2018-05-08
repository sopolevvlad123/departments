package com.dao.implement.HiberImpl;

import com.bean.Department;
import com.bean.Employee;
import com.dao.IGenericDAO;
import com.daov2.impl.hiber.HiberDepartmentDao;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiberDao<T> implements IGenericDAO<T> {

    final static Logger logger = Logger.getLogger(HiberDao.class);
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class<T> clazz;
    private static Map<Class,String> uniqueFieldMap = new HashMap<>();

    static {
        uniqueFieldMap.put(Department.class,"department_name");
        uniqueFieldMap.put(Employee.class, "email");
    }

    public HiberDao(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T get(Integer id) throws DAOException{
        try (Session session = sessionFactory.openSession()) {
             return session.get(clazz, id);
        }catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get bean  by Hibernate",e);
        }
    }

    @Override
    public void saveOrUpdate(T object) throws DAOException {
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
    public List getAll() throws DAOException {
        Transaction transaction = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            beanList = session.createQuery("FROM " + clazz.getName()).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get all entities by Hibernate",e);
        }
        return beanList;
    }

    @Override
    public List<T> getByParam(String param, Integer id) throws DAOException{
        Transaction transaction = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<T> query = session.createQuery("FROM " + clazz.getName() + " WHERE " + param + " = " + id,clazz);
            beanList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get all entities by Hibernate",e);
        }
        return beanList;
    }


    @Override
    public void delete(Integer id) throws DAOException{
        T bean = get(id);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(bean);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get all entities by Hibernate",e);
        }
    }

    @Override
    public T getByUniqueField(String name) throws DAOException{
      /*  Transaction transaction = null;
        List<T> beanList;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<T> query = session.createQuery("FROM " + clazz.getName() + " WHERE " + );
            beanList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get all entities by Hibernate",e);
        }
        return beanList;*/
      return null;
    }


}
