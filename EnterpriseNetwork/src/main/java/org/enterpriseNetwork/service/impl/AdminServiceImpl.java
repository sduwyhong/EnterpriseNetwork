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

import org.enterpriseNetwork.VO.Corporation;
import org.enterpriseNetwork.dao.admin.AdminDao;
import org.enterpriseNetwork.dao.employee.EmployeeDao;
import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Product;
import org.enterpriseNetwork.result.Result;
import org.enterpriseNetwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public String register(Admin admin) {
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(admin);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		adminDao.insert(admin);
		return Result.OK;
	}

	@Override
	public String login(String admin_no, String password,
			HttpServletRequest request, HttpServletResponse response) {
		Admin admin = adminDao.getByNo(admin_no);
		if(admin == null || !admin.getPassword().equals(password)) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("用户名或密码有误！");
			return JSONObject.toJSONString(result);
		}
		//本机ajax不做session跨域管理
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin_no);
		Cookie cookie = null;
		try {
			cookie = new Cookie("admin", URLEncoder.encode(admin.getName(),"UTF-8")+":"+password);
		} catch (UnsupportedEncodingException e) {
			System.out.println("URL编码不支持，请检查login()");
		}
		response.addCookie(cookie);
		return Result.OK;
	}

	@Override
	public String addEmployee(Employee employee) {
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
	public String deleteEmployee(String employeeId) {
		adminDao.deleteEmployee(employeeId);
		return Result.OK;
	}

	@Override
	public String addProduct(Product product) {
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(product);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		adminDao.addProduct(product);
		return Result.OK;
	}

	@Override
	public String deleteProduct(int productId) {
		adminDao.deleteProduct(productId);
		return Result.OK;
	}

	@Override
	public String addComposition(int productId, int compositionId) {
		adminDao.addComposition(productId, compositionId);
		return Result.OK;
	}

	@Override
	public String deleteComposition(int productId, int compositionId) {
		adminDao.deleteComposition(productId, compositionId);
		return Result.OK;
	}

	@Override
	public String delegateEnterprise(String employeeId, int enterpriseId) {
		adminDao.delegateEnterprise(employeeId, enterpriseId);
		return Result.OK;
	}

	@Override
	public String cancelDelegationOfEnterprise(String employeeId,
			int enterpriseId) {
		adminDao.cancelDelegationOfEnterprise(employeeId, enterpriseId);
		return Result.OK;
	}

	@Override
	public String delegateProduct(String employeeId, int productId) {
		adminDao.delegateProduct(employeeId,productId);
		return Result.OK;
	}

	@Override
	public String cancelDelegationOfProduct(String employeeId, int productId) {
		adminDao.cancelDelegationOfProduct(employeeId,productId);
		return Result.OK;
	}

	@Override
	public String corporate(Corporation corporation) {
		adminDao.corporate(corporation);
		return Result.OK;
	}

	@Override
	public String cancelCorporation(int enterpriseId, int partnerId) {
		adminDao.cancelCorporation(enterpriseId, partnerId);
		return Result.OK;
	}

	@Override
	public String getEmployees() {
		
		return null;
	}

	@Override
	public String getProducts() {
		
		return null;
	}

	@Override
	public String getCompositions() {
		
		return null;
	}

	@Override
	public String getEnterpriseDelegations() {
		
		return null;
	}

	@Override
	public String getProductDelegations() {
		
		return null;
	}

	@Override
	public String getCorporations() {
		
		return null;
	}

}
