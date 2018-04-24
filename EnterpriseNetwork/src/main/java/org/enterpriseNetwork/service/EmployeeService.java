package org.enterpriseNetwork.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.enterpriseNetwork.model.Employee;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EmployeeService {

	String register(Employee employee);
	
	String login(String worker_no, String password, 
			HttpServletRequest request, HttpServletResponse response);
	
	String getResponsibleEnterprises(String employeeId);
	
	String getResponsibleProducts(String employeeId);
	
	String getColleagues(String employeeId);
	
	String createNewColleague(String employeeId, String colleageId);
}
