package com.revature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;
import org.postgresql.util.Base64;

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
					//System.out.println(e.imageData.length());
					if (e.imageData != null)
					{
						System.out.println("sending image");
						o.put("imageData", "data:image/jpeg;base64," + e.imageData);
					}
					response.getWriter().println(o.toString());
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				HttpSession session = request.getSession();
				String password = (String)session.getAttribute("password");
				String username = (String)session.getAttribute("username");
				Employee e = Employee.searchEmployee("username", username);
				
				System.out.println("changing user data");
				if (e == null ||password == null || username == null || !e.password.equals(password))
				{
					
				} else {
					JSONObject obj = new JSONObject(request.getReader().readLine());
					String email = obj.getString("email");
					String pass = obj.getString("password");
					String update_pass = obj.getString("updatePassword");
					if (pass.equals(password) && update_pass.length() > 4)
					{
						Employee.updateEmployee(e.id, "password", update_pass);
					}
					if (email.contains("@") && email.contains(".com"))
					Employee.updateEmployee(e.id, "email", email);
					try {
					String image = (String)obj.get("image");
					image = image.replaceFirst("data:image/jpeg;base64,", "");
					image = image.replaceFirst("data:image/png;base64,", "");
					image = image.replaceFirst("data:image/jpg;base64,", "");
					byte[] imageBytes = Base64.decode(image);
					
					Employee.addImage(e.id, imageBytes);
					}
					catch (Exception ex)
					{
						System.out.println("no image");
					}
				}
	}

}
