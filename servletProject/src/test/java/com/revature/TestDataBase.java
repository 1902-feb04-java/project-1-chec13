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

}
