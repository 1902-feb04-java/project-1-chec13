package com.project1.John;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.ResultSet;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) 
    {
    	//Employee emp = Employee.getEmployee(1);
    	//System.out.println(emp.firstName);
        Date d = new Date(System.currentTimeMillis());
    	
    	//Employee newEmployee = new Employee("David", "Pamack", 1, 1, "Dpamack", "Dppassword",
    	//		"DPamack@gmail.com", d);
    	//Employee.depositEmployee(newEmployee);
        //Employee.updateEmployee(7, "password", "Dpassword") ;
        //System.out.println(Employee.employeeCount());
        Employee[] all = Employee.getAll();
        
        for(Employee e: all)
        {
        	e.printData();
        }
        
        
       


    }
}
