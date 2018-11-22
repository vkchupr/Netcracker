package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    private Map<Employee, Map<String, String>> employees;

    public EmployeeDAOImpl() {
        employees = new HashMap<Employee, Map<String, String>>();
        logger.trace("Employee DAO was created");
    }

    public Collection<Employee> getAllEmployees() {
        return employees.keySet();
    }

    public Collection<Employee> getEmployeesByProperty(String key, String value) {
        if(key == null || value == null) {
            logger.warn("key or value is null");
            return null;
        }

        Collection<Employee> result = new LinkedList<Employee>();
        for(Map.Entry<Employee, Map<String, String>> mapEntry : employees.entrySet()){
            Employee employee = mapEntry.getKey();
            Map<String, String> props = mapEntry.getValue();

            String prop = props.get(key);
            if(prop == null)
                continue;

            if(prop.equals(value)) {
                logger.trace("found an employee that matches search pattern");
                result.add(employee);
            }
        }
        return result;
    }

    public void updateEmployee(Employee employee) {
        if(employee == null)
            return;

        if(employees.get(employee) == null){
            logger.warn("employee () wasn't found", employee);
            return;
        }

        Map<String, String> props = employee.getMapOfProperties();
        employees.replace(employee, props);
        logger.info("Updated: ()", employee);
    }

    public void addEmployee(Employee employee) {
        if(employee == null){
            logger.warn("Null value can't be added");
            return;
        }

        if(employees.get(employee) != null){
            logger.warn("() already in database", employee);
            return;
        }

        Map<String, String> props = employee.getMapOfProperties();
        employees.put(employee, props);
        logger.info("Added: ()", employee);
    }

    public void deleteEmployee(Employee employee) {
        if(employee == null){
            logger.warn("Null value can't be deleted");
            return;
        }

        if (employees.remove(employee) != null)
            logger.info("() was successfully removed", employee.getFullName());
        else
            logger.warn("error while deleting ()", employee.getFullName());
    }
}
