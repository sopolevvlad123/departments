package com.dao;

import com.bean.Department;
import com.bean.Employee;
import com.dao.implement.HiderDao;

import java.sql.SQLException;

public class DAOTest {
    public static void main(String[] args) {
        IGenericDAO<Department> dao = new HiderDao();
        ((HiderDao<Department>) dao).setClazz(Department.class);
        try {
            System.out.println( " dao " + dao.get(59));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
