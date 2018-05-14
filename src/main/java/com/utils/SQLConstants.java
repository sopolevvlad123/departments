package com.utils;

public interface SQLConstants {

    String SAVE_OR_UPDATE_EMPLOYEE = "REPLACE INTO `aimprosoft`.`employee` (`email`, `first_name`, " +
            "`second_name`, `salary`, `hire_date`, `department_id`,`employee_id`) VALUES (?, ?, ?, ?, ?, ?, ?);\n";
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
    String CHECK_IS_EMAIL_UNIQUE = "SELECT * FROM aimprosoft.employee where employee.email = ? and employee.employee_id <> ?;";


    String SAVE_OR_UPDATE_DEPARTMENT = "REPLACE INTO `aimprosoft`.`department` (`department_name`,`department_id`) VALUES (?,?);\n";
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




    /*CREATE TABLE `aimprosoft`.`employee` (
 `employee_id` INT NOT NULL AUTO_INCREMENT,
 `first_name` VARCHAR(45) NOT NULL,
 `last_name` VARCHAR(45) NOT NULL,
 `email` VARCHAR(45) NOT NULL,
 `salary` INT NOT NULL,
 `hire_date` DATE NOT NULL,
 `department_id` INT NOT NULL,
 PRIMARY KEY (`employee_id`),
 UNIQUE INDEX `email_UNIQUE` (`email` ASC),
 INDEX `department_id_idx` (`department_id` ASC),
 CONSTRAINT `department_id`
 FOREIGN KEY (`department_id`)
 REFERENCES `aimprosoft`.`department` (`department_id`)
 ON DELETE CASCADE
 ON UPDATE CASCADE);
*/

    /*CREATE TABLE `aimprosoft`.`department` (
    `department_id` INT NOT NULL AUTO_INCREMENT,
    `department_name` VARCHAR(45) NULL,
    PRIMARY KEY (`department_id`),
    UNIQUE INDEX `department_name_UNIQUE` (`department_name` ASC));
    */


}
