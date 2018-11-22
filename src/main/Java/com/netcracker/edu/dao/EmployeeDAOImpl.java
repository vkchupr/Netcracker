package com.netcracker.edu.dao;

import com.netcracker.edu.object.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    private Map<Employee, Map<String, String>> employees;

    public EmployeeDAOImpl() {
        employees = new HashMap<Employee, Map<String, String>>();
        logger.info("Employee DAO was created");
    }

    public Collection<Employee> getAllEmployees() {
        return employees.keySet();
    }

    public Collection<Employee> getEmployeeByProperty(String key, Pattern valuePattern) {
        if(key == null || valuePattern == null) {
            logger.warn("key or search pattern is null");
            return null;
        }

        Collection<Employee> result = new LinkedList<Employee>();
        for(Map.Entry<Employee, Map<String, String>> mapEntry : employees.entrySet()){
            Employee employee = mapEntry.getKey();
            Map<String, String> props = mapEntry.getValue();

            String prop = props.get(key);
            if(prop == null)
                continue;

            if(valuePattern.matcher(prop).matches()) {
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

        Map<String, String> props = getMapOfProperties(employee);
        employees.replace(employee, props);
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

        Map<String, String> props = getMapOfProperties(employee);
        employees.put(employee, props);
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

    private Map<String, String> getMapOfProperties(Employee employee){
        Map<String, String> props = new HashMap<String, String>();
        Field[] fields = employee.getClass().getFields();

        for(int i = 0; i < fields.length; i++){
            String key = null, value;
            try {
                key = fields[i].getName();
                value = fields[i].get(employee).toString();
                props.put(key, value);
            } catch (IllegalAccessException e) {
                logger.warn("Unable to extract field () from ()", key, employee);
            }
        }

        return props;
    }
}
