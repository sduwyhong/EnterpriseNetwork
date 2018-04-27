package org.enterpriseNetwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.enterpriseNetwork.VO.Corporation;
import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.model.Product;
import org.enterpriseNetwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {

	private final String JSON = "application/json;charset=utf-8";

	@Autowired
	AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST,produces=JSON)
	public String register(Admin admin){
		return adminService.register(admin);
	}

	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST,produces=JSON)
	public String login(@RequestParam("enterpriseId") int enterpriseId, 
			@RequestParam("admin_no") String admin_no, 
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response){
		return adminService.login(enterpriseId, admin_no, password, request, response);
	}
	
	@RequestMapping(value="/auth/logout",method=RequestMethod.POST,produces=JSON)
	public void logout(HttpServletRequest request, HttpServletResponse response){
		adminService.logout(request, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/enterprise/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	public String getEnterpriseInfo(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getEnterpriseInfo(enterpriseId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/enterprise",method=RequestMethod.POST,produces=JSON)
	public String modifyEnterprise(Enterprise enterprise){
		return adminService.modifyEnterprise(enterprise);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/employee",method=RequestMethod.POST,produces=JSON)
	public String addEmployee(Employee employee){
		return adminService.addEmployee(employee);
	}

	@ResponseBody
	@RequestMapping(value="/auth/employee/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	public String getEmployees(@PathVariable("enterpriseId") int enterpriseId){
		return adminService.getEmployees(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/employee/{employeeId}",method=RequestMethod.DELETE,produces=JSON)
	String deleteEmployee(@PathVariable("employeeId")String employeeId){
		return adminService.deleteEmployee(employeeId);	
	}

	@ResponseBody
	@RequestMapping(value="/auth/product",method=RequestMethod.POST,produces=JSON)
	String addProduct(Product product){
		return adminService.addProduct(product);
	}

	@ResponseBody
	@RequestMapping(value="/auth/product/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getProducts(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getProducts(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/product/{productId}",method=RequestMethod.DELETE,produces=JSON)
	String deleteProduct(@PathVariable("productId")int productId){
		return adminService.deleteProduct(productId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/pretential/composition/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getPretentialCompositions(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getPretentialCompositions(enterpriseId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/composition/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getCompositions(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getCompositions(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/composition",method=RequestMethod.POST,produces=JSON)
	String addComposition(@RequestParam("productId")int productId, 
			@RequestParam("compositionId")int compositionId){
		return adminService.addComposition(productId, compositionId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/delete/composition",method=RequestMethod.POST,produces=JSON)
	String deleteComposition(@RequestParam("productId")int productId, 
			@RequestParam("compositionId")int compositionId){
		return adminService.deleteComposition(productId, compositionId);
	}

	
	@ResponseBody
	@RequestMapping(value="/auth/delegation/enterprise/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getEnterpriseDelegations(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getEnterpriseDelegations(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/delegation/enterprise",method=RequestMethod.POST,produces=JSON)
	String delegateEnterprise(@RequestParam("employeeId")String employeeId, 
			@RequestParam("enterpriseId")int enterpriseId){
		return adminService.delegateEnterprise(employeeId, enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/cancel/delegation/enterprise",method=RequestMethod.POST,produces=JSON)
	String cancelDelegationOfEnterprise(@RequestParam("employeeId")String employeeId, 
			@RequestParam("enterpriseId")int enterpriseId){
		return adminService.cancelDelegationOfEnterprise(employeeId, enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/delegation/product/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getProductDelegations(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getProductDelegations(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/delegation/product",method=RequestMethod.POST,produces=JSON)
	String delegateProduct(@RequestParam("employeeId")String employeeId, 
			@RequestParam("productId")int productId){
		return adminService.delegateProduct(employeeId, productId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/cancel/delegation/product",method=RequestMethod.POST,produces=JSON)
	String cancelDelegationOfProduct(@RequestParam("employeeId")String employeeId, 
			@RequestParam("productId")int productId){
		return adminService.cancelDelegationOfProduct(employeeId, productId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/corporation/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getCorporations(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getCorporations(enterpriseId);
	}

	@ResponseBody
	@RequestMapping(value="/auth/pretential/corporation/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	String getPretentialCorporations(@PathVariable("enterpriseId")int enterpriseId){
		return adminService.getPretentialCorporations(enterpriseId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/corporation",method=RequestMethod.POST,produces=JSON)
	String corporate(Corporation corporation){
		return adminService.corporate(corporation);
	}

	@ResponseBody
	@RequestMapping(value="/auth/cancel/corporation",method=RequestMethod.POST,produces=JSON)
	String cancelCorporation(@RequestParam("enterpriseId")int enterpriseId, 
			@RequestParam("partnerId")int partnerId){
		return adminService.cancelCorporation(enterpriseId, partnerId);
	}
}
