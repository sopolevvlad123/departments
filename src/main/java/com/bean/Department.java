package com.bean;

import com.validator.DepartmentNameValidator;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department",schema = "aimprosoft")
public class Department  implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "department_name",unique = true)
    @NotNull(message = "Incorrect department name value")
    @NotBlank(message = "Department name should not be blank")
    @MaxLength(value = 10, message = "Maximum length is 10 symbols")
    @MatchPattern(pattern = "\\w+\\.?", message = "Name should contain ONLY letters and numbers")
    @CheckWith(value = DepartmentNameValidator.class, message = "Department with this name already exist")
    private String departmentName;

    @OneToMany(cascade={CascadeType.ALL},fetch =FetchType.EAGER ,mappedBy = "department")
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

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
