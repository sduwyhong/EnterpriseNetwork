package org.enterpriseNetwork.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.enterpriseNetwork.dao.employee.EmployeeDao;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.result.Result;
import org.enterpriseNetwork.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.UserDataHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public String register(Employee employee) {
		Employee emp = employeeDao.getByNo(employee.getWorker_no());
		if(emp != null) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("工号已经存在！");
			return JSONObject.toJSONString(result);
		}
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(employee);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		employeeDao.insert(employee);
		return Result.OK;
	}

	@Override
	public String login(String worker_no, String password,
			HttpServletRequest request, HttpServletResponse response) {
		Employee emp = employeeDao.getByNo(worker_no);
		if(emp == null || !emp.getPassword().equals(password)) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("用户名或密码有误！");
			return JSONObject.toJSONString(result);
		}
		//本机ajax不做session跨域管理
		HttpSession session = request.getSession();
		session.setAttribute("emp", worker_no);
		Cookie cookie = null;
		try {
			cookie = new Cookie("emp", URLEncoder.encode(emp.getName(),"UTF-8")+":"+password);
		} catch (UnsupportedEncodingException e) {
			System.out.println("URL编码不支持，请检查login()");
		}
		response.addCookie(cookie);
		return Result.OK;
	}

	@Override
	public String getResponsibleEnterprises(String employeeId) {
		return employeeDao.getResponsibleEnterprise(employeeId);
	}

	@Override
	public String getResponsibleProducts(String employeeId) {
		return employeeDao.getResponsibleProducts(employeeId);
	}

	@Override
	public String getColleagues(String employeeId) {
		return employeeDao.getColleagues(employeeId);
	}

	@Override
	public String createNewColleague(String employeeId, String colleagueId) {
		return employeeDao.createNewColleague(employeeId, colleagueId);
	}

}
