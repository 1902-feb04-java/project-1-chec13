package com.revature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class allEmployees
 */
public class allEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String password = (String) session.getAttribute("password");
		String username = (String) session.getAttribute("username");
		Employee e = Employee.searchEmployee("username", username);
		
		if (e == null ||password == null || username == null || !e.password.equals(password))
		{
			session.invalidate();
			response.sendRedirect("/");
			System.out.print(password + ", " + username + ", " + e.password);
		}
		if(e.position_id == 2)
		{
			Employee[] all = Employee.getAll();
			String start = "<ul>", end = "</ul>", container="";
			for (Employee current: all)
			{
				if (current.position_id == 2)
				{
					container+= "<li> Position: Manager Name: " + current.firstName + " " + current.lastName + "</li>";
				}
				else
				{
					container+= "<li> Position: Employee Name: " + current.firstName + " " + current.lastName + "</li>";
				}
			}
			String complete = start + container + end;
			response.getWriter().println(complete);
		}
	
	}
	

}
