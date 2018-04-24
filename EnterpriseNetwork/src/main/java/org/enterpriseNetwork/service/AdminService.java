package org.enterpriseNetwork.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Product;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface AdminService {

	String register(Admin admin);
	
	String login(String admin_no, String password, 
			HttpServletRequest request, HttpServletResponse response);
	
	String addEmployee(Employee employee);
	
	String deleteEmployee(String employeeId);
	
	String addProduct(Product product);
	
	String deleteProduct(int productId);
	
	String addComposition(int productId, int compositionId);
	
	String deleteComposition(int productId, int compositionId);
	
	String delegateEnterprise(String employeeId, int enterpriseId);
	
	String cancelDelegationOfEnterprise(String employeeId, int enterpriseId);
	
	String delegateProduct(String employeeId, int productId);
	
	String cancelDelegationOfProduct(String employeeId, int productId);
	
	String corporate(int enterpriseId, int partnerId);
	
	String cancelCorporation(int enterpriseId, int partnerId);
	
	
}
