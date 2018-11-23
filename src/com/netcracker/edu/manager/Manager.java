package com.netcracker.edu.manager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.netcracker.edu.dao.*;

public class Manager
{

    public static void main(String[] args) throws NumberFormatException, IOException 
    {
        EmployeeDAOImpl ss=new EmployeeDAOImpl();
       
        
        while(true)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1) Добавление нового сотрудника");
            System.out.println("2) Удаление сотрудника");
            System.out.println("3) Показать всех сотрудников");
            System.out.println("4) Поиск сотрудника");
            
            int i=Integer.parseInt(input.readLine());
            
            switch (i)
            {
            case 1:
                ss.addEmpoyee();
               

                break;
            case 2:
                System.out.println("Введите полное имя сотрудника:");
                ss.deleteEmployee(input.readLine());
                
                break;
            case 3:
                ss.showAll();
                
                break;
            case 4:
                System.out.println("Введите полное имя сотрудника:");
                ss.searchEmployee(input.readLine());
                
                break;
            default:
                break;
            }
         
        }

        
       
        
    }
    
}
