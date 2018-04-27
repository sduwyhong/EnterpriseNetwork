package org.enterpriseNetwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeAction {

	private final String JSON = "application/json;charset=utf-8";
	
	@Autowired
	EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST,produces=JSON)
	public String register(Employee employee){
		return employeeService.register(employee);
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST,produces=JSON)
	public String login(@RequestParam("enterpriseId")int enterpriseId, 
			@RequestParam("worker_no") String worker_no, 
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response){
		return employeeService.login(enterpriseId, worker_no, password, request, response);
	}
	
	@RequestMapping(value="/auth/logout",method=RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response){
		employeeService.logout(request, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/info/{employeeId}",method=RequestMethod.GET,produces=JSON)
	public String getInfo(@PathVariable("employeeId")String employeeId){
		return employeeService.getInfo(employeeId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/update",method=RequestMethod.POST,produces=JSON)
	public String update(Employee employee){
		return employeeService.update(employee);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/enterprise/{employeeId}",method=RequestMethod.GET,produces=JSON)
	public String getResponsibleEnterprises(@PathVariable("employeeId") String employeeId){
		return employeeService.getResponsibleEnterprises(employeeId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/product/{employeeId}",method=RequestMethod.GET,produces=JSON)
	public String getResponsibleProducts(@PathVariable("employeeId") String employeeId){
		return employeeService.getResponsibleProducts(employeeId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/colleague/{employeeId}",method=RequestMethod.GET,produces=JSON)
	public String getColleagues(@PathVariable("employeeId") String employeeId){
		return employeeService.getColleagues(employeeId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/pretential/colleague/{employeeId}",method=RequestMethod.GET,produces=JSON)
	public String getPretenialColleagues(@PathVariable("employeeId") String employeeId){
		return employeeService.getPretenialColleagues(employeeId);
	}
	
	@ResponseBody
	@RequestMapping(value="/auth/colleague",method=RequestMethod.POST,produces=JSON)
	public String createNewColleague(@RequestParam("employeeId") String employeeId, 
			@RequestParam("colleagueId") String colleagueId){
		return employeeService.createNewColleague(employeeId, colleagueId);
	}
}
