package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ReimbursementsData
 */
public class ReimbursementsData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsData() {
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
		
		if (password.equals(e.password))
		{
			JSONObject objArr = new JSONObject();
			int x = 0;
			if (e.position_id == 1)
			{
				Reimbursements[] reimbursementsArray = Reimbursements.reimbursementsForUserId(e.id);
				for(Reimbursements r: reimbursementsArray)
				{
					Employee res = Employee.getEmployee(r.resolver_id);
					JSONObject obj = new JSONObject();
					obj.put("reimbursement_id", r.reimbursement_id);
					obj.put("amount", r.amount);
					obj.put("info", r.info);
					obj.put("requestee", e.firstName +" " + e.lastName);
					obj.put("resolver", res.firstName + " " + res.lastName);
					obj.put("request_time", r.requested);
					obj.put("resolved", r.resolved);
					obj.put("status", r.status_id);
					objArr.put((x + ""), obj);
					x++;
				}
				response.getWriter().println(objArr.toString());
			}
			else if (e.position_id == 2)
			{
				Reimbursements[] reimbursementsArray = Reimbursements.getAllReimbursements();
				for(Reimbursements r: reimbursementsArray)
				{
					Employee res = Employee.getEmployee(r.resolver_id);
					Employee req = Employee.getEmployee(r.requestee_id);
					JSONObject obj = new JSONObject();
					obj.put("reimbursement_id", r.reimbursement_id);
					obj.put("amount", r.amount);
					obj.put("info", r.info);
					obj.put("requestee", req.firstName +" " + req.lastName);
					obj.put("resolver", res.firstName + " " + res.lastName);
					obj.put("request_time", r.requested);
					obj.put("resolved", r.resolved);
					obj.put("status", r.status_id);
					objArr.put((x + ""), obj);
					x++;
				}
				response.getWriter().println(objArr.toString());
			}
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
		
		if (password.equals(e.password))
		{
		System.out.println("request");
		JSONObject obj = new JSONObject(request.getReader().readLine());
		int status = (int)obj.get("status");
		int id = (int)obj.get("id");
		System.out.println(status + " " + id);
		if (status == 1)
		{
			Reimbursements.approveReimbursement(id, e.id);
		}
		else if (status == 3)
		{
			Reimbursements.denyReimbursement(id, e.id);
		}
		}
	}

}
