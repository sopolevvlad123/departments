package com.bean;

import com.validator.DepartmentNameValidator;
import net.sf.oval.constraint.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Department {

    private Integer departmentId;
    @NotNull
    @NotBlank(message = "Department name should not be blank")
    @MaxLength(value = 10, message = "Maximum length is 10 symbols")
    @MatchPattern(pattern = "\\w+\\.?", message = "Name should contain ONLY letters and numbers")
    @CheckWith(value = DepartmentNameValidator.class, message = "Department with this name already exist")
    private String departmentName;
    private List<Employee> employeeList = new ArrayList<>();

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(int departmentId, String departmentName) {
        this(departmentName);
        this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String demartmentName) {
        this.departmentName = demartmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }

}
