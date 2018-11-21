package com.netcracker.edu.dao;



import com.netcracker.edu.object.Employee;

public interface EmployeeDAO
{
    
  public  void addEmpoyee();
    
  public void deleteEmployee(String fName);
    
  public  String showAll();
    
  public  Employee searchEmployee(String fName);
    
}
