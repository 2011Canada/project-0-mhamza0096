package com.revature.tests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.models.Transaction;
import com.revature.repositories.EmployeeDAO;
import com.revature.services.EmployeeServices;
import com.revature.util.ConnectionFactory;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

public class TestCustomerService{
	
	private EmployeeDAO edao;
	private EmployeeServices es;
	
	
	@BeforeEach
	public void setup() {
		edao = mock(EmployeeDAO.class);
		es = new EmployeeServices();
	}
	
	@Test
	public void getTransactionTest() {

		List<Transaction> allTransactions = edao.getAllTransactions();
		
		when(edao.getAllTransactions()).thenReturn(allTransactions);
		es.getAllTransactions();
		verify(edao).getAllTransactions();

	}
	

}
