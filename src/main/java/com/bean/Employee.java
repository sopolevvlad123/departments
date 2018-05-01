package com.bean;

import com.validator.EmployeeEmailValidator;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    @NotNull(message = "Incorrect value")
    @NotBlank(message = "First Name should not be blank")
    @MaxLength(value = 10, message = " Maximum length is 10 symbols")
    @MatchPattern(pattern = "\\w+\\.?", message = "First Name should contain ONLY letters and numbers")
    private String firstName;

    @Column(name = "second_name")
    @NotNull(message = "Incorrect value")
    @NotBlank(message = "Last Name should not be blank")
    @MaxLength(value = 10, message = "Maximum length is 10 symbols")
    @MatchPattern(pattern = "\\w+\\.?", message = "Last Name should contain ONLY letters and numbers")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "Incorrect value")
    @NotBlank(message = "Email should not be blank")
    @MaxLength(value = 20, message = "Maximum length is 20 symbols")
    @Email(message = "Email is incorrect")
    @CheckWith(value = EmployeeEmailValidator.class, message = "Employee with this email already exist")
    private String email;

    @Column(name = "salary")
    @NotNull(message = "Incorrect salary value")
    @Range(min = 10, max = 10000, message = "Min salary is 10  max salary is 10 000")
    private Integer salary;

    @Column(name = "hire_date")
    @NotNull(message = "Incorrect date   value")
    @DateRange(format = "dd-mm-yyyy", max = "today", min = "01-01-2000", message = "Date should be in range 01-01-2000 to current day")
    private Date hireDate;

    @Column(name = "department_id")
    private Integer departmentId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, int salary, Date hireDate, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.departmentId = departmentId;
    }

    public Employee(int employeeId, String firstName, String lastName, String email, int salary, Date hireDate, int departmentId) {
        this(firstName, lastName, email, salary, hireDate, departmentId);
        this.employeeId = employeeId;
    }



    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer emploeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;
        return this.getEmail().equals(employee.getEmail());
    }

    @Override
    public int hashCode() {
        return this.getEmail().hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", departmentId=" + departmentId +
                '}';
    }
}
