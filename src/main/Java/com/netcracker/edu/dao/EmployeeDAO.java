package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getEmployee(int key);
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
