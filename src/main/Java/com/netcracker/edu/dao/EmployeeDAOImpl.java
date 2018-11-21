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
        return null;
    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(Employee employee) {
        try {
            employees.remove(employee);
            logger.info("() was successfully removed", employee.toString());
        } catch (NullPointerException ex){

        }
    }
}
