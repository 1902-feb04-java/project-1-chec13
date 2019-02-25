package com.project1.John;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees {
	String firstName, lastName, username, password, email;
	int position_id, manager_id;
	Date startDate;
	public static Employees getEmployee(int id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employees emp = new Employees();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	//statement.executeQuery("SET search_path TO project1_schema;");
        	ResultSet rs = statement.executeQuery("SELECT * FROM employee as emp "
        			+ "WHERE emp.id = " + id);
        	while (rs.next())
        	{
        		emp.firstName = rs.getString("first_name");
        		emp.lastName = rs.getString("last_name");
        		emp.username = rs.getString("username");
        		emp.password = rs.getString("password");
        		emp.email = rs.getString("email");
        		emp.position_id = rs.getInt("position_id");
        		emp.manager_id = rs.getInt("manager_id");
        		emp.startDate = rs.getDate("start_date");
        		
        		
        	}
        	rs.close();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return emp;
	}
}
