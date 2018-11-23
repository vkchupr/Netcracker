package com.netcracker.edu.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.netcracker.edu.object.Employee;

public class EmployeeDAOImpl implements EmployeeDAO
{
    List<Employee> list=new LinkedList<>();
    

    
    public void  addEmpoyee()
    {
        
        try 
        {
            
            
        Scanner scanner=new Scanner(System.in);
        
        System.out.println("Создание нового сотрудника: ");
        Employee newEmployee=new Employee();
        
        
        System.out.println("Введите имя, фамилию, возраст и должность сотрудника: ");
        
        String[] ret=scanner.nextLine().split("\\s+");
        
        
        if(ret.length!=4) {System.out.println("Введены неправильные данные!");}
        
        Pattern pattern=Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(ret[0]);
       
        if(!matcher.matches()) {System.out.println("Введено неправильное имя!");}
        else {newEmployee.setFirstName(ret[0]);}
        
        Matcher matcher2=pattern.matcher(ret[1]);
        if(!matcher2.matches()) {System.out.println("Введено неправильная фамилия!");}
        else {newEmployee.setLastName(ret[1]);}
        
        Pattern pattern2=Pattern.compile("[0-9]+");
        Matcher matcher3=pattern2.matcher(ret[2]);
        if(!matcher3.matches()) {System.out.println("Введен неправильный возраст!");}
        else {newEmployee.setAge(Integer.parseInt(ret[2]));}
        
        Matcher matcher4=pattern.matcher(ret[3]);
        if(!matcher4.matches()) {System.out.println("Введена неправильная должность!");}
        else {newEmployee.setPosition(ret[3]);}
        
        list.add(newEmployee);
        System.out.println("Новый сотрудник создан.");
       
        
        }
        
        catch (Exception e) 
        {
            System.out.println("Новый сотрудник не создан из-за ошибки.");
            
            
        }
        
    }

    
    public void deleteEmployee(String fName)
    {
        try
        {
            int i=0;
        for(Employee employee: list)
        {
            if(employee.getFullName().equals(fName))
            {
                list.remove(employee);
                i++;
            }
        }
        if(i==0) {System.out.println("Такой сотрудник не был найден.");}
        if(i>1)  {System.out.println("Таких сотрудников было больше одного, и все они были удалены.");}
        if(i==1) {System.out.println("Такой сотрудник удалён.");}
        }
        catch (Exception e)
        {
            System.out.println("Сотрудник не был удалён из-за ошибки.");
            
        }
       
    }

    
    public String showAll()
    {
        for( Employee emp:list)
        {
            System.out.println(emp.getFullName()+" "+emp.getAge()+" "+emp.getPosition());
        }
       return null;
        
    }

   
    public Employee searchEmployee(String fName)
    {
       List<Employee> retlist=new LinkedList<>();
       try
       {
            
            
            
       for(Employee employee:list)
       {
           
            if(employee.getFullName().equals(fName))
            {
                retlist.add(employee);
                
            }
        }
        if(retlist.size()==0) 
        {
            System.out.println("Такой сотрудник не был найден.");
            return null;
            
        }
        if(retlist.size()>1)  
        {
            System.out.println("Таких сотрудников было больше одного, возвращён первый.");
            return retlist.get(0);
        }
        else {
            return retlist.get(0);
        }
        
        }
        catch (Exception e)
        {
            System.out.println("Сотрудник не был найден из-за ошибки.");
            
        }
        return null;
        
    }

}
