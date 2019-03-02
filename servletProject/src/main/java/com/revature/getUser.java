package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Servlet implementation class getUser
 */
public class getUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String password = (String)session.getAttribute("password");
		String username = (String)session.getAttribute("username");
		Employee e = Employee.searchEmployee("username", username);
		
		if (e == null ||password == null || username == null || !e.password.equals(password))
		{
			session.invalidate();
			response.sendRedirect("/");
			System.out.print(password + ", " + username + ", " + e.password);
		}
		else
		{
			Employee manager = Employee.getEmployee(e.manager_id);
			JSONObject o = new JSONObject();
			o.put("username", e.username);
			o.put("id", e.id);
			o.put("first_name", e.firstName);
			o.put("last_name", e.lastName);
			o.put("email", e.email);
			o.put("Manager", manager.firstName +" " + manager.lastName);
			o.put("start_date", e.startDate.toString());
			o.put("position", e.position_id);
			
			response.getWriter().println(o.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
