package com.project1.John;
import java.sql.Connection;
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
    	Employees emp = Employees.getEmployee(1);
    	System.out.println(emp.firstName);
        

        
        
       


    }
}
