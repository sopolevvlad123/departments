package com.daov2.impl.hiber;

import com.bean.Department;
import com.daov2.DepDAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberDepartmentDao extends AbstractHiberDao implements DepDAO {
    final static Logger logger = Logger.getLogger(HiberDepartmentDao.class);
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public Department getDepartmentByID(Integer departmentId) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, departmentId);
        }catch (HibernateException e){
            logger.error(e);
            throw new DAOException("Fail to get department by ID " + departmentId,e);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department").list();
        }catch  (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get all Departments by Hibernate",e);
        }
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws DAOException {
        Transaction transaction = null;
        Department department;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Department where departmentName=:departmentName");
            query.setParameter("departmentName", departmentName);
            department = (Department) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get department by name " + departmentName,e);
        }
        return department;
    }
}
