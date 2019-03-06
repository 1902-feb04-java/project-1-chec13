package com.revature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.postgresql.util.Base64;

/**
 * Servlet implementation class createReimbursement
 */
public class createReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createReimbursement() {
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
		String password = (String)session.getAttribute("password");
		String username = (String)session.getAttribute("username");
		
		Employee e = Employee.searchEmployee("username", username);
		double amount = Double.parseDouble(request.getParameter("amount"));
		if (password.equals(e.password))
		{
			String imageData = (String)request.getParameter("imageData");
			imageData = imageData.replaceFirst("data:image/jpeg;base64,", "");
			byte[] image = Base64.decode(imageData);
			Reimbursements r = new Reimbursements(amount, request.getParameter("info"),
					e.id, image);
			Reimbursements.addRequest(r);
			request.getRequestDispatcher("/reimbursements.html").forward(request, response);
		}
	}

}
