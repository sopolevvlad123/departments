package com.daov2.impl.jdbc;

import com.bean.Department;
import com.dao.implement.JDBCImpl.DepartmentDAOImpl;
import com.daov2.DepDAO;
import com.exception.DAOException;
import org.apache.log4j.Logger;

import java.util.List;

public class JDBCDepartmentDao implements DepDAO {
    final static Logger logger = Logger.getLogger(JDBCDepartmentDao.class);
    private DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public Department getDepartmentByID(Integer departmentId) throws DAOException {
        return departmentDAO.getDepartment(departmentId);
    }

    @Override
    public List<Department> getAllDepartments() throws DAOException {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return null;
    }

    @Override
    public void saveOrUpdate(Object object) throws DAOException {
        Department department = (Department) object;
        departmentDAO.saveOrUpdate(department);
    }

    @Override
    public void delete(Integer id) throws DAOException {
        departmentDAO.deleteDepartment(id);
    }
}
