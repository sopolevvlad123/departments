package com.daov2.impl.hiber;

import com.bean.Employee;
import com.daov2.EmpDAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberEmployeeDao extends AbstractHiberDao implements EmpDAO {
    final static Logger logger = Logger.getLogger(HiberEmployeeDao.class);
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    @Override
    public Employee getEmployeeByID(Integer employeeId) throws DAOException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Employee.class,employeeId);
        }catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get Employee by ID by Hibernate",e);
        }

    }

    @Override
    public List<Employee> getEmployeesByDepartmentID(Integer departmentId) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee ").list();
        }catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get employees of the department # " + departmentId +" by Hibernate",e);
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws DAOException {
        Transaction transaction = null;
        Employee employee;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Employee where email=:email");
            query.setParameter("email",email);
            employee = (Employee) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e);
            throw new DAOException("Fail to get employee by email " + email,e);
        }
        return employee;
    }
}
