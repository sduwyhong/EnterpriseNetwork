package org.enterpriseNetwork.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.enterpriseNetwork.dao.employee.EmployeeDao;
import org.enterpriseNetwork.dao.enterprise.EnterpriseDao;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.result.Result;
import org.enterpriseNetwork.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	EnterpriseDao enterpriseDao;
	
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
		employee.setId(UUID.randomUUID().toString().replace("-", ""));
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
			cookie = new Cookie("emp", emp.getId()+":"+URLEncoder.encode(emp.getName(),"UTF-8")+":"+emp.getEnterprise_id());
			cookie.setPath("/");
			cookie.setMaxAge(60*30);
		} catch (UnsupportedEncodingException e) {
			System.out.println("URL编码不支持，请检查login()");
		}
		response.addCookie(cookie);
		return Result.OK;
	}

	@Override
	public String getResponsibleEnterprises(String employeeId) {
		Result result = new Result();
		result.setObject(employeeDao.getResponsibleEnterprises(employeeId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getResponsibleProducts(String employeeId) {
		Result result = new Result();
		result.setObject(employeeDao.getResponsibleProducts(employeeId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getColleagues(String employeeId) {
		Result result = new Result();
		result.setObject(employeeDao.getColleagues(employeeId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String createNewColleague(String employeeId, String colleagueId) {
		Result result = new Result();
		result.setObject(employeeDao.createNewColleague(employeeId, colleagueId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getPretenialColleagues(String employeeId) {
		Result result = new Result();
		//mysql没有minus运算
		List<Employee> pretenialColleagues = employeeDao.getPretenialColleagues(employeeId);
		List<Employee> colleagues = employeeDao.getColleagues(employeeId);
		if(pretenialColleagues != null) {
			for (Employee employee : colleagues) {
				pretenialColleagues.remove(employee);
			}
		}
		result.setObject(pretenialColleagues);
		return JSONObject.toJSONString(result);
	}

	@Override
	public String update(Employee employee) {
		employeeDao.update(employee);
		return Result.OK;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("emp")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		request.getSession().invalidate();
	}

	@Override
	public String getEnterprises() {
		Result result = new Result();
		result.setObject(enterpriseDao.getAll());
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getInfo(String employeeId) {
		Result result = new Result();
		result.setObject(employeeDao.getInfo(employeeId));
		return JSONObject.toJSONString(result);
	}

}
