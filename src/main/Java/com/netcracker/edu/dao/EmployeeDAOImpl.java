package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    private List<Employee> employees;

    public EmployeeDAOImpl() {
        employees = new ArrayList<Employee>();
        logger.info("Employee DAO was created");
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int key) {
        try {
            Employee employee = employees.get(key);
            logger.info("Employee () with key () was found", employee.getFullName(), key);
            return employee;
        } catch (IndexOutOfBoundsException ex){
            logger.warn("Key () is out of bounds", key);
            return null;
        }
    }

    public void updateEmployee(Employee employee) {
        if(employee == null)
            return;

        employees.indexOf(employee);
    }

    public void deleteEmployee(Employee employee) {
        if(employee == null)
            return;

        if (employees.remove(employee))
            logger.info("() was successfully removed", employee.getFullName());
        else
            logger.warn("() wasn't removed", employee.getFullName());
    }
}
