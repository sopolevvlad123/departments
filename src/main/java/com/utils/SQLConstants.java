package com.utils;

public interface SQLConstants {

    String INSERT_EMPLOYEE = "INSERT INTO `aimprosoft`.`employee` (`email`, `first_name`, " +
            "`second_name`, `salary`, `hire_date`, `department_id`) VALUES (?, ?, ?, ?, ?, ?);\n";
    String SELECT_EMPLOYEE_BY_EMAIL = "SELECT employee.*, department.department_name  FROM aimprosoft.employee " +
            "join aimprosoft.department where employee.department_id = department.department_id and email = ?;";
    String SELECT_EMPLOYEE_BY_ID = "SELECT employee.*, department.department_name  FROM aimprosoft.employee " +
            "join aimprosoft.department where employee.department_id = department.department_id and employee_id = ?;";
    String SELECT_ALL_EMPLOYEE = "SELECT employee.*, department.department_name  FROM aimprosoft.employee " +
            "join aimprosoft.department where employee.department_id = department.department_id ;";
    String UPDATE_EMPLOYEE = "UPDATE `aimprosoft`.`employee` SET `email`= ?, `first_name`=?, " +
            "`second_name`=?, `salary`=?, `hire_date`=?, `department_id`=? WHERE `employee_id`=?\n";
    String DELETE_EMPLOYEE = "DELETE FROM `aimprosoft`.`employee` WHERE `employee_id`=?;";
    String CHECK_IS_EMAIL_UNIQUE_NO_ID = "SELECT employee.email FROM aimprosoft.employee where employee.email = ?;";
    String CHECK_IS_EMAIL_UNIQUE_WITH_ID = "SELECT * FROM aimprosoft.employee where employee.email = ? and employee.employee_id <> ?;";


    String INSERT_DEPARTMENT = "INSERT INTO `aimprosoft`.`department` (`department_name`) VALUES (?);\n";
    String SELECT_DEPARTMENT_BY_NAME = "SELECT * FROM aimprosoft.department where aimprosoft.department.department_name = ?;";
    String SELECT_DEPARTMENT_BY_ID = "SELECT * FROM aimprosoft.department where aimprosoft.department.department_id = ?;";
    String SELECT_DEPARTMENTS_EMPLOYEE_BY_NAME = "SELECT employee.*, department_name FROM aimprosoft.employee join aimprosoft.department " +
            "on department.department_id = employee.department_id and department.department_name = ?;";
    String SELECT_DEPARTMENTS_EMPLOYEE_BY_ID = "SELECT employee.*, department_name FROM aimprosoft.employee " +
            "join aimprosoft.department where employee.department_id = ? and employee.department_id = department.department_id GROUP BY employee_id;";
    String SELECT_ALL_DEPARTMENTS = "SELECT * FROM aimprosoft.department GROUP BY department_id";
    String UPDATE_DEPARTMENT = "UPDATE `aimprosoft`.`department` SET `department_name`=? WHERE `department_id`=?;\n";
    String DELETE_DEPARTMENT = "DELETE FROM `aimprosoft`.`department` WHERE `department_id`=?;";
    String CHECK_DEP_NAME_UNIQUE_NO_ID = "SELECT aimprosoft.department.department_name FROM aimprosoft.department where department_name = ?;";
    String CHECK_DEP_NAME_UNIQUE_WITH_ID = "SELECT aimprosoft.department.department_name FROM aimprosoft.department where department.department_name = ? " +
            "and department.department_id <> ?;";


    String mainURL = "http://localhost:8080/";
    String CREATE_EMPLOYEE_TABLE = "CREATE TABLE `aimprosoft`.`employee` (\n" +
            "  `employee_id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `email` VARCHAR(45) NOT NULL,\n" +
            "  `first_name` VARCHAR(45) NOT NULL,\n" +
            "  `second_name` VARCHAR(45) NOT NULL,\n" +
            "  `salary` INT NOT NULL,\n" +
            "  `hire_date` DATE NOT NULL,\n" +
            "  `department_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`employee_id`),\n" +
            "  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC),\n" +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC),\n" +
            "  INDEX `department_id_idx` (`department_id` ASC),\n" +
            "  CONSTRAINT `department_id`\n" +
            "    FOREIGN KEY (`department_id`)\n" +
            "    REFERENCES `aimprosoft`.`department` (`department_id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";
    String CREATE_DEPARTMENT_TABLE = "CREATE TABLE `aimprosoft`.`department` (\\n\" +\n" +
            "                   \"  `department_id` INT NOT NULL AUTO_INCREMENT,\\n\" +\n" +
            "                   \"  `name` VARCHAR(45) NOT NULL,\\n\" +\n" +
            "                   \"  PRIMARY KEY (`department_id`),\\n\" +\n" +
            "                   \"  UNIQUE INDEX `employee_id_UNIQUE` (`department_id` ASC),\\n\" +\n" +
            "                   \"  UNIQUE INDEX `email_UNIQUE` (`name` ASC));\\n";


}
