package com.dao.implement.HiberImpl;

import com.bean.Department;
import com.dao.DepartmentDAO;
import com.exception.DAOException;
import com.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HiberDepartmentDAOImpl implements DepartmentDAO {
    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();


    @Override
    public void saveOrUpdate(Department department) throws DAOException {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(department);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Department getDepartment(Integer departmentId) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class,departmentId);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        Transaction tx = null;
        List<Department> beanList;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            beanList =  session.createQuery("FROM Department ").list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return beanList;
    }

    @Override
    public void deleteDepartment(Integer departmentId) throws DAOException {
        Department department = getDepartment(departmentId);
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(department);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean checkUnique(String departmentName, Integer departmentId) throws DAOException {
        Transaction tx = null;
        List<Department> resultList;
        boolean result = false;
        System.out.println("Check Unique department  from hiber");
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query;
            if (departmentId == null){
                 query = session.createQuery("from Department where departmentName = :departmentName");
                query.setParameter("departmentName", departmentName);
            }else {
                 query = session.createQuery("from Department where departmentName = :departmentName and departmentId <> :departmentId ");
                query.setParameter("departmentName", departmentName);
                query.setParameter("departmentId", departmentId);
            }

            resultList = query.list();
            System.out.println("dep result set"  );


            System.out.println("resultSet" + resultList);
            result =  (resultList.size() == 0);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return result;
    }
}
