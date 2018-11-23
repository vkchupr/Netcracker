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
            System.out.println("1) ���������� ������ ����������");
            System.out.println("2) �������� ����������");
            System.out.println("3) �������� ���� �����������");
            System.out.println("4) ����� ����������");
            
            int i=Integer.parseInt(input.readLine());
            
            switch (i)
            {
            case 1:
                ss.addEmpoyee();
               

                break;
            case 2:
                System.out.println("������� ������ ��� ����������:");
                ss.deleteEmployee(input.readLine());
                
                break;
            case 3:
                ss.showAll();
                
                break;
            case 4:
                System.out.println("������� ������ ��� ����������:");
                ss.searchEmployee(input.readLine());
                
                break;
            default:
                break;
            }
         
        }

        
       
        
    }
    
}
