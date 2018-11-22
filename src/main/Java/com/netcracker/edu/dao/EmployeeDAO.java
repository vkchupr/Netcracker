package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;

import java.util.Collection;
import java.util.regex.Pattern;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Collection<Employee> getAllEmployees();
    Collection<Employee> getEmployeesByProperty(String property, String value);
}
