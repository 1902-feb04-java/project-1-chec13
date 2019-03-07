package com.revature;

import java.io.IOException;
import com.revature.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.json.JSONObject;

//import java.sql.*;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Scanner;

public class LoginServlet extends HttpServlet {
	
	@Override
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("username", req.getParameter("username"));
		session.setAttribute("password", req.getParameter("password"));
		doGet(req, resp);
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			Class.forName("org.postgresql.Driver");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		String password = (String) session.getAttribute("password");
		String username = (String) session.getAttribute("username");
		
		Employee e = Employee.searchEmployee("username", username);
		if (password==null || username==null)
		{
			session.invalidate();
			req.getRequestDispatcher("login.html").forward(req, resp);
			
			System.out.println("no username no password");
		} else if(e == null) {
			session.invalidate();
			req.getRequestDispatcher("login-username.html").forward(req, resp);
			System.out.println("invalid username" + ", " + password);
		}
		else if(!e.password.equals(password))
		{
			session.invalidate();
			req.getRequestDispatcher("login-password.html").forward(req, resp);
			System.out.println("invalid password");
		}
		else
		{
			 
			resp.sendRedirect("reimbursements.html");
		}
		
		
	}
}
