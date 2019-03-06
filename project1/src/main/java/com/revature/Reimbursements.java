package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Reimbursements {

	double amount;
	String info;
	byte[] imageData;
	int status_id, requestee_id, resolver_id, reimbursement_id;
	Timestamp requested, resolved;
	public Reimbursements()
	{
		
	}
	public Reimbursements (double amount, String info, int requestee_id)
	{
		this.amount = amount;
		this.info = info;
		this.requestee_id = requestee_id;
	}
	public Reimbursements (double amount, String info, int requestee_id, byte[] imageData)
	{
		this.amount = amount;
		this.info = info;
		this.requestee_id = requestee_id;
		this.imageData = imageData;
	}
	public static void addRequest(Reimbursements r) 
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee e =  Employee.getEmployee(r.requestee_id);
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	
        	String statement = "INSERT INTO Reimbursements (amount, info, status_id,"
        			+ " requestee_id, request_time, image) VALUES (" + r.amount + ", '" + r.info +
        			"', " + 2 + ", " + r.requestee_id  + ", current_timestamp, ?);";
        	PreparedStatement state = connection.prepareStatement(statement);	
        	state.setBytes(1, r.imageData);
        	state.execute();
        	
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static Reimbursements getReimbursement(int id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Reimbursements r = new Reimbursements();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM Reimbursements WHERE id = " + id + ";");
        			
        	while(rs.next())
        	{
        		r.amount = rs.getDouble("amount");
        		r.info = rs.getString("info");
        		r.status_id = rs.getInt("status_id");
        		r.reimbursement_id = rs.getInt("id");
        		r.requestee_id = rs.getInt("requestee_id");
        		r.resolver_id = rs.getInt("resolver_id");
        		r.requested = rs.getTimestamp("request_time");
        		r.resolved = rs.getTimestamp("resolve_time");
        		r.imageData = rs.getBytes("image");
        	}
        	rs.close();
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return r;
	}
	public static int reimbursementCount()
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        int count = 0;
        
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT COUNT(id) FROM Reimbursements");
        	rs.next();
        	
        		count = rs.getInt("count");
        	
        	rs.close();
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return count;
	}
	public static Reimbursements[] reimbursementsForUserId(int id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Reimbursements[] rArray;
        ArrayList<Reimbursements> toArr = new ArrayList<Reimbursements>();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM Reimbursements WHERE requestee_id = "
        	+ id + ";");
        			
        	while(rs.next())
        	{
        		Reimbursements r = new Reimbursements();
        		r.amount = rs.getDouble("amount");
        		r.info = rs.getString("info");
        		r.status_id = rs.getInt("status_id");
        		r.reimbursement_id = rs.getInt("id");
        		r.requestee_id = rs.getInt("requestee_id");
        		r.resolver_id = rs.getInt("resolver_id");
        		r.requested = rs.getTimestamp("request_time");
        		r.resolved = rs.getTimestamp("resolve_time");
        		r.imageData = rs.getBytes("image");
        		toArr.add(r);
        	}
        	rs.close();
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        rArray =  toArr.toArray(new Reimbursements[toArr.size()]);
        return rArray;
	}
	public static void approveReimbursement(int reimbursement_id, int resolver_id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee e = Employee.getEmployee(resolver_id);
        if (e.position_id != 2)
        {
        	return;
        }
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	statement.execute("UPDATE Reimbursements SET " + "resolver_id" + " = " + resolver_id 
        			+", resolve_time = current_timestamp, status_id = 1 "
        			+ "WHERE id = " + reimbursement_id + " AND status_id = 2;");
        			
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void denyReimbursement(int reimbursement_id, int resolver_id)
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Employee e = Employee.getEmployee(resolver_id);
        if (e.position_id != 2)
        {
        	return;
        }
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	statement.execute("UPDATE Reimbursements SET " + "resolver_id" + " = " + resolver_id 
        			+", resolve_time = current_timestamp, status_id = 3 "
        			+ "WHERE id = " + reimbursement_id + " AND status_id = 2;");
        			
        	
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static Reimbursements[] getAllReimbursements()
	{
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project1_schema";
        String username = "postgres";
        String password = "admin";
        Reimbursements[] rArray;
        ArrayList<Reimbursements> toArr = new ArrayList<Reimbursements>();
        try (Connection connection = DriverManager.getConnection(url, username, password))
        {
        	Statement statement = connection.createStatement();
        	ResultSet rs = statement.executeQuery("SELECT * FROM Reimbursements;");
        			
        	while(rs.next())
        	{
        		Reimbursements r = new Reimbursements();
        		r.amount = rs.getDouble("amount");
        		r.info = rs.getString("info");
        		r.status_id = rs.getInt("status_id");
        		r.reimbursement_id = rs.getInt("id");
        		r.requestee_id = rs.getInt("requestee_id");
        		r.resolver_id = rs.getInt("resolver_id");
        		r.requested = rs.getTimestamp("request_time");
        		r.resolved = rs.getTimestamp("resolve_time");
        		r.imageData = rs.getBytes("image");
        		toArr.add(r);
        	}
        	rs.close();
        } catch(SQLException e)
        {
        	e.printStackTrace();
        }
        rArray =  toArr.toArray(new Reimbursements[toArr.size()]);
        return rArray;
	}
	
}
