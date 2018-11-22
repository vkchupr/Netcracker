package com.netcracker.edu.manager;

import com.netcracker.edu.dao.*;
import com.netcracker.edu.object.Employee;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Manager {
    private static final Pattern helpPattern = Pattern.compile("help");
    private static final Pattern exitPattern = Pattern.compile("exit");
    private static final Pattern listallPattern = Pattern.compile("list all");
    private static final Pattern addPattern = Pattern.compile("(add)\\s" +
            "([A-Z][a-zA-Z]*)\\s" +
            "([a-zA-z]+([ '-][a-zA-Z]+)*)\\s" +
            "(0*[0-9]{1,2})\\s" +
            "([a-zA-z]+([ '-][a-zA-Z]+)*)");
    private static final Pattern removePattern = Pattern.compile("(remove)\\s" +
            "([A-Z][a-zA-Z]*)\\s" +
            "([a-zA-z]+([ '-][a-zA-Z]+)*)\\s" +
            "(0*[0-9]{1,2})\\s" +
            "([a-zA-z]+([ '-][a-zA-Z]+)*)");
    private static final Pattern searchPattern = Pattern.compile("(search)\\s([a-zA-Z]+)(.*)");

    private EmployeeDAO employeeDAO;

    public Manager() {
        employeeDAO = new EmployeeDAOImpl();
    }

    private void add(String firstName, String lastName, int age, String position) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employee.setPosition(position);
        employeeDAO.addEmployee(employee);
    }

    private void listAll() {
        Collection<Employee> employees = employeeDAO.getAllEmployees();

        if(employees == null || employees.isEmpty()) {
            System.out.println("Database is empty");
            return;
        }

        System.out.println("List of employees:");
        for(Employee employee : employees)
            System.out.println(String.format("\t%s", employee.toString()));
    }

    private void remove(String firstName, String lastName, int age, String position) {
        Collection<Employee> employees = employeeDAO.getEmployeesByProperty("lastName", lastName);
        for (Employee employee : employees)

            if (employee.getFirstName().equals(firstName)
                    && employee.getAge() == age
                    && employee.getPosition().equals(position)) {
                employeeDAO.deleteEmployee(employee);
                return;
            }

        System.out.println("Employee wasn't removed");
    }

    private void search(String property, String value) {
        Collection<Employee> employees = employeeDAO.getEmployeesByProperty(property, value);
        if(employees == null || employees.isEmpty()) {
            System.out.println("Not found");
            return;
        }

        System.out.println("Found:");
        for(Employee employee : employees)
            System.out.println(String.format("\t%s", employee));
    }

    private void checkAndserveCommand(String command) {
        if (helpPattern.matcher(command).matches()) {
            printHints();
            return;
        }

        if (exitPattern.matcher(command).matches()) {
            exit();
            return;
        }

        if (listallPattern.matcher(command).matches()) {
            listAll();
            return;
        }

        Matcher matcher;
        if ((matcher = addPattern.matcher(command)).matches()) {
            String firstname = matcher.group(2);
            String lastname = matcher.group(3);
            int age = Integer.parseInt(matcher.group(5));
            String position = matcher.group(6);
            add(firstname, lastname, age, position);
            return;
        }

        if ((matcher = removePattern.matcher(command)).matches()) {
            String firstname = matcher.group(2);
            String lastname = matcher.group(3);
            int age = Integer.parseInt(matcher.group(5));
            String position = matcher.group(6);
            remove(firstname, lastname, age, position);
            return;
        }

        if ((matcher = searchPattern.matcher(command)).matches()) {
            try {
                String prop = matcher.group(2);
                String value = matcher.group(3).substring(1);
                search(prop, value);
            } catch (PatternSyntaxException ex){
                System.out.println("Wrong regular expression");
                ex.printStackTrace();
            }
            return;
        }

        System.out.println("No such command");
        printHints();
    }

    private static void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        printHints();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            manager.checkAndserveCommand(line);
        }

    }

    private static void printHints() {
        System.out.println("List of commands:");
        System.out.println("\tlist all - prints full list of employees");
        System.out.println("\tadd firstname lastname age position - adds new employee");
        System.out.println("\tremove firstname lastname age position - removes employee");
        System.out.println("\tsearch property value - searches for employee using value of property");
        System.out.println("\texit - shutdowns program");
        System.out.println("\thelp - prints this message");
    }
}

//add Emp Fasg-Gen 12 kjkfsd-fds
//add Emp Fasg 54 fs
//add Emsap Ffsdasg 44 fs
//list all
//search firstName Emp