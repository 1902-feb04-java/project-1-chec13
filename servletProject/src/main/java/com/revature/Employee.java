package com.revature;

import java.security.spec.EncodedKeySpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.postgresql.util.Base64;



public class Employee {
	String firstName, lastName, username, password, email;
	int position_id, manager_id, id;
	Date startDate;
	String imageData;
	public static Employee getEmployee(int id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee emp = new Employee();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	//statement.executeQuery("SET search_path TO project1_schema;");
        	ResultSet rs = statement.executeQuery("SELECT * FROM employee as emp "
        			+ "WHERE emp.id = " + id);
        	while (rs.next())
        	{
        		emp.id = rs.getInt("id");
        		emp.firstName = rs.getString("first_name");
        		emp.lastName = rs.getString("last_name");
        		emp.username = rs.getString("username");
        		emp.password = rs.getString("password");
        		emp.email = rs.getString("email");
        		emp.position_id = rs.getInt("position_id");
        		emp.manager_id = rs.getInt("manager_id");
        		emp.startDate = rs.getDate("start_date");
        		try {
        		emp.imageData = Base64.encodeBytes(rs.getBytes("image"));
        		} catch (Exception e)
        		{
        			
        		}
        		//System.out.println(emp.imageData);
        		
        	}
        	rs.close();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return emp;
	}
	public static void depositEmployee(Employee e )
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	statement.execute("INSERT INTO employee (first_name, last_name, position_id,"
        			+ "manager_id, username, password, email, start_date) VALUES ('" + e.firstName +
        			"', '" + e.lastName + "', " + e.position_id + ", " + e.manager_id + ", '" +
        			e.username + "', '" + e.password + "', '" + e.email + "', '" + e.startDate + "');");
        			
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public Employee (String fn, String ln, int p_ID, int m_ID, String username, String password,
			String email, Date start_Date)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.position_id = p_ID;
		this.manager_id = m_ID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.startDate = start_Date;
		
	}
	public Employee()
	{
		
	}
	public static void updateEmployee(int id, String col, String s)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	statement.execute("UPDATE employee SET " + col + " = '" + s 
        			+"' WHERE employee.id = " + id + ";");
        			
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void addImage(int id, byte[] b)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	
        	String statement = "UPDATE employee SET image = ? WHERE employee.id = " + id + ";";
        	PreparedStatement p = connection.prepareStatement(statement);
        	p.setBytes(1, b);
        	
        	p.execute();
        			
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static int employeeCount()
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        int count = 0;
        
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT COUNT(id) FROM employee");
        	rs.next();
        	
        		count = rs.getInt("count");
        	
        	rs.close();
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return count;
	}
	public static Employee[] getAll()
	{
		
		int count = employeeCount();
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee[] emp = new Employee[count];
        for (int x = 0; x < emp.length; x++)
        {
        	emp[x] = new Employee();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	//statement.executeQuery("SET search_path TO project1_schema;");
        	ResultSet rs = statement.executeQuery("SELECT * FROM employee;");
        	int x = 0;
        	while (rs.next())
        	{
        		emp[x].id = rs.getInt("id");
        		emp[x].firstName = rs.getString("first_name");
        		emp[x].lastName = rs.getString("last_name");
        		emp[x].username = rs.getString("username");
        		emp[x].password = rs.getString("password");
        		emp[x].email = rs.getString("email");
        		emp[x].position_id = rs.getInt("position_id");
        		emp[x].manager_id = rs.getInt("manager_id");
        		emp[x].startDate = rs.getDate("start_date");
        		x++;
        		
        	}
        	rs.close();
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
        return emp;
	}
	public void printData()
	{
		System.out.println("First Name: " + firstName
				+ " Last Name: " + lastName +
				" Username: " + username +
				" Password: " + password +
				" Email: " + email +
				" position_id: " + position_id +
				" manager_id: " + manager_id +
				" id: " + id);
	}
	public static Employee searchEmployee(String col, String value)
	{
		
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee emp = new Employee();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	//statement.executeQuery("SET search_path TO project1_schema;");
        	ResultSet rs = statement.executeQuery("SELECT * FROM employee WHERE " +
        	col + " = '" + value + "';");
        	while (rs.next())
        	{
        		emp.id = rs.getInt("id");
        		emp.firstName = rs.getString("first_name");
        		emp.lastName = rs.getString("last_name");
        		emp.username = rs.getString("username");
        		emp.password = rs.getString("password");
        		emp.email = rs.getString("email");
        		emp.position_id = rs.getInt("position_id");
        		emp.manager_id = rs.getInt("manager_id");
        		emp.startDate = rs.getDate("start_date");
        		try {
        		emp.imageData = Base64.encodeBytes(rs.getBytes("image"));
        		} catch (Exception e)
        		{
        			
        		}
        		return emp;
        		
        	}
        	rs.close();
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
        return null;
	}
}
