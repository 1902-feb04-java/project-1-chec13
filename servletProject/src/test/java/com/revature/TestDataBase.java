package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDataBase {

	@Test
	void testGetAll() {
		assertTrue(Employee.getAll().length > 1);
	}
	@Test
	void testCount()
	{
		assertTrue(Employee.employeeCount() == 6);
		
	}
	@Test
	void testSearch()
	{
		assertNotNull(Employee.searchEmployee("username", "three"));
	}
	@Test
	void createReimbursement()
	{
		Reimbursements.addRequest(new Reimbursements(130.0, "please approve", 5));
		assertTrue(Reimbursements.reimbursementCount() > 0);
	}
	@Test
	void approveReimbursement()
	{
		Reimbursements.approveReimbursement(3, 1);
		assertTrue(Reimbursements.getReimbursement(3).status_id == 1);
	}
	@Test
	void cantApprove()
	{
		Reimbursements.approveReimbursement(5, 5);
		assertTrue(Reimbursements.getReimbursement(5).status_id != 1);
	}
	@Test
	void denyReimbursement()
	{
		Reimbursements.denyReimbursement(5, 1);
		assertTrue(Reimbursements.getReimbursement(5).status_id == 3);
	}
	@Test
	void cantDeny()
	{
		Reimbursements.denyReimbursement(6, 5);
		assertTrue(Reimbursements.getReimbursement(6).status_id == 2);
	}

}
