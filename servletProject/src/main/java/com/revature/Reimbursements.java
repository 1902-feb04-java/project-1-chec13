package com.revature;

import java.sql.Timestamp;

public class Reimbursements {

	double amount;
	String info;
	int status_id, requestee_id, resolver_id;
	Timestamp requested, resolved;
}