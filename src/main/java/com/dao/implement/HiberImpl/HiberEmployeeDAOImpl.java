package com.dao.implement.HiberImpl;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberEmployeeDAOImpl implements EmployeeDAO {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public void saveOrUpdate(Employee employee) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Employee getEmployee(Integer employeeId) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, employeeId);
        }
    }

    @Override
    public List<Employee> getAllEmployee() throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee").list();
        }
    }

    @Override
    public List<Employee> getEmployeeByDepartment(Integer departmentId) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM  Employee  WHERE departmentId =:departmentId");
            query.setParameter("departmentId",departmentId);
            return query.list();
        }
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws DAOException {
        Employee bean = getEmployee(employeeId);
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
    public Employee getEmployeeByEmail(String email) throws DAOException {
        return null;
    }


}
