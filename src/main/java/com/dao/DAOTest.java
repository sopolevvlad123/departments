package com.dao;

import com.bean.Department;
import com.bean.Employee;
import com.dao.implement.HiberDao;

import java.sql.SQLException;

public class DAOTest {
    public static void main(String[] args) {
        IGenericDAO dao = new HiberDao(Employee.class);
        try {
            dao.saveOrUpdate(new Department("testo"));


            System.out.println( " dao " + dao.get(68));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
