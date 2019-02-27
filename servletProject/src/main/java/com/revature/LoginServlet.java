package com.revature;

import java.io.IOException;
import com.revature.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)req.getAttribute("username");
		String password =  (String)req.getAttribute("password");
		Employee[] e = Employee.getAll();
		System.out.println(e.length);
		
	}
}
