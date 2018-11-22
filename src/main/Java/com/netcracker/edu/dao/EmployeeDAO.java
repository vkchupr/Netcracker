package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;

import java.util.Collection;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Collection<Employee> getAllEmployees();
    Collection<Employee> getEmployeesByProperty(String property, String value);
}
