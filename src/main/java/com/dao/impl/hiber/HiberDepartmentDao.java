package com.dao.impl.hiber;

import com.bean.Department;
import com.dao.DepartmentDAO;
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
public class HiberDepartmentDao extends AbstractHiberDao implements DepartmentDAO {
    final static Logger logger = Logger.getLogger(HiberDepartmentDao.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Department getDepartmentByID(Integer departmentId) throws DAOException {
        try  {
            return sessionFactory.getCurrentSession().get(Department.class, departmentId);
        }catch (HibernateException e){
            logger.error(e);
            throw new DAOException("Fail to get department by ID " + departmentId + " by Hibernate",e);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        try  {
            return sessionFactory.getCurrentSession().createQuery(HibernateConstants.FROM_DEPARTMENT).list();
        }catch  (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get all Departments by Hibernate",e);
        }
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws DAOException {
        Department department;
        try  {
            Query query = sessionFactory.getCurrentSession().createQuery(HibernateConstants.FROM_DEPARTMENT_BY_NAME);
            query.setParameter("departmentName", departmentName);
            department = (Department) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error(e);
            throw new DAOException("Fail to get department by name " + departmentName + " by Hibernate",e);
        }
        return department;
    }

    @Override
    public void delete(Integer id) throws DAOException {
       try {
           Department department = sessionFactory.getCurrentSession().load(Department.class, id);
           if (department != null) {
               sessionFactory.getCurrentSession().delete(department);
           }
       }catch (HibernateException e){
           logger.error(e);
           throw new DAOException("Fail to get delete department " + id + " by Hibernate",e);

       }

    }
}
