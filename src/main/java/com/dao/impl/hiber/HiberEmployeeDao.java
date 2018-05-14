package com.dao.impl.hiber;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.exception.DAOException;
import com.utils.HibernateConstants;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HiberEmployeeDao extends AbstractHiberDao implements EmployeeDAO {
    final static Logger logger = Logger.getLogger(HiberEmployeeDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Employee getEmployeeByID(Integer employeeId) throws DAOException {
        try {
            return sessionFactory.getCurrentSession().get(Employee.class, employeeId);
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get Employee by ID by Hibernate", e);
        }

    }

    @Override
    public List<Employee> getEmployeesByDepartmentID(Integer departmentId) throws DAOException {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(HibernateConstants.FROM_EMPLOYEE_BY_DEP_ID);
            query.setParameter("departmentId", departmentId);
            return query.list();
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get employees of the department # " + departmentId + " by Hibernate", e);
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws DAOException {
        try  {
            Query query = sessionFactory.getCurrentSession().createQuery(HibernateConstants.FROM_EMPLOYEE_BY_EMAIL);
            query.setParameter("email", email);
            return (Employee) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get employee by email " + email + " by Hibernate", e);
        }

    }

    @Override
    public void delete(Integer id) throws DAOException {
        try  {
            Employee employee = sessionFactory.getCurrentSession().load(Employee.class, id);
            if (employee != null) {
                sessionFactory.getCurrentSession().delete(employee);
            }
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to delete employee " + id + " by Hibernate", e);
        }
    }
}
