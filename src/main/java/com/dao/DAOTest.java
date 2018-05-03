package com.dao;

import com.bean.Department;
import com.bean.Employee;
import com.dao.implement.HiberDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
        IGenericDAO<Department> dao = new HiberDao(Department.class);
        Department department = dao.get(59);
        System.out.println(department);

/*        ArrayList<String> strings = new ArrayList<>();
        strings.get(2).isEmpty();
        System.out.println("dao" + dao.checkUnique("Q",null));*/

    }
}
