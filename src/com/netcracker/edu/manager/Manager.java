package com.netcracker.edu.manager;

import com.netcracker.edu.dao.*;

public class Manager
{

    public static void main(String[] args)
    {
        EmployeeDAOImpl ss=new EmployeeDAOImpl();
        
        ss.addEmpoyee();
        ss.addEmpoyee();
        System.out.println(ss.searchEmployee("Bobryakov Dmitry").getFullName());
        ss.searchEmployee("Dmitry");
        
        ss.showAll();
        ss.deleteEmployee("Dmitry");
        ss.deleteEmployee("Bobryakov Dmitry");
        
    }
}
